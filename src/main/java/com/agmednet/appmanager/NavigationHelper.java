package com.agmednet.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pshynin on 1/12/17.
 */
public class NavigationHelper extends HelperBase {
    private static final String URL = "https://test05.agmednet.net";
    private static final String LOGIN = "//div[@id='userwidget']//a[.='Login']";
    private static final String LOGOUT = "//*[@href='/logout.seam' and contains(text(), 'Logout')]";


    NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void loginPage() {
        driver.get(URL);
 //       driver.findElement(By.xpath(LOGIN)).click();
    }

    public void logoutPage() {
        driver.findElement(By.xpath(LOGOUT)).click();
    }
}
