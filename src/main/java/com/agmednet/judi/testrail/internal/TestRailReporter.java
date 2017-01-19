package com.agmednet.judi.testrail.internal;

import com.agmednet.judi.testrail.client.ClientException;
import com.agmednet.judi.testrail.client.TestRailClient;
import com.agmednet.judi.testrail.dto.Case;
import com.agmednet.judi.testrail.dto.Plan;
import com.agmednet.judi.testrail.dto.PlanEntry;
import com.agmednet.judi.testrail.dto.Run;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by pshynin on 1/18/17.
 */
public class TestRailReporter {

    //keys for the properties map that is used to pass test information into this reporter
    public static final String KEY_MORE_INFO = "moreInfo";
    public static final String KEY_SCREENSHOT_URL = "screenshotUrl";
    public static final String KEY_STATUS = "status";
    public static final String KEY_ELAPSED = "elapsed";
    public static final String KEY_THROWABLE = "throwable";
    private Logger logger = Logger.getLogger(TestRailReporter.class.getName());
    private TestRailClient client;
    private Map<String, Integer> caseIdLookupMap;
    private Map<String, Integer> testToRunIdMap;
    private Boolean enabled;
    private String config;

    /**
     * Initializes the required state including any required caches.
     * <p>
     * This method is allowed to throw exception so that we can stop test execution
     * to fix invalid configuration before proceeding further
     *
     * @throws java.io.IOException
     * @throws com.nullin.testrail.client.ClientException
     */
    private TestRailReporter() {
        TestRailArgs args = TestRailArgs.getNewTestRailListenerArgs();
        enabled = args.getEnabled();
        enabled = enabled == null ? false : enabled;

        if (!enabled) {
            logger.info("TestRail listener is not enabled. Results will not be reported to TestRail.");
            return;
        }

        logger.info("TestRail listener is enabled. Configuring...");
        try {
            client = new TestRailClient(args.getUrl(), args.getUsername(), args.getPassword());

            //prepare the test plan and stuff
            Plan plan = client.getPlan(args.getTestPlanId());

            /*
            We will make an assumption that the plan can contains multiple entries, but all the
            entries would be associated with the same suite. This helps simplify the automated
            reporting of results.

            Another assumption is that a test with a given automation id will not re-appear twice
            for the same configuration set. Multiple instances of the same configuration set
            is possible. If same automation id and configuration set combination is repeated, the
            result would only be reported once.
             */
            Set<Integer> suiteIdSet = new HashSet<Integer>();
            List<PlanEntry> planEntries = plan.entries;

            int projectId = 0;
            int suiteId = 0;
            testToRunIdMap = new HashMap<String, Integer>();
            for (PlanEntry entry : planEntries) {
                suiteIdSet.add(suiteId = entry.suiteId);
                for (Run run : entry.runs) {
                    projectId = run.projectId;
                    List<Test> tests = client.getTests(run.id);
                    for (Test test : tests) {
                       // testToRunIdMap.put(test.automationId + run.config, run.id);
                    }
                }
            }

            caseIdLookupMap = cacheCaseIdLookupMap(client, projectId, suiteId);

            //check some constraints
            if (suiteIdSet.size() != 1) {
                throw new IllegalStateException("Referenced plan " + plan.id + " has multiple test suites (" +
                        suiteIdSet + "). This configuration is currently not supported.");
            }

            /*
             This should be specified when starting the JVM for test execution. It should match exactly at least
             one of the configurations used in the test runs. This, along with the automation id of the test
             are used to identify the run id.
             */
            config = System.getProperty("testRail.runConfig");
        } catch (Exception ex) {
            //wrap in a Runtime and throw again
            //why? because we don't want to handle it and we want
            //to stop the execution asap.
            throw new RuntimeException(ex);
        }
    }

    public static TestRailReporter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Gets all the test cases associated with the test run and caches a map of the
     * associated automation id's to the case ids
     *
     * @param projectId
     * @param suiteId
     * @return Map with keys as automation ids and corresponding values as the case ids.
     */
    private Map<String, Integer> cacheCaseIdLookupMap(TestRailClient client, int projectId, int suiteId)
            throws IOException, ClientException {
        List<Case> cases = client.getCases(projectId, suiteId, 0, null);
        Map<String, Integer> lookupMap = new HashMap<String, Integer>();
        for (Case c : cases) {
            if (c.automationId == null || c.automationId.isEmpty()) {
                continue; //ignore empty automation IDs
            }

            if (lookupMap.get(c.automationId) != null) {
                logger.severe("Found multiple tests cases with same automation id. " +
                        "Case Ids " + lookupMap.get(c.automationId) + " & " + c.id);
            } else {
                lookupMap.put(c.automationId, c.id);
            }
        }
        return lookupMap;
    }

    /**
     * Reports results to testrail
     *
     * @param automationId test automation id
     * @param properties   test properties. Following values are supported:
     *                     <ul>
     *                     <li>{@link #KEY_ELAPSED}: elapsed time as string</li>
     *                     <li>{@link #KEY_MORE_INFO}: more test information as Map<String, String></li>
     *                     <li>{@link #KEY_SCREENSHOT_URL}: screen shot url as string</li>
     *                     <li>{@link #KEY_STATUS}: {@link ResultStatus} of the test</li>
     *                     <li>{@link #KEY_THROWABLE}: any associated {@link Throwable}</li>
     *                     </ul>
     */
    public void reportResult(String automationId, Map<String, Object> properties) {
        if (!enabled) {
            return; //do nothing
        }

        ResultStatus resultStatus = (ResultStatus) properties.get(KEY_STATUS);
        Throwable throwable = (Throwable) properties.get(KEY_THROWABLE);
        String elapsed = (String) properties.get(KEY_ELAPSED);
        String screenshotUrl = (String) properties.get(KEY_SCREENSHOT_URL);
        Map<String, String> moreInfo = (Map<String, String>) properties.get(KEY_MORE_INFO);

        try {
            Integer caseId = caseIdLookupMap.get(automationId);
            if (caseId == null) {
                logger.severe("Didn't find case id for test with automation id " + automationId);
                return; //nothing more to do
            }

            StringBuilder comment = new StringBuilder("More Info (if any):\n");
            if (moreInfo != null && !moreInfo.isEmpty()) {
                for (Map.Entry<String, String> entry : moreInfo.entrySet()) {
                    comment.append("- ").append(entry.getKey()).append(" : ")
                            .append('`').append(entry.getValue()).append("`\n");
                }
            } else {
                comment.append("- `none`\n");
            }
            comment.append("\n");
            if (screenshotUrl != null && !screenshotUrl.isEmpty()) {
                comment.append("![](").append(screenshotUrl).append(")\n\n");
            }
            if (resultStatus.equals(ResultStatus.SKIP)) {
                comment.append("Test skipped because of configuration method failure. " +
                        "Related config error (if captured): \n\n");
                comment.append(getStackTraceAsString(throwable));
            }
            if (resultStatus.equals(ResultStatus.FAIL)) {
                comment.append("Test failed with following exception (if captured): \n\n");
                comment.append(getStackTraceAsString(throwable));
            }

            //add the result
            Map<String, Object> body = new HashMap<String, Object>();
            body.put("status_id", getStatus(resultStatus));
            body.put("comment", new String(comment.toString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            body.put("elapsed", elapsed);

            Integer runId = testToRunIdMap.get(automationId + config);
            if (runId == null) {
                throw new IllegalArgumentException("Unable to find run id for test with automation id "
                        + automationId + " and configuration set as " + config);
            }
            client.addResultForCase(runId, caseId, body);
        } catch (Exception ex) {
            //only log and do nothing else
            logger.severe("Ran into exception " + ex.getMessage());
        }
    }

    private String getStackTraceAsString(Throwable throwable) throws UnsupportedEncodingException {
        if (throwable == null) {
            return "";
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        throwable.printStackTrace(new PrintStream(os));
        String str = new String(os.toByteArray(), "UTF-8");
        str = "    " + str.replace("\n", "\n    ").replace("\t", "    "); //better printing
        return str;
    }

    /**
     * @param status TestNG specific status code
     * @return TestRail specific status IDs
     */
    private int getStatus(ResultStatus status) {
        switch (status) {
            case PASS:
                return 1; //Passed
            case FAIL:
                return 5; //Failed
            case SKIP:
                return 2; //Blocked
            default:
                return 3; //Untested
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    private static class Holder {
        private static final TestRailReporter INSTANCE = new TestRailReporter();
    }

}
