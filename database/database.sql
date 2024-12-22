-- USER Table
CREATE TABLE USER (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    user_type INT,
    FOREIGN KEY (hotel_id) REFERENCES HOTEL (hotel_id)
);

-- USER_TYPE Table
CREATE TABLE USER_TYPE (
    user_type_id INT AUTO_INCREMENT PRIMARY KEY,
    user_type_name VARCHAR(50) UNIQUE NOT NULL
);

-- CUSTOMER Table
CREATE TABLE CUSTOMER (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone BIGINT,
    email VARCHAR(50)
);

-- HOTEL Table
CREATE TABLE HOTEL (
    hotel_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(50),
    room_num INT,
    amenities VARCHAR(255)
);

-- ROOM_TYPE Table
CREATE TABLE ROOM_TYPE (
    type_id INT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL
);

-- ROOM Table
CREATE TABLE ROOM (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_num INT,
    hotel_id INT,
    type INT,
    price DECIMAL(10,2),
    floor INT,
    availability BOOLEAN,
    FOREIGN KEY (hotel_id) REFERENCES HOTEL(hotel_id),
    FOREIGN KEY (type) REFERENCES ROOM_TYPE(type_id)
);

-- RESERVATION Table
CREATE TABLE RESERVATION (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    room_id INT,
    check_in_date DATE,
    check_out_date DATE,
    cost DECIMAL(10,2),
    FOREIGN KEY (customer_id) REFERENCES CUSTOMER(customer_id),
    FOREIGN KEY (room_id) REFERENCES ROOM(room_id)
);

-- LOGS Table
CREATE TABLE LOGS (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    table_name VARCHAR(50),
    action_type VARCHAR(50),
    action_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_name VARCHAR(50),
    details VARCHAR(255)
);

-----------------------------------------------
-- Insert Data into ROOM_TYPE
INSERT INTO ROOM_TYPE (type_name) VALUES 
('Single'),
('Double'),
('Suite'),
('Deluxe'),
('Family');
