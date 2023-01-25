CREATE TABLE Client (
   id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(300),
   CONSTRAINT client_check_name CHECK((CHAR_LENGTH("NAME") >= 3) AND (CHAR_LENGTH("NAME") <= 300))
);

CREATE TABLE Planet (
   id VARCHAR(50) NOT NULL PRIMARY KEY,
   name VARCHAR(500) NOT NULL,
   CONSTRAINT planet_check_name CHECK((CHAR_LENGTH("NAME") >= 1) AND (CHAR_LENGTH("NAME") <= 500)),
   CONSTRAINT planet_check_id CHECK(regexp_like(id, '\b[A-Z0-9]+\b'))
);

CREATE TABLE Ticket (
   id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   client_id BIGINT NOT NULL,
   from_planet_id VARCHAR(50) NOT NULL,
   to_planet_id VARCHAR(50) NOT NULL,
   CONSTRAINT ticket_fk_client FOREIGN KEY (client_id) REFERENCES Client(id),
   CONSTRAINT ticket_fk_from_planet FOREIGN KEY (from_planet_id) REFERENCES Planet(id),
   CONSTRAINT ticket_fk_to_planet FOREIGN KEY (to_planet_id) REFERENCES Planet(id)
);