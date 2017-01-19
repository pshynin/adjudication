package com.agmednet.judi.testrail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by pshynin on 1/18/17.
 */
public class PlanEntry {

    public String id;
    @JsonProperty("suite_id")
    public int suiteId;
    public String name;
    public List<Run> runs;
    @JsonProperty("assignedto_id")
    public Integer assignedTo;
    @JsonProperty("include_all")
    public boolean includeAll;
    @JsonProperty("case_ids")
    public List<Integer> caseIds;
    @JsonProperty("config_ids")
    public List<Integer> configIds;

}