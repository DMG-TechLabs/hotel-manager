-- USER_TYPE Table
CREATE TABLE USER_TYPE (
    user_type_id NUMBER PRIMARY KEY,
    user_type_name VARCHAR(50) UNIQUE NOT NULL
);

-- HOTEL Table
CREATE TABLE HOTEL (
    hotel_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(50),
    room_num NUMBER,
    amenities VARCHAR(255)
);

-- SYSTEM_USER Table
CREATE TABLE SYSTEM_USER (
    user_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    user_type NUMBER,
    hotel_id NUMBER NULL, -- Allowed null for system administrator
    FOREIGN KEY (hotel_id) REFERENCES HOTEL (hotel_id),
    FOREIGN KEY (user_type) REFERENCES USER_TYPE(user_type_id)
);

-- CUSTOMER Table
-- When a new hotel is added to the table, a guest user for the new hotel must also be added into SYSTEM_USER
CREATE TABLE CUSTOMER (
    customer_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone NUMBER,
    email VARCHAR(50)
);

-- ROOM_TYPE Table
CREATE TABLE ROOM_TYPE (
    type_id NUMBER PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL
);

-- ROOM Table
CREATE TABLE ROOM (
    room_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    room_num NUMBER,
    hotel_id NUMBER,
    type NUMBER,
    price DECIMAL(10,2),
    floor NUMBER,
    availability CHAR(1) DEFAULT 'Y', -- 'Y' for available, 'N' for not available
    FOREIGN KEY (hotel_id) REFERENCES HOTEL(hotel_id),
    FOREIGN KEY (type) REFERENCES ROOM_TYPE(type_id)
);

-- RESERVATION Table
CREATE TABLE RESERVATION (
    reservation_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    customer_id NUMBER,
    room_id NUMBER,
    check_in_date DATE,
    check_out_date DATE,
    cost DECIMAL(10,2),
    FOREIGN KEY (customer_id) REFERENCES CUSTOMER(customer_id),
    FOREIGN KEY (room_id) REFERENCES ROOM(room_id)
);

-- HOTEL_LOGS Table
CREATE TABLE HOTEL_LOGS (
    log_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    table_name VARCHAR(50),
    action_type VARCHAR(50),
    action_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_name VARCHAR(50),
    details VARCHAR(255)
);

------------------------------
--Insert Procedures

CREATE OR REPLACE PROCEDURE insertUserType (
    p_user_type_id IN NUMBER,
    p_user_type_name IN VARCHAR
) AS
BEGIN
    INSERT INTO USER_TYPE (user_type_name)
    VALUES (p_user_type_name);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while inserting into USER_TYPE: ' || SQLERRM);
END insertUserType;
/

CREATE OR REPLACE PROCEDURE insertHotel (
    p_name IN VARCHAR,
    p_address IN VARCHAR,
    p_room_num IN NUMBER,
    p_amenities IN VARCHAR
) AS
BEGIN
    INSERT INTO HOTEL (name, address, room_num, amenities)
    VALUES (p_name, p_address, p_room_num, p_amenities);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while inserting into HOTEL: ' || SQLERRM);
END insertHotel;
/

CREATE OR REPLACE PROCEDURE insertSystemUser (
    p_username IN VARCHAR,
    p_password IN VARCHAR,
    p_user_type IN NUMBER,
    p_hotel_id IN NUMBER
) AS
BEGIN
    INSERT INTO SYSTEM_USER (username, password, user_type, hotel_id)
    VALUES (p_username, p_password, p_user_type, p_hotel_id);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while inserting into SYSTEM_USER: ' || SQLERRM);
END insertSystemUser;
/

CREATE OR REPLACE PROCEDURE insertCustomer (
    p_first_name IN VARCHAR,
    p_last_name IN VARCHAR,
    p_phone IN NUMBER,
    p_email IN VARCHAR
) AS
BEGIN
    INSERT INTO CUSTOMER (first_name, last_name, phone, email)
    VALUES (p_first_name, p_last_name, p_phone, p_email);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while inserting into CUSTOMER: ' || SQLERRM);
END insertCustomer;
/

CREATE OR REPLACE PROCEDURE insertRoomType (
    p_type_name IN VARCHAR
) AS
BEGIN
    INSERT INTO ROOM_TYPE (type_name)
    VALUES (p_type_name);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while inserting into ROOM_TYPE: ' || SQLERRM);
END insertRoomType;
/

CREATE OR REPLACE PROCEDURE insertRoom (
    p_room_num IN NUMBER,
    p_hotel_id IN NUMBER,
    p_type IN NUMBER,
    p_price IN DECIMAL,
    p_floor IN NUMBER,
    p_availability IN CHAR
) AS
BEGIN
    INSERT INTO ROOM (room_num, hotel_id, type, price, floor, availability)
    VALUES (p_room_num, p_hotel_id, p_type, p_price, p_floor, p_availability);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while inserting into ROOM: ' || SQLERRM);
END insertRoom;
/

CREATE OR REPLACE PROCEDURE insertReservation (
    p_customer_id IN NUMBER,
    p_room_id IN NUMBER,
    p_check_in_date IN DATE,
    p_check_out_date IN DATE,
    p_cost IN DECIMAL
) AS
BEGIN
    INSERT INTO RESERVATION (customer_id, room_id, check_in_date, check_out_date, cost)
    VALUES (p_customer_id, p_room_id, p_check_in_date, p_check_out_date, p_cost);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while inserting into RESERVATION: ' || SQLERRM);
END insertReservation;
/

CREATE OR REPLACE PROCEDURE insertHotelLogs (
    p_table_name IN VARCHAR,
    p_action_type IN VARCHAR,
    p_user_name IN VARCHAR,
    p_details IN VARCHAR
) AS
BEGIN
    INSERT INTO HOTEL_LOGS (table_name, action_type, user_name, details)
    VALUES (p_table_name, p_action_type, p_user_name, p_details);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while inserting into HOTEL_LOGS: ' || SQLERRM);
END insertHotelLogs;
/

--Update procedures

CREATE OR REPLACE PROCEDURE updateUserType (
    p_user_type_id IN NUMBER,
    p_user_type_name IN VARCHAR
) AS
BEGIN
    UPDATE USER_TYPE
    SET user_type_name = p_user_type_name
    WHERE user_type_id = p_user_type_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while updating USER_TYPE: ' || SQLERRM);
END updateUserType;
/

CREATE OR REPLACE PROCEDURE updateHotel (
    p_hotel_id IN NUMBER,
    p_name IN VARCHAR,
    p_address IN VARCHAR,
    p_room_num IN NUMBER,
    p_amenities IN VARCHAR
) AS
BEGIN
    UPDATE HOTEL
    SET name = p_name,
        address = p_address,
        room_num = p_room_num,
        amenities = p_amenities
    WHERE hotel_id = p_hotel_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while updating HOTEL: ' || SQLERRM);
END updateHotel;
/

CREATE OR REPLACE PROCEDURE updateSystemUser (
    p_user_id IN NUMBER,
    p_username IN VARCHAR,
    p_password IN VARCHAR,
    p_user_type IN NUMBER,
    p_hotel_id IN NUMBER
) AS
BEGIN
    UPDATE SYSTEM_USER
    SET username = p_username,
        password = p_password,
        user_type = p_user_type,
        hotel_id = p_hotel_id
    WHERE user_id = p_user_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while updating SYSTEM_USER: ' || SQLERRM);
END updateSystemUser;
/

CREATE OR REPLACE PROCEDURE updateCustomer (
    p_customer_id IN NUMBER,
    p_first_name IN VARCHAR,
    p_last_name IN VARCHAR,
    p_phone IN NUMBER,
    p_email IN VARCHAR
) AS
BEGIN
    UPDATE CUSTOMER
    SET first_name = p_first_name,
        last_name = p_last_name,
        phone = p_phone,
        email = p_email
    WHERE customer_id = p_customer_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while updating CUSTOMER: ' || SQLERRM);
END updateCustomer;
/

CREATE OR REPLACE PROCEDURE updateRoomType (
    p_type_id IN NUMBER,
    p_type_name IN VARCHAR
) AS
BEGIN
    UPDATE ROOM_TYPE
    SET type_name = p_type_name
    WHERE type_id = p_type_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while updating ROOM_TYPE: ' || SQLERRM);
END updateRoomType;
/

CREATE OR REPLACE PROCEDURE updateRoom (
    p_room_id IN NUMBER,
    p_room_num IN NUMBER,
    p_hotel_id IN NUMBER,
    p_type IN NUMBER,
    p_price IN DECIMAL,
    p_floor IN NUMBER,
    p_availability IN CHAR
) AS
BEGIN
    UPDATE ROOM
    SET room_num = p_room_num,
        hotel_id = p_hotel_id,
        type = p_type,
        price = p_price,
        floor = p_floor,
        availability = p_availability
    WHERE room_id = p_room_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while updating ROOM: ' || SQLERRM);
END updateRoom;
/

CREATE OR REPLACE PROCEDURE updateReservation (
    p_reservation_id IN NUMBER,
    p_customer_id IN NUMBER,
    p_room_id IN NUMBER,
    p_check_in_date IN DATE,
    p_check_out_date IN DATE,
    p_cost IN DECIMAL
) AS
BEGIN
    UPDATE RESERVATION
    SET customer_id = p_customer_id,
        room_id = p_room_id,
        check_in_date = p_check_in_date,
        check_out_date = p_check_out_date,
        cost = p_cost
    WHERE reservation_id = p_reservation_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while updating RESERVATION: ' || SQLERRM);
END updateReservation;
/

CREATE OR REPLACE PROCEDURE updateHotelLogs (
    p_log_id IN NUMBER,
    p_table_name IN VARCHAR,
    p_action_type IN VARCHAR,
    p_user_name IN VARCHAR,
    p_details IN VARCHAR
) AS
BEGIN
    UPDATE HOTEL_LOGS
    SET table_name = p_table_name,
        action_type = p_action_type,
        user_name = p_user_name,
        details = p_details
    WHERE log_id = p_log_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while updating HOTEL_LOGS: ' || SQLERRM);
END updateHotelLogs;
/


--Delete by id procedures

CREATE OR REPLACE PROCEDURE deleteUserType (
    p_user_type_id IN NUMBER
) AS
BEGIN
    DELETE FROM USER_TYPE
    WHERE user_type_id = p_user_type_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while deleting from USER_TYPE: ' || SQLERRM);
END deleteUserType;
/

CREATE OR REPLACE PROCEDURE deleteHotel (
    p_hotel_id IN NUMBER
) AS
BEGIN
    DELETE FROM HOTEL
    WHERE hotel_id = p_hotel_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while deleting from HOTEL: ' || SQLERRM);
END deleteHotel;
/

CREATE OR REPLACE PROCEDURE deleteSystemUser (
    p_user_id IN NUMBER
) AS
BEGIN
    DELETE FROM SYSTEM_USER
    WHERE user_id = p_user_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while deleting from SYSTEM_USER: ' || SQLERRM);
END deleteSystemUser;
/

CREATE OR REPLACE PROCEDURE deleteCustomer (
    p_customer_id IN NUMBER
) AS
BEGIN
    DELETE FROM CUSTOMER
    WHERE customer_id = p_customer_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while deleting from CUSTOMER: ' || SQLERRM);
END deleteCustomer;
/

CREATE OR REPLACE PROCEDURE deleteRoomType (
    p_type_id IN NUMBER
) AS
BEGIN
    DELETE FROM ROOM_TYPE
    WHERE type_id = p_type_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while deleting from ROOM_TYPE: ' || SQLERRM);
END deleteRoomType;
/

CREATE OR REPLACE PROCEDURE deleteRoom (
    p_room_id IN NUMBER
) AS
BEGIN
    DELETE FROM ROOM
    WHERE room_id = p_room_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while deleting from ROOM: ' || SQLERRM);
END deleteRoom;
/

CREATE OR REPLACE PROCEDURE deleteReservation (
    p_reservation_id IN NUMBER
) AS
BEGIN
    DELETE FROM RESERVATION
    WHERE reservation_id = p_reservation_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while deleting from RESERVATION: ' || SQLERRM);
END deleteReservation;
/

CREATE OR REPLACE PROCEDURE deleteHotelLogs (
    p_log_id IN NUMBER
) AS
BEGIN
    DELETE FROM HOTEL_LOGS
    WHERE log_id = p_log_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while deleting from HOTEL_LOGS: ' || SQLERRM);
END deleteHotelLogs;
/

--Select by id

CREATE OR REPLACE PROCEDURE selectUserTypeById (
    p_user_type_id IN NUMBER
) AS
BEGIN
    SELECT *
    FROM USER_TYPE
    WHERE user_type_id = p_user_type_id;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while selecting from USER_TYPE: ' || SQLERRM);
END selectUserTypeById;
/

CREATE OR REPLACE PROCEDURE selectHotelById (
    p_hotel_id IN NUMBER
) AS
BEGIN
    SELECT *
    FROM HOTEL
    WHERE hotel_id = p_hotel_id;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while selecting from HOTEL: ' || SQLERRM);
END selectHotelById;
/

CREATE OR REPLACE PROCEDURE selectSystemUserById (
    p_user_id IN NUMBER
) AS
BEGIN
    SELECT *
    FROM SYSTEM_USER
    WHERE user_id = p_user_id;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while selecting from SYSTEM_USER: ' || SQLERRM);
END selectSystemUserById;
/

CREATE OR REPLACE PROCEDURE selectCustomerById (
    p_customer_id IN NUMBER
) AS
BEGIN
    SELECT *
    FROM CUSTOMER
    WHERE customer_id = p_customer_id;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while selecting from CUSTOMER: ' || SQLERRM);
END selectCustomerById;
/

CREATE OR REPLACE PROCEDURE selectRoomTypeById (
    p_type_id IN NUMBER
) AS
BEGIN
    SELECT *
    FROM ROOM_TYPE
    WHERE type_id = p_type_id;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while selecting from ROOM_TYPE: ' || SQLERRM);
END selectRoomTypeById;
/

CREATE OR REPLACE PROCEDURE selectRoomById (
    p_room_id IN NUMBER
) AS
BEGIN
    SELECT *
    FROM ROOM
    WHERE room_id = p_room_id;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while selecting from ROOM: ' || SQLERRM);
END selectRoomById;
/

CREATE OR REPLACE PROCEDURE selectReservationById (
    p_reservation_id IN NUMBER
) AS
BEGIN
    SELECT *
    FROM RESERVATION
    WHERE reservation_id = p_reservation_id;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while selecting from RESERVATION: ' || SQLERRM);
END selectReservationById;
/

CREATE OR REPLACE PROCEDURE selectHotelLogsById (
    p_log_id IN NUMBER
) AS
BEGIN
    SELECT *
    FROM HOTEL_LOGS
    WHERE log_id = p_log_id;
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while selecting from HOTEL_LOGS: ' || SQLERRM);
END selectHotelLogsById;
/


-----------------------------------------------
-- Insert Data into ROOM_TYPE
INSERT 
INTO ROOM_TYPE (type_id, type_name) VALUES 
(0, 'Single'),
(1, 'Double'),
(2, 'Family'),
(3, 'Suite'),
(4, 'Deluxe');

