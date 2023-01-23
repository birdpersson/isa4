
INSERT INTO CENTER (name, city, address, rating, description, opens, closes) VALUES ('One', 'City of One', 'One Street', 3.15, 'Abut One', '09:57:58', '21:58:58');
INSERT INTO CENTER (name, city, address, rating, description, opens, closes) VALUES ('Too', 'City of Too', 'Too Street', 4.23, 'Abut Two', '09:57:58', '21:58:58');
INSERT INTO CENTER (name, city, address, rating, description, opens, closes) VALUES ('Tre', 'City of Tee', 'Tre Sucked', 2.45, 'Abut Thy', '09:57:58', '21:58:58');
INSERT INTO CENTER (name, city, address, rating, description, opens, closes) VALUES ('For', 'City of Gee', 'For Struct', 4.87, 'Abut You', '09:57:58', '21:58:58');
INSERT INTO CENTER (name, city, address, rating, description, opens, closes) VALUES ('Fiv', 'City of Zee', 'Fiv Struct', 1.11, 'Abut Nil', '09:57:58', '21:58:58');

INSERT INTO USERS (username, password, first_name, last_name, address, city, country, phone, jmbg, gender, occupation, employment, role, enabled)
VALUES ('user@email.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'User', 'Nil', 'user@example.com', 'Cloud', 'One', '+1234567890', '1234567890123', 'male', 'unemployed', 'non', 0, true);
INSERT INTO USERS (username, password, first_name, last_name, address, city, country, phone, jmbg, gender, occupation, employment, role, enabled)
VALUES ('admin@email.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Admin', 'One', 'admin@example.com', 'About', 'Uns', '+9876543210', '0123456789012', 'other', 'admin', 'some', 1, true);
INSERT INTO USERS (username, password, first_name, last_name, address, city, country, phone, jmbg, gender, occupation, employment, role, enabled)
VALUES ('staff@email.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Staff', 'Too', 'staff@example.com', 'Cloud', 'Tee', '+1234567899', '1012345678901', 'female', 'staff', 'sad', 2, true);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_STAFF');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 1);
