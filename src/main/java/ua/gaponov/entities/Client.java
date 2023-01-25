package ua.gaponov.entities;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Client {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(nullable = false)
  private long id;

  @Column(length = 300)
  private String name;

  @OneToMany(mappedBy = "client")
  private List<Ticket> tickets;
}
