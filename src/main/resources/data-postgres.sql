
INSERT INTO USERS (username, password, first_name, last_name, address, city, country, phone, jmbg, gender, role, enabled) VALUES ('user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'User', 'Nil', 'user@example.com', 'Cloud', 'One', '+1234567890', '1234567890123', 'male', 0, true);
INSERT INTO USERS (username, password, first_name, last_name, address, city, country, phone, jmbg, gender, role, enabled) VALUES ('admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Admin', 'One', 'admin@example.com', 'About', 'Uns', '+9876543210', '0123456789012', 'other', 1, true);
INSERT INTO USERS (username, password, first_name, last_name, address, city, country, phone, jmbg, gender, role, enabled) VALUES ('staff', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Staff', 'Too', 'staff@example.com', 'Cloud', 'Tee', '+1234567899', '1012345678901', 'female', 2, true);

INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_STAFF');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 1);
