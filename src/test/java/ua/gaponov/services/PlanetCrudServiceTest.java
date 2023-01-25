package ua.gaponov.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.gaponov.entities.Planet;

class PlanetCrudServiceTest {

  PlanetCrudService service;

  @BeforeEach
  void setUp() {
    service = new PlanetCrudService();
  }

  @Test
  void create() {
    Planet planet = new Planet();
    planet.setId("MAR");
    planet.setName("Mars");
    service.create(planet);
  }

  @Test
  void get() {
    Planet planet = service.get("MAR");
    System.out.println("planet = " + planet);
  }

  @Test
  void update() {
    Planet planet = service.get("MAR");
    planet.setName("Earth");
    service.update(planet);

    Planet planetUpdated = service.get("MAR");
    System.out.println("planetUpdated = " + planetUpdated);
  }

  @Test
  void delete() {
    Planet planet = service.get("MAR");
    service.delete(planet);
  }
}