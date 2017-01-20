package com.agmednet.appmanager;

import com.codepine.api.testrail.TestRail;

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

    public void connect() {
        TestRail.builder(properties.getProperty("testRail.url"),
                properties.getProperty("testRail.username"), properties.getProperty("testRail.password"));
    }


    public void addStep(int stepOrder, String result, String test01, String test02) {
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
