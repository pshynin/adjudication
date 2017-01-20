package com.agmednet.appmanager;

import com.agmednet.model.EventData;
import com.agmednet.model.Events;
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

  public Events events() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<EventData> result = session.createQuery("from EventMetadataEntity").list();
    session.getTransaction().commit();
    session.close();
    return new Events(result);
  }

  public void deleteEvent() {

  }

}
