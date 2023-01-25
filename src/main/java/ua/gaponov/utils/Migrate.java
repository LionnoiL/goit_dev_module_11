package ua.gaponov.utils;

import org.flywaydb.core.Flyway;

public class Migrate {

  public static void migrate(String url) {
    Flyway flyway = Flyway
        .configure()
        .dataSource(url, "", "")
        .load();
    flyway.migrate();
  }
}
