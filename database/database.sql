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


--Update procedures

CREATE OR REPLACE FUNCTION update_system_user(
    _user_id INT,
    _username VARCHAR,
    _password VARCHAR,
    _user_type INT,
    _hotel_id INT DEFAULT NULL
) RETURNS VOID AS $$
BEGIN
    BEGIN
        UPDATE SYSTEM_USER
        SET 
            username = _username,
            password = _password,
            user_type = _user_type,
            hotel_id = _hotel_id
        WHERE user_id = _user_id;

        IF NOT FOUND THEN
            RAISE EXCEPTION 'No SYSTEM_USER found with ID %', _user_id;
        END IF;
    EXCEPTION WHEN unique_violation THEN
        RAISE EXCEPTION 'Username % already exists', _username;
    WHEN foreign_key_violation THEN
        RAISE EXCEPTION 'Hotel ID % or User Type % does not exist', _hotel_id, _user_type;
    WHEN others THEN
        RAISE EXCEPTION 'An unexpected error occurred while updating SYSTEM_USER.';
    END;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION update_customer(
    _customer_id INT,
    _first_name VARCHAR,
    _last_name VARCHAR,
    _phone BIGINT,
    _email VARCHAR
) RETURNS VOID AS $$
BEGIN
    BEGIN
        UPDATE CUSTOMER
        SET 
            first_name = _first_name,
            last_name = _last_name,
            phone = _phone,
            email = _email
        WHERE customer_id = _customer_id;

        IF NOT FOUND THEN
            RAISE EXCEPTION 'No CUSTOMER found with ID %', _customer_id;
        END IF;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'An error occurred while updating CUSTOMER.';
    END;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION update_hotel(
    _hotel_id INT,
    _name VARCHAR,
    _address VARCHAR,
    _phone BIGINT
) RETURNS VOID AS $$
BEGIN
    BEGIN
        UPDATE HOTEL
        SET 
            name = _name,
            address = _address,
            phone = _phone
        WHERE hotel_id = _hotel_id;

        IF NOT FOUND THEN
            RAISE EXCEPTION 'No HOTEL found with ID %', _hotel_id;
        END IF;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'An error occurred while updating HOTEL.';
    END;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION update_room(
    _room_id INT,
    _number INT,
    _hotel_id INT,
    _type INT,
    _price NUMERIC(10, 2),
    _floor INT,
    _occupied BOOLEAN
) RETURNS VOID AS $$
BEGIN
    BEGIN
        UPDATE ROOM
        SET 
            number = _number,
            hotel_id = _hotel_id,
            type = _type,
            price = _price,
            floor = _floor,
            occupied = _occupied
        WHERE room_id = _room_id;

        IF NOT FOUND THEN
            RAISE EXCEPTION 'No ROOM found with ID %', _room_id;
        END IF;
    EXCEPTION WHEN foreign_key_violation THEN
        RAISE EXCEPTION 'Hotel ID % or Room Type % does not exist', _hotel_id, _type;
    WHEN others THEN
        RAISE EXCEPTION 'An error occurred while updating ROOM.';
    END;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION update_amenities(
    _hotel_id INT,
    _amenity_id INT
) RETURNS VOID AS $$
BEGIN
    BEGIN
        UPDATE AMENITIES
        SET 
            type = _type
        WHERE hotel_id = _hotel_id AND amenity_id = _amenity_id;

        IF NOT FOUND THEN
            RAISE EXCEPTION 'No AMENITIES found with Hotel ID %', _hotel_id;
        END IF;
    EXCEPTION WHEN foreign_key_violation THEN
        RAISE EXCEPTION 'Hotel ID % does not exist', _hotel_id;
    WHEN others THEN
        RAISE EXCEPTION 'An error occurred while updating AMENITIES.';
    END;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION update_reservation(
    _reservation_id INT,
    _customer_id INT,
    _room_id INT,
    _check_in_date DATE,
    _check_out_date DATE,
    _cost NUMERIC(10, 2),
    _status INT
) RETURNS VOID AS $$
BEGIN
    BEGIN
        UPDATE RESERVATION
        SET 
            customer_id = _customer_id,
            room_id = _room_id,
            check_in_date = _check_in_date,
            check_out_date = _check_out_date,
            cost = _cost,
            status = _status
        WHERE reservation_id = _reservation_id;

        IF NOT FOUND THEN
            RAISE EXCEPTION 'No RESERVATION found with ID %', _reservation_id;
        END IF;
    EXCEPTION WHEN foreign_key_violation THEN
        RAISE EXCEPTION 'Customer ID % or Room ID % does not exist', _customer_id, _room_id;
    WHEN others THEN
        RAISE EXCEPTION 'An error occurred while updating RESERVATION.';
    END;
END;
$$ LANGUAGE plpgsql;

--Delete Procedures
CREATE OR REPLACE FUNCTION delete_system_user(
    _user_id INT
) RETURNS VOID AS $$
BEGIN
    BEGIN
        DELETE FROM SYSTEM_USER
        WHERE user_id = _user_id;

        IF NOT FOUND THEN
            RAISE EXCEPTION 'No SYSTEM_USER found with ID %', _user_id;
        END IF;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'An error occurred while deleting SYSTEM_USER with ID %', _user_id;
    END;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION delete_customer(
    _customer_id INT
) RETURNS VOID AS $$
BEGIN
    BEGIN
        DELETE FROM CUSTOMER
        WHERE customer_id = _customer_id;

        IF NOT FOUND THEN
            RAISE EXCEPTION 'No CUSTOMER found with ID %', _customer_id;
        END IF;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'An error occurred while deleting CUSTOMER with ID %', _customer_id;
    END;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_hotel(
    _hotel_id INT
) RETURNS VOID AS $$
BEGIN
    BEGIN
        DELETE FROM HOTEL
        WHERE hotel_id = _hotel_id;

        IF NOT FOUND THEN
            RAISE EXCEPTION 'No HOTEL found with ID %', _hotel_id;
        END IF;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'An error occurred while deleting HOTEL with ID %', _hotel_id;
    END;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_room(
    _room_id INT
) RETURNS VOID AS $$
BEGIN
    BEGIN
        DELETE FROM ROOM
        WHERE room_id = _room_id;

        IF NOT FOUND THEN
            RAISE EXCEPTION 'No ROOM found with ID %', _room_id;
        END IF;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'An error occurred while deleting ROOM with ID %', _room_id;
    END;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_amenities(
    _hotel_id INT,
    _amenity_id INT
) RETURNS VOID AS $$
BEGIN
    BEGIN
        DELETE FROM AMENITIES
        WHERE hotel_id = _hotel_id AND amenity_id = _amenity_id;

        IF NOT FOUND THEN
            RAISE EXCEPTION 'No AMENITY found with Hotel ID % and Amenity ID %', _hotel_id, _amenity_id;
        END IF;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'An error occurred while deleting AMENITY for Hotel ID % and Amenity ID %', _hotel_id, _amenity_id;
    END;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_reservation(
    _reservation_id INT
) RETURNS VOID AS $$
BEGIN
    BEGIN
        DELETE FROM RESERVATION
        WHERE reservation_id = _reservation_id;

        IF NOT FOUND THEN
            RAISE EXCEPTION 'No RESERVATION found with ID %', _reservation_id;
        END IF;
    EXCEPTION WHEN others THEN
        RAISE EXCEPTION 'An error occurred while deleting RESERVATION with ID %', _reservation_id;
    END;
END;
$$ LANGUAGE plpgsql;
