package com.agmednet.testrail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by pshynin on 1/18/17.
 */
public class Plan {

    public int id;
    public String name;
    @JsonProperty("milestone_id")
    public Integer milestoneId;
    public List<PlanEntry> entries;

}
