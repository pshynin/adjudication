package com.agmednet.judi.appmanager;

import com.agmednet.judi.model.TrialSiteData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Created by Pasha Shynin on 8/30/2016.
 */
public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }
//
//  public TrialSites sites() {
//    Session session = sessionFactory.openSession();
//    session.beginTransaction();
//    List<TrialSiteData> result = session.createQuery("from TrialSiteData").list();
//    session.getTransaction().commit();
//    session.close();
//    return new TrialSites(result);
//  }
//
//  public UserAccounts accounts() {
//    Session session = sessionFactory.openSession();
//    session.beginTransaction();
//    List<UserAccountData> result = session.createQuery("from UserAccountData").list();
//    session.getTransaction().commit();
//    session.close();
//    return new UserAccounts((UserAccounts) result);
//  }
}
