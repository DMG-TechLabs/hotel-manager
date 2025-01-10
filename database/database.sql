--Create tables

CREATE TABLE SYSTEM_USER (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    user_type INT NOT NULL,
    hotel_id INT, -- NULL for system administrators
    FOREIGN KEY (hotel_id) REFERENCES HOTEL (hotel_id)
);

CREATE TABLE CUSTOMER (
    customer_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone BIGINT,
    email VARCHAR(50)
);

CREATE TABLE HOTEL (
    hotel_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(50),
    phone BIGINT
);

CREATE TABLE ROOM (
    room_id SERIAL PRIMARY KEY,
    number INT NOT NULL,
    hotel_id INT NOT NULL,
    type INT NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    floor INT,
    occupied BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (hotel_id) REFERENCES HOTEL (hotel_id)
);

CREATE TABLE AMENITIES (
    hotel_id INT NOT NULL,
    amenity_id SERIAL,
    type VARCHAR(50),
    PRIMARY KEY (hotel_id, amenity_id),
    FOREIGN KEY (hotel_id) REFERENCES HOTEL (hotel_id)
);

CREATE TABLE RESERVATION (
    reservation_id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL,
    room_id INT NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    cost NUMERIC(10, 2) NOT NULL,
    status INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES CUSTOMER (customer_id),
    FOREIGN KEY (room_id) REFERENCES ROOM (room_id)
);
