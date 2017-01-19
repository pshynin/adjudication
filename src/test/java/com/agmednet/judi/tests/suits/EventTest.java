package com.agmednet.judi.tests.suits;

import com.agmednet.judi.model.EventData;
import com.agmednet.judi.model.Events;
import com.agmednet.judi.testrail.Step;
import com.agmednet.judi.testrail.annotations.TestRailCase;
import com.agmednet.judi.tests.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EventTest extends TestBase {

    private EventData event = new EventData().withTrial("Credo").withSite("Site").withSubject("Subject")
            .withEventtype("Type").withEid("Test1").withEventterm("term1").withOnsetdate("Date1");


    @DataProvider
    public Iterator<Object[]> validEvent() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/Judi_Demo.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new EventData().withTrial(split[2])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @TestRailCase
    @Test(enabled = true, priority = 1)
    public void testEventCreation() {
        app.goTo().loginPage();
        app.loginAs().eventCoordinator();
        app.goTo().logoutPage();
    }

    @Test(enabled = true, priority = 2)
    public void testUploaderStage() {
        app.goTo().loginPage();
        app.loginAs().uploader();
        app.goTo().logoutPage();
    }

    @Test(enabled = true, priority = 3)
    public void testReviewerStage() {
        app.goTo().loginPage();
        app.loginAs().reviewer();
        app.goTo().logoutPage();
    }

    @Test(enabled = true, priority = 4)
    public void testFirstAdjudicatorStage() {
        app.goTo().loginPage();
        app.loginAs().adjudicatorFirst();
        app.goTo().logoutPage();
    }

    @Test(enabled = true, priority = 5)
    public void testSecondAdjudicatorStage() {
        app.goTo().loginPage();
        app.loginAs().adjudicatorSecond();
        app.goTo().logoutPage();
    }

    @Test(enabled = true, priority = 6)
    public void testCommitteeChairStage() {
        app.goTo().loginPage();
        app.loginAs().committeeChair();
        app.committeeChair();
        app.goTo().logoutPage();
    }

    @Test(enabled = true, priority = 7)
    public void testCommitteeMemberStage() {
        app.goTo().loginPage();
        app.loginAs().committeeMember();
        app.goTo().logoutPage();
    }

    @Test(enabled = false, priority = 8)
    public void testEventDeletion() {
        Events before = app.dataBase().events();
        Events after = app.dataBase().events();

        assertThat(after, equalTo(before));
    }
}