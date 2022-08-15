/* Populate table regions */
INSERT INTO regions (name) VALUES ('Sudamérica');
INSERT INTO regions (name) VALUES ('Centroamérica');
INSERT INTO regions (name) VALUES ('Norteamérica');
INSERT INTO regions (name) VALUES ('Europa');
INSERT INTO regions (name) VALUES ('Asia');
INSERT INTO regions (name) VALUES ('Africa');
INSERT INTO regions (name) VALUES ('Oceanía');
INSERT INTO regions (name) VALUES ('Antartída');

/* Populate table customers */
INSERT INTO customers (firstname, lastname, email, date_of_birth, create_at, region_id) VALUES('Sergio', 'Suaréz', 'srsuarez9@gmail.com', '2022-01-01', now(), 1);
INSERT INTO customers (firstname, lastname, email, date_of_birth, create_at, region_id) VALUES('Mr. John', 'Doe', 'john.doe@gmail.com', '2022-01-02', now(), 2);
INSERT INTO customers (firstname, lastname, email, date_of_birth, create_at, region_id) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2022-01-03', now(), 3);
INSERT INTO customers (firstname, lastname, email, date_of_birth, create_at, region_id) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2022-01-04', now(), 4);
INSERT INTO customers (firstname, lastname, email, date_of_birth, create_at, region_id) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2022-01-05', now(), 5);
INSERT INTO customers (firstname, lastname, email, date_of_birth, create_at, region_id) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2022-01-06', now(), 6);
INSERT INTO customers (firstname, lastname, email, date_of_birth, create_at, region_id) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2022-01-07', now(), 7);
INSERT INTO customers (firstname, lastname, email, date_of_birth, create_at, region_id) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2022-01-08', now(), 8);
INSERT INTO customers (firstname, lastname, email, date_of_birth, create_at, region_id) VALUES('Dr. James', 'Gosling', 'james.gosling@gmail.com', '2022-01-09', now(), 1);
INSERT INTO customers (firstname, lastname, email, date_of_birth, create_at, region_id) VALUES('Magma', 'Lee', 'magma.lee@gmail.com', '2022-01-10', now(), 2);
INSERT INTO customers (firstname, lastname, email, date_of_birth, create_at, region_id) VALUES('Tornado', 'Roe', 'tornado.roe@gmail.com', '2022-01-11', now(), 3);
INSERT INTO customers (firstname, lastname, email, date_of_birth, create_at, region_id) VALUES('Jade', 'Doe', 'jane.doe@gmail.com', '2022-01-12', now(), 4);