package ua.gaponov.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.gaponov.entities.Client;
import ua.gaponov.entities.Planet;
import ua.gaponov.entities.Ticket;

class TicketCrudServiceTest {

  TicketCrudService ticketCrudService;
  ClientCrudService clientCrudService;
  PlanetCrudService planetCrudService;

  Client getAnyClient() {
    return clientCrudService.getAll().stream().findAny().orElse(null);
  }

  Planet getAnyPlanet() {
    return planetCrudService.getAll().stream().findAny().orElse(null);
  }

  Ticket getAnyTicket() {
    return ticketCrudService.getAll().stream().findAny().orElse(null);
  }

  @BeforeEach
  void setUp() {
    ticketCrudService = new TicketCrudService();
    clientCrudService = new ClientCrudService();
    planetCrudService = new PlanetCrudService();
  }

  @Test
  void create() {
    Ticket ticket = new Ticket();
    ticket.setClient(getAnyClient());
    ticket.setFromPlanet(getAnyPlanet());
    ticket.setToPlanet(getAnyPlanet());

    ticketCrudService.create(ticket);

    Ticket createdTicked = ticketCrudService.get(ticket.getId());
    System.out.println("createdTicked = " + createdTicked);
  }

  @Test
  void createTestNullClient() {
    Ticket ticket = new Ticket();
    ticket.setFromPlanet(getAnyPlanet());
    ticket.setToPlanet(getAnyPlanet());

    Assertions.assertEquals(-1, ticketCrudService.create(ticket));
  }

  @Test
  void createTestNullFromPlanet() {
    Ticket ticket = new Ticket();
    ticket.setClient(getAnyClient());
    ticket.setToPlanet(getAnyPlanet());

    Assertions.assertEquals(-1, ticketCrudService.create(ticket));
  }

  @Test
  void createTestNullToPlanet() {
    Ticket ticket = new Ticket();
    ticket.setClient(getAnyClient());
    ticket.setFromPlanet(getAnyPlanet());

    Assertions.assertEquals(-1, ticketCrudService.create(ticket));
  }

  @Test
  void get() {
    Ticket ticket = ticketCrudService.get(getAnyTicket().getId());
    System.out.println("ticket = " + ticket);
  }

  @Test
  void getAll() {
    System.out.println("service.getAll() = " + ticketCrudService.getAll());
  }

  @Test
  void update() {
    Ticket ticket = getAnyTicket();
    System.out.println("ticket = " + ticket);
    long id = ticket.getId();

    Ticket ticketUpdated = ticketCrudService.get(id);
    ticket.setClient(getAnyClient());
    ticket.setToPlanet(getAnyPlanet());
    ticketCrudService.update(ticketUpdated);
  }

  @Test
  void delete() {
    Ticket ticket = new TicketCrudService().getAll().stream().findFirst().orElse(null);
    System.out.println("ticket = " + ticket);
    long id = ticket.getId();
    ticketCrudService.delete(ticket);
    Assertions.assertEquals(null, ticketCrudService.get(id));
  }
}