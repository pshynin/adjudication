package com.agmednet.judi.appmanager;

import com.agmednet.judi.testrail.internal.TestRailListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Pasha Shynin on 8/12/2016.
 */
@Listeners(TestRailListener.class)
public class TestBase {

    public static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

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
        //    //Good place to record test results
//    Method testMethod = getClass().getMethod(testName);
//    if(testMethod.isAnnotationPresent(TestCase.class))
//    {
//      TestCase testData = testMethod.getAnnotation(TestCase.class);
//      //Do something with testData.testId();
//      System.out.println("Test ID = " + testData.testId());
//    }
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

    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
        }
    }
}

