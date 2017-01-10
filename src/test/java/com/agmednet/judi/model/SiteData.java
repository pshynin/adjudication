package com.agmednet.judi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.File;

/**
 * Created by Pasha Shynin on 8/17/2016.
 */

@Entity
@Table(name = "trialsite")
public class SiteData extends SubjectData {
//  @Id
//  @Column(name = "id")
//  private long id;

  @Column(name = "clinicaltrialsiteid")
  private String siteId;

  @Column(name = "clinicaltrialsitename")
  private String siteDescription;

  @Column(name = "country_id")
  @Transient
  private String country;

  @Transient
  private File file;

//  public long getId() {
//    return id;
//  }

  public String getSiteId() {
    return siteId;
  }

  public String getSiteDescription() {
    return siteDescription;
  }

  public String getCountry() {
    return country;
  }

  public File getFile() {
    return file;
  }

//  public SiteData withId(long id) {
//    this.id = id;
//    return this;
//  }

  public SiteData withSiteId(String siteId) {
    this.siteId = siteId;
    return this;
  }

  public SiteData withSiteDescription(String siteDescription) {
    this.siteDescription = siteDescription;
    return this;
  }

  public SiteData withCountry(String country) {
    this.country = country;
    return this;
  }

  public SiteData withFile(File file) {
    this.file = file;
    return this;
  }

  public String toString() {
    return siteDescription;
  }
}
