package com.agmednet.judi.tests;

import com.agmednet.judi.model.TrialSiteData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Pasha Shynin on 8/30/2016.
 */
public class HbConnectionTest {

  private SessionFactory sessionFactory;

  @BeforeClass
  protected void setUp() throws Exception {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }

  @Test
  public void testHbConnection() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<TrialSiteData> result = session.createQuery("from").list();
    for (TrialSiteData sites : result) {
      System.out.println(sites);
    }
    session.getTransaction().commit();
    session.close();
  }
}