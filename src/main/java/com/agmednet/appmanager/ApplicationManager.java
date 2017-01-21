package com.agmednet.appmanager;

import com.agmednet.roles.*;
import com.agmednet.testrail.internal.TestRailListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pasha Shynin on 8/12/2016.
 */
public class ApplicationManager {
    private static final int SLEEP_PERIOD = 1000;
    private static final int TIMEOUT = 30000;

    private final Properties properties;

    private WebDriver driver;
    private WebDriverWait wait;

    private String browser;

    private LoginHelper loginHelper;
    private NavigationHelper navigationHelper;
    private DbHelper dbHelper;
    private TestRailListener testRailListener;

    private TrialAdmin trialAdmin;
    private EventCoordinator eventCoordinator;
    private Uploader uploader;
    private Reviewer reviewer;
    private Adjudicator adjudicator;
    private CommitteeChair committeeChair;
    private CommitteeMember committeeMember;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {

        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/main/resources/%s.properties", target))));

        if (Objects.equals(browser, BrowserType.CHROME)) {
            driver = new ChromeDriver();

        } else if (Objects.equals(browser, BrowserType.FIREFOX)) {
            DesiredCapabilities caps = new DesiredCapabilities();
            FirefoxBinary bin = new FirefoxBinary(new File("/Applications/FirefoxNightly.app"));
            driver = new FirefoxDriver(bin, new FirefoxProfile(), caps);

        } else if (Objects.equals(browser, BrowserType.IE)) {
            driver = new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(properties.getProperty("web.baseUrl"));
        driver.manage().window().maximize();

        loginHelper = new LoginHelper(driver, properties);
        navigationHelper = new NavigationHelper(driver);
//        dbHelper = new DbHelper();
        testRailListener = new TestRailListener();

        trialAdmin = new TrialAdmin(driver);
        eventCoordinator = new EventCoordinator(driver);
        uploader = new Uploader(driver);
        reviewer = new Reviewer(driver);
        adjudicator = new Adjudicator(driver);
        committeeChair = new CommitteeChair(driver);
        committeeMember = new CommitteeMember(driver);
    }

    public void stop() {
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    driver.quit();
                    driver = null;
                }));
    }

    public LoginHelper loginAs() {
        return loginHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public DbHelper dataBase() {
        return dbHelper;
    }

    public TrialAdmin trialAdmin() {
        return trialAdmin;
    }

    public EventCoordinator eventCoordinator() {
        return eventCoordinator;
    }

    public Uploader uploader() {
        return uploader;
    }

    public Reviewer reviewer() {
        return reviewer;
    }

    public Adjudicator adjudicator() {
        return adjudicator;
    }

    public CommitteeChair committeeChair() {
        return committeeChair;
    }

    public CommitteeMember committeeMember() {
        return committeeMember;
    }

    public HttpHelper newSession() {
        return new HttpHelper(this);
    }

    String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void takeScreenshot() {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screen = new File("screen- " + System.currentTimeMillis() + " .png");
    }
}