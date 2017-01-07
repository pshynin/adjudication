package com.agmednet.judi.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Created by Pasha Shynin on 8/14/2016.
 */
public class HelperBase {
  WebDriver wd;

  HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  void  type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  void  attach(By locator, File file) {
    if (file != null) {
        wd.findElement(locator).sendKeys(file.getAbsolutePath());
    }
  }

  void click(By locator) {
    wd.findElement(locator).click();
  }

  void submit(By locator) {
    wd.findElement(locator).submit();
  }

  boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
          return true;
    } catch (NoAlertPresentException ex) {
      return false;
    }
  }
}
