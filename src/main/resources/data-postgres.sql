
INSERT INTO CENTER (name, city, address, average, description) VALUES ('One', 'City of One', 'One Street', 4.5, 'Abut One');
INSERT INTO CENTER (name, city, address, average, description) VALUES ('Too', 'City of Too', 'Too Street', 4.5, 'Abut Two');
INSERT INTO CENTER (name, city, address, average, description) VALUES ('Tre', 'City of Tee', 'Tre Sucked', 4.5, 'Abut Thy');
INSERT INTO CENTER (name, city, address, average, description) VALUES ('For', 'City of Gee', 'For Struct', 4.5, 'Abut You');
INSERT INTO CENTER (name, city, address, average, description) VALUES ('Fiv', 'City of Zee', 'Fiv Struct', 4.5, 'Abut Nil');

INSERT INTO USERS (username, password, first_name, last_name, address, city, country, phone, jmbg, gender, occupation, employment, role, enabled)
VALUES ('user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'User', 'Nil', 'user@example.com', 'Cloud', 'One', '+1234567890', '1234567890123', 'male', 'unemployed', 'non', 0, true);
INSERT INTO USERS (username, password, first_name, last_name, address, city, country, phone, jmbg, gender, occupation, employment, role, enabled)
VALUES ('admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Admin', 'One', 'admin@example.com', 'About', 'Uns', '+9876543210', '0123456789012', 'other', 'admin', 'some', 1, true);
INSERT INTO USERS (username, password, first_name, last_name, address, city, country, phone, jmbg, gender, occupation, employment, role, enabled)
VALUES ('staff', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Staff', 'Too', 'staff@example.com', 'Cloud', 'Tee', '+1234567899', '1012345678901', 'female', 'staff', 'sad', 2, true);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_STAFF');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 1);
