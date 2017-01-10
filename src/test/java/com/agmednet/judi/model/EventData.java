package com.agmednet.judi.model;

/**
 * Created by pshynin on 1/6/17.
 */
public class EventData {
    private String TrialName;

    public String getTrialName() {
        return TrialName;
    }

    public EventData withTrialName(String trialName) {
        this.TrialName = trialName;
        return this;
    }


}
