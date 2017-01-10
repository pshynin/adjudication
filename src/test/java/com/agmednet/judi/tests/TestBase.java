package com.agmednet.judi.tests;

import com.agmednet.judi.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Pasha Shynin on 8/12/2016.
 */
public class TestBase {

  static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  private Logger logger = LoggerFactory.getLogger(TestBase.class);

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + " With parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m, Object[] p) {
    logger.info("Stop test " + m.getName() + " With parameters " + Arrays.asList(p));
  }

  public void verifyEventInUI() {
    if (Boolean.getBoolean("verifyUI")) {
//      Events dbEvents = app.db().contacts();
//      Events uiEvents = app.event().all();
//      assertThat(uiEvents, equalTo(dbEvents.stream()
//              .map((g) -> new EventData().withId(g.getId()).withName(g.getFirstname()))
//              .collect(Collectors.toSet())));
    }
  }
}

