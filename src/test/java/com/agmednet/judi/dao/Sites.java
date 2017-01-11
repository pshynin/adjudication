package com.agmednet.judi.dao;

import com.agmednet.judi.model.SiteData;
import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pasha Shynin on 8/18/2016.
 */
public class Sites extends ForwardingSet<SiteData> {

  private HashSet<SiteData> delegate;

  public Sites(Sites sites) {
    this.delegate = new HashSet<SiteData>(sites.delegate);
  }

  public Sites(Collection<SiteData> sites) {
    this.delegate = new HashSet<SiteData>(sites);
  }

  public Sites() {
    this.delegate = new HashSet<SiteData>();
  }

  @Override
  protected Set<SiteData> delegate() {
    return null;
  }


  public Sites withAdded(SiteData site) {
    Sites sites = new Sites(this);
    sites.add(site);
    return sites;
  }

  public Sites without(SiteData site) {
    Sites sites = new Sites(this);
    sites.remove(site);
    return sites;
  }
}