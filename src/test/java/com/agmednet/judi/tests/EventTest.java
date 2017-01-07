package com.agmednet.judi.tests;

import com.agmednet.judi.model.TrialSiteData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class EventTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validSite() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/TrialSiteData.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(",");
      list.add(new Object[]{new TrialSiteData().withSiteId(split[0]).withSiteDescription(split[1]).withCountry(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test
  public void testEventCreation() {
    app.eventCoordinator();
  }

  @Test
  public void testUploaderStage() {

  }

  @Test
  public void testReviwerStage() {

  }

  @Test
  public void testFirstAdjudicatorStage() {

  }

  @Test
  public void testSecondAdjudicatorStage() {

  }

  @Test
  public void testEventDeletion() {

  }


  @Test(dataProvider = "validSite")
  public void testSingleSiteCreation(TrialSiteData site) {
//    TrialSites before = app.db().sites();
// //   TrialSiteData createdSite = before.iterator().next();
//    app.loginAs().trialAdmin(new LoginData("operations", "Sail2Fast!"));
//    app.goTo().trialAdministrationPage();
//    app.trialAdministration().initSiteCreation(new TrialData().withTrial("AgentTest Trial"));
////    app.sites().viewSites();
////    int before = app.sites().count();
//    app.sites().createSingleSite(site);
//    app.sites().viewSites();
//    int after = app.sites().count();
//    Assert.assertEquals(after, before + 1);
//    assertThat(app.site().count(), equalTo(before.size() + 1));
//    TrialSites after = app.site().all();
//    assertThat(after, equalTo(before.withAdded(site.withId(after.stream()
//            .mapToInt((g) -> g.getId()).max().getAsInt()))));
    app.logout();
//    TrialSites after = app.db().sites();
//    assertThat(after, equalTo(before.without(createdSite).withAdded(site)));
  }
}