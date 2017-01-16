package com.agmednet.judi.tests;

import com.agmednet.judi.model.EventData;
import com.agmednet.judi.model.Events;
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

public class EventDataTest extends TestBase {

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

    @Test(enabled = true, priority = 1)
    public void testEventCreation() {
//        Events before = app.dataBase().events();
        app.goTo().loginPage();
        app.loginAs().eventCoordinator();
        app.eventCoordinator().createEvent(event);
        app.goTo().logoutPage();
 //       Events after = app.dataBase().events();

 //       assertThat(after, equalTo(before.withCreated(event)));
    }

    @Test(enabled = true, priority = 2)
    public void testUploaderStage() {
        app.goTo().logoutPage();
        app.loginAs().uploader();
        app.goTo().logoutPage();

    }

    @Test(enabled = true, priority = 3)
    public void testReviwerStage() {
        app.goTo().logoutPage();
        app.loginAs().reviewer();
        app.goTo().logoutPage();
    }

    @Test(enabled = true, priority = 4)
    public void testFirstAdjudicatorStage() {
        app.goTo().logoutPage();
        app.loginAs().adjudicatorFirst();
        app.goTo().logoutPage();
    }

    @Test(enabled = true, priority = 5)
    public void testSecondAdjudicatorStage() {
        app.goTo().logoutPage();
        app.loginAs().adjudicatorSecond();
        app.goTo().logoutPage();
    }

    @Test(enabled = false, priority = 6)
    public void testEventDeletion() {
        Events before = app.dataBase().events();
        Events after = app.dataBase().events();

        assertThat(after, equalTo(before));
    }
}