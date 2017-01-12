package com.agmednet.judi.dao;

import com.agmednet.judi.entity.TrialSiteEntity;
import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pasha Shynin on 8/18/2016.
 */
public class Site extends ForwardingSet<TrialSiteEntity> {

  private HashSet<TrialSiteEntity> delegate;

  public Site(Site site) {
    this.delegate = new HashSet<TrialSiteEntity>(site.delegate);
  }

  public Site(Collection<TrialSiteEntity> sites) {
    this.delegate = new HashSet<TrialSiteEntity>(sites);
  }

  public Site() {
    this.delegate = new HashSet<TrialSiteEntity>();
  }

  @Override
  protected Set<TrialSiteEntity> delegate() {
    return null;
  }


  public Site withAdded(TrialSiteEntity site) {
    Site sites = new Site(this);
    sites.add(site);
    return sites;
  }

  public Site without(TrialSiteEntity site) {
    Site sites = new Site(this);
    sites.remove(site);
    return sites;
  }
}