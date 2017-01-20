package com.agmednet.testrail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pshynin on 1/18/17.
 */
public class Case {
    public int id;
    public String title;
    @JsonProperty("suite_id")
    public int suiteId;
    @JsonProperty("type_id")
    public int typeId;
    @JsonProperty("milestone_id")
    public int milestoneId;
    @JsonProperty("section_id")
    public int sectionId;
    //following property is not present in TestRail by default
    //we need to add it as a custom field
    @JsonProperty("custom_automation_id")
    public String automationId;
    public String refs;
}
