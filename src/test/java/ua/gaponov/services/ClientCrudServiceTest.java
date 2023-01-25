package ua.gaponov.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.gaponov.entities.Client;

class ClientCrudServiceTest {

  ClientCrudService service;

  @BeforeEach
  void setUp() {
    service = new ClientCrudService();
  }

  @Test
  void create() {
    Client client = new Client();
    client.setName("Andriy");
    service.create(client);
  }

  @Test
  void get() {
    Client client = service.get(2);
    System.out.println("client = " + client);
  }

  @Test
  void update() {
    Client client = service.get(2);
    client.setName("Keith Nelsonuk");
    service.update(client);
    Client getClient = service.get(2);
    System.out.println("getClient = " + getClient);
  }

  @Test
  void delete() {
    Client client = service.get(11);
    service.delete(client);
  }
}