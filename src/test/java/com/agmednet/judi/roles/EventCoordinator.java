package com.agmednet.judi.roles;

import com.agmednet.judi.appmanager.HelperBase;
import com.agmednet.judi.model.EventData;
import com.agmednet.judi.entity.EventInstanceEntity;
import org.openqa.selenium.WebDriver;

/**
 * Created by pshynin on 1/6/17.
 */
public class EventCoordinator extends HelperBase {

    public EventCoordinator(WebDriver wd) {
        super(wd);
    }

    public void createEvent(EventInstanceEntity eventData) {

    }

    public void initEventCreation(EventData eventData) {
    }
}
