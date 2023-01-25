package ua.gaponov.services;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.gaponov.entities.Client;
import ua.gaponov.entities.Ticket;
import ua.gaponov.utils.HibernateUtil;

public class ClientCrudService {

  public long create(Client client) {
    if (client == null) {
      return -1;
    }

    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      session.persist(client);
      transaction.commit();
      return client.getId();
    }
  }

  public Client get(long id) {
    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
      Query<Client> query = session.createQuery(
          "from Client where id = :id",
          Client.class
      );
      query.setParameter("id", id);
      return query.stream().findFirst().orElse(null);
    }
  }

  public long update(Client client) {
    if (client == null) {
      return -1;
    }

    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession();) {
      Transaction transaction = session.beginTransaction();
      session.merge(client);
      transaction.commit();
      return client.getId();
    }
  }

  public long delete(Client client) {
    if (client == null) {
      return -1;
    }

    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession();) {
      Transaction transaction = session.beginTransaction();
      session.remove(client);
      transaction.commit();
      return client.getId();
    }
  }

  public List<Client> getAll(){
    List<Client> result = new ArrayList<>();

    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession();) {
      Query<Client> query = session.createQuery(
          "from Client",
          Client.class
      );
      result = query.list();
    }

    return result;
  }

  public List<Ticket> getTickets(Client client) {
    List<Ticket> result = new ArrayList<>();

    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession();) {
      Query<Ticket> query = session.createQuery(
          "from Ticket where client_id = :client",
          Ticket.class
      );
      query.setParameter("client", client);
      result = query.list();
    }

    return result;
  }
}
