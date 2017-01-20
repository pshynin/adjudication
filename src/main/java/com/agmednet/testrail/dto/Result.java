package com.agmednet.testrail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pshynin on 1/18/17.
 */
public class Result {

    public int id;
    @JsonProperty("test_id")
    public int testId;
    @JsonProperty("status_id")
    public Integer statusId;
    public String comment;

}