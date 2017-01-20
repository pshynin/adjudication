package com.agmednet.testrail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pshynin on 1/18/17.
 */
public class Test {

    public int id;
    @JsonProperty("case_id")
    public int caseId;
    @JsonProperty("statusId")
    public int status_id;
    @JsonProperty("run_id")
    public int runId;
    //following property is not present in TestRail by default
    //we need to add it as a custom field
    @JsonProperty("custom_automation_id")
    public String automationId;

}