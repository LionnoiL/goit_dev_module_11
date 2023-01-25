package ua.gaponov.utils;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.gaponov.entities.Client;
import ua.gaponov.entities.Planet;
import ua.gaponov.entities.Ticket;

public class HibernateUtil {

  private static final HibernateUtil INSTANCE;

  static {
    INSTANCE = new HibernateUtil();
  }

  @Getter
  private SessionFactory sessionFactory;

  private HibernateUtil() {
    sessionFactory = new Configuration()
        .addAnnotatedClass(Client.class)
        .addAnnotatedClass(Planet.class)
        .addAnnotatedClass(Ticket.class)
        .buildSessionFactory();

    Migrate.migrate((String) sessionFactory.getProperties().get("hibernate.connection.url"));
  }

  public static HibernateUtil getInstance() {
    return INSTANCE;
  }

  public void close() {
    sessionFactory.close();
  }
}
