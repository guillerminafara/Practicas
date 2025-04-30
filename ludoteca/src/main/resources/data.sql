INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

INSERT INTO customer(name) VALUES('Paquito');
INSERT INTO customer(name) VALUES('Tito');
INSERT INTO customer(name) VALUES('Tuqui');
INSERT INTO customer(name) VALUES('Rolando');
INSERT INTO customer(name) VALUES('Carlos');
INSERT INTO customer(name) VALUES('Pepe');
INSERT INTO customer(name) VALUES('Ernesto');
INSERT INTO customer(name) VALUES('María');
INSERT INTO customer(name) VALUES('Carmen');


INSERT INTO rent(customer_id, game_id, initial_date, final_date, enable)VALUES(1, 5, '2025-04-23', '2025-04-27',1);
INSERT INTO rent(customer_id, game_id, initial_date, final_date, enable)VALUES(4, 1, '2025-04-20', '2025-04-27',1);
INSERT INTO rent(customer_id, game_id, initial_date, final_date, enable)VALUES(8, 4, '2025-04-21', '2025-04-30',1);
INSERT INTO rent(customer_id, game_id, initial_date, final_date, enable)VALUES(3, 2, '2025-04-30', '2025-05-13',1);
INSERT INTO rent(customer_id, game_id, initial_date, final_date, enable)VALUES(2, 6, '2025-04-28', '2025-05-04',1);
INSERT INTO rent(customer_id, game_id, initial_date, final_date, enable)VALUES(8, 3, '2025-05-02', '2025-05-10',1);
INSERT INTO rent(customer_id, game_id, initial_date, final_date, enable)VALUES(1, 5, '2025-04-23', '2025-05-07',1);
INSERT INTO rent(customer_id, game_id, initial_date, final_date, enable)VALUES(6, 3, '2025-04-25', '2025-05-10',1);
INSERT INTO rent(customer_id, game_id, initial_date, final_date, enable)VALUES(8, 4, '2025-04-21', '2025-04-30',1);