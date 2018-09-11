INSERT INTO category VALUES (1, 'Jedzenie');
INSERT INTO category VALUES (2, 'Napoje');
INSERT INTO category VALUES (3, 'Ubrania');

INSERT INTO expense(id, name, price, category_id) values (1, 'Mleko', 2.2, 1);
INSERT INTO expense(id, name, price, category_id) values (2, 'Piwo', 3.99, 2);
INSERT INTO expense(id, name, price, category_id) values (3, 'Chleb', 4.2, 1);
INSERT INTO expense(id, name, price, category_id) values (4, 'Spodnie', 59, 3);

INSERT INTO location(id, name, latitude, longitude, zoom) VALUES (1, 'Wrocław', 51.12435564007667, 17.026131749153137, 7.0);
INSERT INTO location(id, name, latitude, longitude, zoom) VALUES (2, 'Warszawa', 52.20815544760742, 21.00586272776127, 7.0);
INSERT INTO location(id, name, latitude, longitude, zoom) VALUES (3, 'Poznań', 52.39580078998643, 16.912937834858894, 7.0);
INSERT INTO location(id, name, latitude, longitude, zoom) VALUES (4, 'Gdańsk', 54.35275988082065, 18.660413436591625, 7.0);
INSERT INTO location(id, name, latitude, longitude, zoom)  VALUES (5, 'Katowice', 50.248745563561215, 19.026563800871372, 7.0);