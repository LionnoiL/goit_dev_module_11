package ua.gaponov.entities;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Data;
import lombok.ToString;
import ua.gaponov.services.ClientCrudService;
import ua.gaponov.services.PlanetCrudService;

@Entity
@Data
public class Ticket {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "from_planet_id", nullable = false)
  private Planet fromPlanet;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "to_planet_id", nullable = false)
  private Planet toPlanet;

  public static boolean validate(Ticket ticket) {
    if (ticket == null || ticket.getClient() == null || ticket.getFromPlanet() == null
        || ticket.getToPlanet() == null) {
      return false;
    }

    Client client = new ClientCrudService().get(ticket.getClient().getId());
    if (client == null) {
      return false;
    }

    Planet fromPlanet = new PlanetCrudService().get(ticket.getFromPlanet().getId());
    if (fromPlanet == null) {
      return false;
    }

    Planet toPlanet = new PlanetCrudService().get(ticket.getToPlanet().getId());
    if (toPlanet == null) {
      return false;
    }

    return true;
  }
}
