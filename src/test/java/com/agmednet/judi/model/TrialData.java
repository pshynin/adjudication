package com.agmednet.judi.model;

/**
 * Created by Pasha Shynin on 8/17/2016.
 */
public class TrialData {
  private String trial;

  public TrialData withTrial(String trial) {
    this.trial = trial;
    return this;
  }

  public String getTrial() {
    return trial;
  }
}
