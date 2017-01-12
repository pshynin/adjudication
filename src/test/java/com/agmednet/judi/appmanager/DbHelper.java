package com.agmednet.judi.appmanager;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
//  public SiteData sites() {
//    Session session = sessionFactory.openSession();
//    session.beginTransaction();
//    List<SiteData> result = session.createQuery("from SiteData").list();
//    session.getTransaction().commit();
//    session.close();
//    return new SiteData(result);
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
