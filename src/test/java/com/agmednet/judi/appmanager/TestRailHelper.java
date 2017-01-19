package com.agmednet.judi.appmanager;

import com.agmednet.judi.testrail.Step;
import com.agmednet.judi.testrail.TestCase;
import com.agmednet.judi.testrail.TestRailAPIClient;
import com.agmednet.judi.testrail.TestRailAPIException;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by pshynin on 1/18/17.
 */
public class TestRailHelper {
    private Properties properties;
    private List steps;

    TestRailHelper(Properties properties) {
        this.properties = properties;
        steps = new ArrayList();
    }

    public void addCase() {
        TestRailAPIClient client = new TestRailAPIClient(properties.getProperty("testRail.url"));

        client.setUser(properties.getProperty("testRail.username"));
        client.setPassword(properties.getProperty("testRail.password"));
        JSONObject c = null;

        Map data = new HashMap();

        data.put("title", "Event Coordinator able to create a new Event");
        data.put("type_id", 1);
        data.put("priority_id", 3);
        data.put("estimate", "15m");
        data.put("refs", "RF-1, RF-2");
        data.put("template_id", 2);

        Map step1 = new HashMap();
        step1.put("status_id", new Integer(1));
        step1.put("content", "Login as Event Coordinator");
        step1.put("expected", "Event Coordinator is logged in");
        steps.add(step1);

        Map step2 = new HashMap();
        step2.put("status_id", new Integer(2));
        step2.put("content", "Create Event");
        step2.put("expected", "Event created");
        steps.add(step2);

        Map step3 = new HashMap();
        step2.put("status_id", new Integer(3));
        step2.put("content", "Logout");
        step2.put("expected", "Logout successfull");
        steps.add(step3);

        List steps = new ArrayList();

        data.put("custom_steps_separated", steps);

        try {
            JSONObject r = (JSONObject) client.sendPost("add_case/1", data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TestRailAPIException e) {
            e.printStackTrace();
        }

    }

    public void addStep(int stepOrder, String result, String test01,String test02) {
        Map step = new HashMap();

    //    Method testMethod = getClass().getMethod();
    //if(testMethod.isAnnotationPresent(Step.class))
//    {
//      TestCase testCase = testMethod.getAnnotation(TestCase.class);
//      //Do something with testCase.testId();
//      step.put("status_id", stepData.testId());
    }

//        step.put("status_id", stepOrder);
//        step.put("content", result);
//        step.put("expected", test01);
//        step.put("test",test02);
//        steps.add(step);
//    }

}
