package ua.gaponov.services;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.gaponov.entities.Client;
import ua.gaponov.entities.Ticket;
import ua.gaponov.utils.HibernateUtil;

public class TicketCrudService {

  public long create(Ticket ticket) {
    if (!Ticket.validate(ticket)) {
      return -1;
    }

    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();
      session.persist(ticket);
      transaction.commit();
      return ticket.getId();
    }
  }

  public Ticket get(long id) {
    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
      Query<Ticket> query = session.createQuery(
          "from Ticket where id = :id",
          Ticket.class
      );
      query.setParameter("id", id);
      return query.stream().findFirst().orElse(null);
    }
  }

  public long update(Ticket ticket) {
    if (!Ticket.validate(ticket)) {
      return -1;
    }

    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession();) {
      Transaction transaction = session.beginTransaction();
      session.merge(ticket);
      transaction.commit();
      return ticket.getId();
    }
  }

  public long delete(Ticket ticket) {
    if (ticket == null) {
      return -1;
    }

    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession();) {
      Transaction transaction = session.beginTransaction();
      session.remove(ticket);
      transaction.commit();
      return ticket.getId();
    }
  }

  public List<Ticket> getAll(){
    List<Ticket> result = new ArrayList<>();

    try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession();) {
      Query<Ticket> query = session.createQuery(
          "from Ticket",
          Ticket.class
      );
      result = query.list();
    }

    return result;
  }
}
