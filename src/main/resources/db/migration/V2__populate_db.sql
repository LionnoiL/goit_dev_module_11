INSERT INTO Client (name) VALUES
  ('Eden Robbins'),
  ('Keith Nelson'),
  ('Leonard Gutierrez'),
  ('Theodora Valdez'),
  ('Morton Guzman'),
  ('Marlon Byrd'),
  ('Ford Taylor'),
  ('Elaine Burke'),
  ('Rosa Malcom'),
  ('Violet Presley')
;

INSERT INTO Planet (id, name) VALUES
    ('REE', 'Reeclen'),
    ('INE1', 'Ineran On'),
    ('PALAD', 'Palidic'),
    ('VENE', 'Vene'),
    ('KHISH', 'Khishl-ini')
;

INSERT INTO Ticket (client_id, from_planet_id, to_planet_id) VALUES
    (1, 'REE', 'INE1'),
    (2, 'REE', 'PALAD'),
    (3, 'INE1', 'VENE'),
    (4, 'VENE', 'REE'),
    (5, 'KHISH', 'INE1'),
    (6, 'INE1', 'KHISH'),
    (7, 'PALAD', 'REE'),
    (8, 'KHISH', 'REE'),
    (9, 'KHISH', 'REE'),
    (10, 'VENE', 'PALAD')
;

