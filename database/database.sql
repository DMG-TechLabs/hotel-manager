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

--Insert Proceedures
CREATE OR REPLACE FUNCTION insert_system_user(
    _username VARCHAR,
    _password VARCHAR,
    _user_type INT,
    _hotel_id INT DEFAULT NULL
) RETURNS VOID AS $$
BEGIN
    BEGIN
        INSERT INTO SYSTEM_USER (username, password, user_type, hotel_id)
        VALUES (_username, _password, _user_type, _hotel_id);
    EXCEPTION WHEN unique_violation THEN
        RAISE EXCEPTION 'Username % already exists', _username;
    WHEN foreign_key_violation THEN
        RAISE EXCEPTION 'Hotel ID % or User Type % does not exist', _hotel_id, _user_type;
    WHEN others THEN
        RAISE EXCEPTION 'An unexpected error occurred while inserting SYSTEM_USER.';
    END;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_customer(
    _first_name VARCHAR,
    _last_name VARCHAR,
    _phone BIGINT,
    _email VARCHAR
) RETURNS VOID AS $$
BEGIN
    BEGIN
        INSERT INTO CUSTOMER (first_name, last_name, phone, email)
        VALUES (_first_name, _last_name, _phone, _email);
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'An error occurred while inserting CUSTOMER.';
    END;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_hotel(
    _name VARCHAR,
    _address VARCHAR,
    _phone BIGINT
) RETURNS VOID AS $$
BEGIN
    BEGIN
        INSERT INTO HOTEL (name, address, phone)
        VALUES (_name, _address, _phone);
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'An error occurred while inserting HOTEL.';
    END;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_room(
    _number INT,
    _hotel_id INT,
    _type INT,
    _price NUMERIC(10, 2),
    _floor INT,
    _occupied BOOLEAN DEFAULT FALSE
) RETURNS VOID AS $$
BEGIN
    BEGIN
        INSERT INTO ROOM (number, hotel_id, type, price, floor, occupied)
        VALUES (_number, _hotel_id, _type, _price, _floor, _occupied);
    EXCEPTION WHEN foreign_key_violation THEN
        RAISE EXCEPTION 'Hotel ID % or Room Type % does not exist', _hotel_id, _type;
    WHEN others THEN
        RAISE EXCEPTION 'An error occurred while inserting ROOM.';
    END;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_amenities(
    _hotel_id INT,
    _type VARCHAR
) RETURNS VOID AS $$
BEGIN
    BEGIN
        INSERT INTO AMENITIES (hotel_id, type)
        VALUES (_hotel_id, _type);
    EXCEPTION WHEN foreign_key_violation THEN
        RAISE EXCEPTION 'Hotel ID % does not exist', _hotel_id;
    WHEN others THEN
        RAISE EXCEPTION 'An error occurred while inserting AMENITIES.';
    END;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_reservation(
    _customer_id INT,
    _room_id INT,
    _check_in_date DATE,
    _check_out_date DATE,
    _cost NUMERIC(10, 2),
    _status INT
) RETURNS VOID AS $$
BEGIN
    BEGIN
        INSERT INTO RESERVATION (customer_id, room_id, check_in_date, check_out_date, cost, status)
        VALUES (_customer_id, _room_id, _check_in_date, _check_out_date, _cost, _status);
    EXCEPTION WHEN foreign_key_violation THEN
        RAISE EXCEPTION 'Customer ID % or Room ID % does not exist', _customer_id, _room_id;
    WHEN others THEN
        RAISE EXCEPTION 'An error occurred while inserting RESERVATION.';
    END;
END;
$$ LANGUAGE plpgsql;
