package com.agmednet.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

/**
 * Created by pshynin on 1/8/17.
 */
public class LoginHelper extends HelperBase {
    private static final String USERNAME = "[id=IDToken1]";
    private static final String PASSWORD = "[id=IDToken2]";
    private static final String BUTTON = "[class=button_cap]";
    private static final String ADJUDICATION = "[id=j_id125]";

    private Properties properties;

    LoginHelper(WebDriver driver, Properties properties) {
        super(driver);
        this.properties = properties;
    }

    public void trialAdmin() {
        login(properties.getProperty("trialadmin.username"), properties.getProperty("trialadmin.password"));
    //    redirectToJudi();
    }

    public void eventCoordinator() {
        login(properties.getProperty("eventCoordinator.username"), properties.getProperty("eventCoordinator.password"));
    //    redirectToJudi();
    }

    public void uploader() {
        login(properties.getProperty("uploader.username"), properties.getProperty("uploader.password"));
    //    redirectToJudi();
    }

    public void reviewer() {
        login(properties.getProperty("reviewer.username"), properties.getProperty("reviewer.password"));
    //    redirectToJudi();
    }

    public void adjudicatorFirst() {
        login(properties.getProperty("adjudicatorFirst.username"), properties.getProperty( "adjudicatorFirst.password"));
    //    redirectToJudi();
    }

    public void adjudicatorSecond() {
        login(properties.getProperty("adjudicatorSecond.username"), properties.getProperty("adjudicatorSecond.password"));
    //    redirectToJudi();
    }

    public void committeeChair() {
        login(properties.getProperty("committeeChair.username"), properties.getProperty("committeeChair.password"));
    //    redirectToJudi();
    }

    public void committeeMember() {
        login(properties.getProperty("committeeMember.username"), properties.getProperty("committeeMember.password"));
    //    redirectToJudi();
    }

    private void login(String username, String password) {
        type(By.cssSelector(USERNAME), username);
        type(By.cssSelector(PASSWORD), password);
        click(By.cssSelector(BUTTON));
    }

    private void redirectToJudi() {
        click(By.cssSelector(ADJUDICATION));
    }
}
