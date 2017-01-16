package com.agmednet.judi.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

/**
 * Created by Pasha Shynin on 8/14/2016.
 */
public class HelperBase {
  public WebDriver driver;

  public HelperBase(WebDriver driver) {
    this.driver = driver;
  }

  void click(By locator) {
    WebElement element = driver.findElement(locator);
    if (element.isDisplayed()) {
      element.click();
    }
  }

  public void  type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = driver.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
      }
    }
  }

  void  attach(By locator, File file) {
    if (file != null) {
        driver.findElement(locator).sendKeys(file.getAbsolutePath());
    }
  }

  void submit(By locator) {
    driver.findElement(locator).submit();
  }

  public boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
          return true;
    } catch (NoAlertPresentException ex) {
      return false;
    }
  }
}