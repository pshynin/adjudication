package com.agmednet.judi.tests.debug;

import com.agmednet.judi.testrail.TestRailAPIClient;
import com.agmednet.judi.testrail.TestRailAPIException;
import com.agmednet.judi.tests.TestBase;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pshynin on 1/17/17.
 */
public class TestRailTest extends TestBase{

    @Test
    public void getCase() {
        TestRailAPIClient client = new TestRailAPIClient("https://pshynin.testrail.net/");
        client.setUser("pshynin@yahoo.com");
        client.setPassword("fj9y55sx1cPGQQEFWijK");
        JSONObject c = null;
        try {
            c = (JSONObject) client.sendGet("get_case/5");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TestRailAPIException e) {
            e.printStackTrace();
        }
        System.out.println(c.get("title"));
    }

    @Test
    public void createRun() {
    }

    @Test
    public void addRun() {
        TestRailAPIClient client = new TestRailAPIClient("https://pshynin.testrail.net/");
        client.setUser("pshynin@yahoo.com");
        client.setPassword("fj9y55sx1cPGQQEFWijK");
        JSONObject c = null;

        Map data = new HashMap();
        data.put("status_id", new Integer(1));
        data.put("comment", "This test worked fine!");

        data.put("title", "This is a test case");
        data.put("type_id", 1);
        data.put("priority_id", 3);
        data.put("estimate", "3m");
        data.put("refs", "RF-1, RF-2");


        try {
            JSONObject r = (JSONObject) client.sendPost("add_run/1", data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TestRailAPIException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void addTestCase() {
        TestRailAPIClient client = new TestRailAPIClient("https://pshynin.testrail.net/");
        client.setUser("pshynin@yahoo.com");
        client.setPassword("fj9y55sx1cPGQQEFWijK");
        JSONObject c = null;

        Map data = new HashMap();
        List steps = new ArrayList();


        data.put("title", "Title 1");
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

        data.put("custom_steps_separated", steps);

        try {
            JSONObject r = (JSONObject) client.sendPost("add_case/1", data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TestRailAPIException e) {
            e.printStackTrace();
        }
    }
}