package com.agmednet.roles;

import com.agmednet.appmanager.HelperBase;
import com.agmednet.model.EventData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pshynin on 1/6/17.
 */
public class EventCoordinator extends HelperBase {

    public EventCoordinator(WebDriver wd) {
        super(wd);
    }

    public void createEvent(EventData eventData) {
        type(By.name("firstname"), eventData.getTrial());
        type(By.name("lastname"), eventData.getSite());
        type(By.name("address"), eventData.getSubject());
        type(By.name("home"), eventData.getEventtype());
        type(By.name("mobile"), eventData.getEventterm());
        type(By.name("work"), eventData.getEid());
        type(By.name("email"), eventData.getOnsetdate());
        type(By.name("email2"), eventData.getDelete());
    }

    public void initEventCreation(EventData eventData) {
    }
}
