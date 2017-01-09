package com.agmednet.judi.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pasha Shynin on 8/15/2016.
 */
//public class UserAccounts extends ForwardingSet<UserAccountData>{
//
//  private Set<UserAccountData> delegate;
//
//  public UserAccounts(UserAccounts userAccounts) {
//    this.delegate = new HashSet<UserAccountData>(userAccounts.delegate);
//  }
//
//  public UserAccounts() {
//    this.delegate = new HashSet<UserAccountData>();
//  }
//
//
//  public UserAccounts withAdded(UserAccountData account) {
//    UserAccounts userAccounts = new UserAccounts(this);
//    userAccounts.add(account);
//    return userAccounts;
//  }
//
//  public UserAccounts without(UserAccountData account) {
//    UserAccounts userAccounts = new UserAccounts(this);
//    userAccounts.remove(account);
//    return userAccounts;
//  }
//
//  @Override
//  protected Set<UserAccountData> delegate() {
//    return delegate;
//  }
//}
