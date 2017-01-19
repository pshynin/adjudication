package com.agmednet.judi.testrail.internal;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by pshynin on 1/18/17.
 */
public class TestRailArgs {

    //if the listener is enabled or not
    private Boolean enabled;
    //test plan id (if one already exists)
    private Integer testPlanId;
    //suite names
    private List<String> suiteNames;
    //url to the TestRail instance
    private String url;
    //username to login to TestRail
    private String username;
    //password to login to TestRail
    private String password;

    private TestRailArgs() {
    }

    public static TestRailArgs getNewTestRailListenerArgs() {
        TestRailArgs args = new TestRailArgs();
        args.enabled = Boolean.valueOf(System.getProperty("testRail.enabled"));

        if (args.enabled == null || !args.enabled) {
            return args; //no need to process further. TestRail reporting is not enabled
        }

        String planId = System.getProperty("testRail.testPlanId");
        if (planId == null) {
            throw new IllegalArgumentException("TestRail Test Plan ID not specified");
        } else {
            try {
                args.testPlanId = Integer.valueOf(planId);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Plan Id is not an integer as expected");
            }
        }

        String suiteNamesStr = System.getProperty("testRail.suiteNames");
        if (suiteNamesStr != null) {
            try {
                String[] suiteNamesArr = suiteNamesStr.split(",");
                args.suiteNames = new ArrayList<String>();
                for (String suiteName : suiteNamesArr) {
                    if (suiteName != null && !suiteName.trim().isEmpty()) {
                        args.suiteNames.add(suiteName.trim());
                    }
                }

            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Plan Id is not an integer as expected");
            }
        }

        if ((args.url = System.getProperty("testRail.url")) == null) {
            throw new IllegalArgumentException("TestRail URL not specified (testRail.url)");
        }

        if ((args.username = System.getProperty("testRail.username")) == null) {
            throw new IllegalArgumentException("TestRail user not specified (testRail.username)");
        }

        if ((args.password = System.getProperty("testRail.password")) == null) {
            throw new IllegalArgumentException("TestRail password not specified (testRail.password)");
        }

        return args;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Integer getTestPlanId() {
        return testPlanId;
    }

    public List<String> getSuiteNames() {
        return suiteNames;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
