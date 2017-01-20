package com.agmednet.roles;

import com.agmednet.appmanager.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pshynin on 1/8/17.
 */
public class Adjudicator extends HelperBase {

    public Adjudicator(WebDriver driver) {
        super(driver);
    }

    public void adjudicate() {
        driver.findElement(By.cssSelector("")).click();
    }
}