-- DROP SCHEMA hoteldb;

CREATE SCHEMA hoteldb AUTHORIZATION iee2021035;
-- hoteldb.hotel definition

-- Drop table

-- DROP TABLE hotel;

CREATE TABLE hotel (
	"name" varchar NOT NULL,
	address varchar NOT NULL,
	phone numeric NOT NULL,
	id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	CONSTRAINT hotel_pk PRIMARY KEY (id)
);


-- hoteldb.customer definition

-- Drop table

-- DROP TABLE customer;

CREATE TABLE customer (
	id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	fname varchar NOT NULL,
	lname varchar NOT NULL,
	phone numeric NOT NULL,
	email varchar NOT NULL,
	CONSTRAINT customer_pk PRIMARY KEY (id)
);


-- hoteldb.room definition

-- Drop table

-- DROP TABLE room;

CREATE TABLE room (
	id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	"number" int4 NOT NULL,
	"type" int4 NOT NULL,
	price float8 NOT NULL,
	floor int4 NOT NULL,
	occupied bool DEFAULT false NOT NULL,
	room_hotel_fk int4 NOT NULL,
	CONSTRAINT room_pk PRIMARY KEY (id),
	CONSTRAINT room_hotel_fk FOREIGN KEY (room_hotel_fk) REFERENCES hotel(id)
);


-- hoteldb.amenities definition

-- Drop table

-- DROP TABLE amenities;

CREATE TABLE amenities (
	amenity int4 NOT NULL,
	hotel_id int4 NOT NULL,
	CONSTRAINT amenities_pk PRIMARY KEY (amenity, hotel_id),
	CONSTRAINT amenities_hotel_fk FOREIGN KEY (hotel_id) REFERENCES hotel(id)
);


-- hoteldb.account definition

-- Drop table

-- DROP TABLE account;

CREATE TABLE account (
	username varchar NOT NULL,
	"password" varchar NOT NULL,
	"type" int4 NOT NULL,
	account_hotel_fk int4 NOT NULL,
	id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	CONSTRAINT account_pk PRIMARY KEY (id),
	CONSTRAINT account_unique UNIQUE (username),
	CONSTRAINT account_hotel_fk FOREIGN KEY (account_hotel_fk) REFERENCES hotel(id)
);


-- hoteldb.reservation definition

-- Drop table

-- DROP TABLE reservation;

CREATE TABLE reservation (
	id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	reservation_customer_fk int4 NOT NULL,
	reservation_room_fk int4 NOT NULL,
	checkin date NOT NULL,
	checkout date NOT NULL,
	"cost" float8 NOT NULL,
	status int4 NOT NULL,
	CONSTRAINT reservation_pk PRIMARY KEY (id),
	CONSTRAINT reservation_customer_fk FOREIGN KEY (reservation_customer_fk) REFERENCES customer(id),
	CONSTRAINT reservation_room_fk FOREIGN KEY (reservation_room_fk) REFERENCES room(id)
);