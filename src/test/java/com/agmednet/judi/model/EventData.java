package com.agmednet.judi.model;

import java.util.List;

/**
 * Created by pshynin on 1/11/17.
 */
public class EventData {
    private int id = Integer.MAX_VALUE;
    private String Trial;
    private String Site;
    private String Subject;
    private String Eventtype;
    private String Eventterm;
    private String Eid;
    private String Onsetdate;
    private String Delete;
    private String Testdate;

    public int getId() {
        return id;
    }

    public String getTrial() {
        return Trial;
    }

    public String getSite() {
        return Site;
    }

    public String getSubject() {
        return Subject;
    }

    public String getEventtype() {
        return Eventtype;
    }

    public String getEventterm() {
        return Eventterm;
    }

    public String getEid() {
        return Eid;
    }

    public String getOnsetdate() {
        return Onsetdate;
    }

    public String getDelete() {
        return Delete;
    }

    public String getTestdate() {
        return Testdate;
    }

    public EventData withId(int id) {
        this.id = id;
        return this;
    }

    public EventData withTrial(String trial) {
        this.Trial = trial;
        return this;
    }

    public EventData withSite(String site) {
        this.Site = site;
        return this;
    }

    public EventData withSubject(String subject) {
        this.Subject = subject;
        return this;
    }

    public EventData withEventtype(String eventtype) {
        this.Eventtype = eventtype;
        return this;
    }

    public EventData withEventterm(String eventterm) {
        this.Eventterm = eventterm;
        return this;
    }

    public EventData withEid(String eid) {
        this.Eid = eid;
        return this;
    }

    public EventData withOnsetdate(String onsetdate) {
        this.Onsetdate = onsetdate;
        return this;
    }

    public EventData withDelete(String delete) {
        this.Delete = delete;
        return this;
    }

    public EventData withTestdate(String testdate) {
        this.Testdate = testdate;
        return this;
    }
}
