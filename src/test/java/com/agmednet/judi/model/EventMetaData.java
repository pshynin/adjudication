package com.agmednet.judi.model;

/**
 * Created by pshynin on 1/6/17.
 */
public class EventMetaData {
    private String TrialName;

    public String getTrialName() {
        return TrialName;
    }

    public EventMetaData withTrialName(String trialName) {
        this.TrialName = trialName;
        return this;
    }
}
