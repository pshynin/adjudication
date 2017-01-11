package com.agmednet.judi.appmanager;

import com.agmednet.judi.roles.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

import static jdk.nashorn.internal.objects.NativeJava.type;

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

    private TrialAdmin trialAdmin;
    private EventCoordinator eventCoordinator;
    private Uploader uploader;
    private Reviewer reviewer;
    private AdjudicatorFirst adjudicatorFirst;
    private AdjudicatorSecond adjudicatorSecond;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {

        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (Objects.equals(browser, BrowserType.CHROME)) {
            driver = new ChromeDriver();
        } else if (Objects.equals(browser, BrowserType.FIREFOX)) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(FirefoxDriver.MARIONETTE, false);
            driver = new FirefoxDriver(caps);
        } else if (Objects.equals(browser, BrowserType.IE)) {
            driver = new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(properties.getProperty("web.baseUrl"));
        driver.manage().window().maximize();

        loginHelper = new LoginHelper(driver, properties);

        trialAdmin = new TrialAdmin(driver);
        eventCoordinator = new EventCoordinator(driver);
        uploader = new Uploader(driver);
        reviewer = new Reviewer(driver);
        adjudicatorFirst = new AdjudicatorFirst(driver);
        adjudicatorSecond = new AdjudicatorSecond(driver);
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

    public TrialAdmin trialAdmin() {
        return trialAdmin;
    }

    public EventCoordinator eventCoordinator() {
        return eventCoordinator;
    }

    public Uploader uploader() {
        return uploader;
    }

    public Reviewer reviwer() {
        return reviewer;
    }

    public AdjudicatorFirst adjudicatorFirst() {
        return adjudicatorFirst;
    }

    public AdjudicatorSecond adjudicatorSecond() {
        return adjudicatorSecond;
    }

    public void logout() {
        driver.findElement(By.id("logout")).click();
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    String getProperty(String key) {
        return properties.getProperty(key);
    }
}