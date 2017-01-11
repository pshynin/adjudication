package com.agmednet.judi.tests;

import com.agmednet.judi.model.EventData;
import com.agmednet.judi.model.SiteData;
import com.agmednet.judi.model.TrialData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventTest extends TestBase {
    private static final String TRIAL = "CREDO";

    @DataProvider
    public Iterator<Object[]> validEvent() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/Judi_Demo.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new EventData().withTrialName(split[2])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @Test (dataProvider = "validEvent")
    public void testEventCreation(EventData eventData) {
        //    Sites before = app.db().sites();
     //   app.login("Pavlorialadmin1", "Pavlorialadmin1");
        app.loginAs().eventCoordinator();
        app.eventCoordinator().initTrialCreation(new TrialData().withTrial(TRIAL));
        app.eventCoordinator().createEvent(eventData);
        app.logout();
        //    Sites after = app.db().sites();
        //    assertThat(after, equalTo(before.withAdded(site.withId(after.stream()
//            .mapToInt((g) -> g.getId()).max().getAsInt()))));
        verifyEventInUI();
    }

    @Test
    public void testUploaderStage() {
        app.loginAs().uploader();
        app.uploader();
        app.logout();
    }

    @Test
    public void testReviwerStage() {
        app.loginAs().reviewer();
        app.reviwer();
        app.logout();
    }

    @Test
    public void testFirstAdjudicatorStage() {
        app.loginAs().adjudicatorFirst();
        app.adjudicatorFirst();
        app.logout();
    }

    @Test
    public void testSecondAdjudicatorStage() {
        app.loginAs().adjudicatorSecond();
        app.adjudicatorSecond();
        app.logout();
    }

    @Test
    public void testEventDeletion() {
    }
}