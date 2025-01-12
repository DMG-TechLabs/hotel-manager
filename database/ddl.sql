-- DROP SCHEMA hoteldb;

CREATE SCHEMA hoteldb AUTHORIZATION iee2021035;

-- DROP SEQUENCE hoteldb.account_id_seq;

CREATE SEQUENCE hoteldb.account_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE hoteldb.customer_id_seq;

CREATE SEQUENCE hoteldb.customer_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE hoteldb.hotel_id_seq;

CREATE SEQUENCE hoteldb.hotel_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE hoteldb.reservation_id_seq;

CREATE SEQUENCE hoteldb.reservation_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE hoteldb.room_id_seq;

CREATE SEQUENCE hoteldb.room_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;-- hoteldb.customer definition

-- Drop table

-- DROP TABLE hoteldb.customer;

CREATE TABLE hoteldb.customer (
	id int4 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	fname varchar NOT NULL,
	lname varchar NOT NULL,
	phone numeric NOT NULL,
	email varchar NOT NULL,
	CONSTRAINT customer_pk PRIMARY KEY (id)
);


-- hoteldb.hotel definition

-- Drop table

-- DROP TABLE hoteldb.hotel;

CREATE TABLE hoteldb.hotel (
	"name" varchar NOT NULL,
	address varchar NOT NULL,
	phone int8 NOT NULL,
	id int4 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	CONSTRAINT hotel_pk PRIMARY KEY (id)
);


-- hoteldb.account definition

-- Drop table

-- DROP TABLE hoteldb.account;

CREATE TABLE hoteldb.account (
	username varchar NOT NULL,
	"password" varchar NOT NULL,
	"type" int4 NOT NULL,
	account_hotel_fk int4 NOT NULL,
	id int4 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	CONSTRAINT account_pk PRIMARY KEY (id),
	CONSTRAINT account_unique UNIQUE (username),
	CONSTRAINT account_hotel_fk FOREIGN KEY (account_hotel_fk) REFERENCES hoteldb.hotel(id)
);


-- hoteldb.amenities definition

-- Drop table

-- DROP TABLE hoteldb.amenities;

CREATE TABLE hoteldb.amenities (
	amenity int4 NOT NULL,
	hotel_id int4 NOT NULL,
	CONSTRAINT amenities_pk PRIMARY KEY (amenity, hotel_id),
	CONSTRAINT amenities_hotel_fk FOREIGN KEY (hotel_id) REFERENCES hoteldb.hotel(id)
);


-- hoteldb.room definition

-- Drop table

-- DROP TABLE hoteldb.room;

CREATE TABLE hoteldb.room (
	id int4 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	"number" int4 NOT NULL,
	"type" int4 NOT NULL,
	price float8 NOT NULL,
	floor int4 NOT NULL,
	occupied bool DEFAULT false NOT NULL,
	room_hotel_fk int4 NOT NULL,
	CONSTRAINT room_pk PRIMARY KEY (id),
	CONSTRAINT room_hotel_fk FOREIGN KEY (room_hotel_fk) REFERENCES hoteldb.hotel(id)
);


-- hoteldb.reservation definition

-- Drop table

-- DROP TABLE hoteldb.reservation;

CREATE TABLE hoteldb.reservation (
	id int4 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	reservation_customer_fk int4 NOT NULL,
	reservation_room_fk int4 NOT NULL,
	checkin date NOT NULL,
	checkout date NOT NULL,
	"cost" float8 NOT NULL,
	status int4 NOT NULL,
	CONSTRAINT reservation_pk PRIMARY KEY (id),
	CONSTRAINT reservation_customer_fk FOREIGN KEY (reservation_customer_fk) REFERENCES hoteldb.customer(id),
	CONSTRAINT reservation_room_fk FOREIGN KEY (reservation_room_fk) REFERENCES hoteldb.room(id)
);



-- DROP PROCEDURE hoteldb.delete_account(int4);

CREATE OR REPLACE PROCEDURE hoteldb.delete_account(IN account_id integer)
 LANGUAGE sql
AS $procedure$
DELETE FROM hoteldb.account 
WHERE id = account_id;
$procedure$
;

-- DROP PROCEDURE hoteldb.delete_amenities(int4, int4);

CREATE OR REPLACE PROCEDURE hoteldb.delete_amenities(IN amenity integer, IN hotel_id integer)
 LANGUAGE sql
AS $procedure$
DELETE FROM hoteldb.amenities 
WHERE amenity = amenity AND hotel_id = hotel_id;
$procedure$
;

-- DROP PROCEDURE hoteldb.delete_customer(int4);

CREATE OR REPLACE PROCEDURE hoteldb.delete_customer(IN customer_id integer)
 LANGUAGE sql
AS $procedure$
DELETE FROM hoteldb.customer 
WHERE id = customer_id;
$procedure$
;

-- DROP PROCEDURE hoteldb.delete_hotel(int4);

CREATE OR REPLACE PROCEDURE hoteldb.delete_hotel(IN hotel_id integer)
 LANGUAGE sql
AS $procedure$
DELETE FROM hoteldb.hotel 
WHERE id = hotel_id;
$procedure$
;

-- DROP PROCEDURE hoteldb.delete_reservation(int4);

CREATE OR REPLACE PROCEDURE hoteldb.delete_reservation(IN reservation_id integer)
 LANGUAGE sql
AS $procedure$
DELETE FROM hoteldb.reservation 
WHERE id = reservation_id;
$procedure$
;

-- DROP PROCEDURE hoteldb.delete_room(int4);

CREATE OR REPLACE PROCEDURE hoteldb.delete_room(IN room_id integer)
 LANGUAGE sql
AS $procedure$
DELETE FROM hoteldb.room 
WHERE id = room_id;
$procedure$
;

-- DROP PROCEDURE hoteldb.insert_account(varchar, varchar, int4, int4);

CREATE OR REPLACE PROCEDURE hoteldb.insert_account(IN username character varying, IN password character varying, IN account_type integer, IN account_hotel_fk integer)
 LANGUAGE sql
AS $procedure$
INSERT INTO hoteldb.account (username, "password", "type", account_hotel_fk) 
VALUES (username, password, account_type, account_hotel_fk);
$procedure$
;

-- DROP PROCEDURE hoteldb.insert_amenities(int4, int4);

CREATE OR REPLACE PROCEDURE hoteldb.insert_amenities(IN amenity integer, IN hotel_id integer)
 LANGUAGE sql
AS $procedure$
INSERT INTO hoteldb.amenities (amenity, hotel_id) 
VALUES (amenity, hotel_id);
$procedure$
;

-- DROP PROCEDURE hoteldb.insert_customer(varchar, varchar, numeric, varchar);

CREATE OR REPLACE PROCEDURE hoteldb.insert_customer(IN fname character varying, IN lname character varying, IN phone numeric, IN email character varying)
 LANGUAGE sql
AS $procedure$
INSERT INTO hoteldb.customer (fname, lname, phone, email) 
VALUES (fname, lname, phone, email);
$procedure$
;

-- DROP PROCEDURE hoteldb.insert_hotel(varchar, varchar, int8);

null;

-- DROP PROCEDURE hoteldb.insert_hotel(varchar, varchar, int4);

null;

-- DROP PROCEDURE hoteldb.insert_hotel(varchar, varchar, numeric);

null;

-- DROP PROCEDURE hoteldb.insert_reservation(int4, int4, date, date, float8, int4);

CREATE OR REPLACE PROCEDURE hoteldb.insert_reservation(IN customer_fk integer, IN room_fk integer, IN checkin date, IN checkout date, IN cost double precision, IN status integer)
 LANGUAGE sql
AS $procedure$
INSERT INTO hoteldb.reservation (reservation_customer_fk, reservation_room_fk, checkin, checkout, cost, status) 
VALUES (customer_fk, room_fk, checkin, checkout, cost, status);
$procedure$
;

-- DROP PROCEDURE hoteldb.insert_room(int4, int4, float8, int4, int4);

CREATE OR REPLACE PROCEDURE hoteldb.insert_room(IN room_number integer, IN room_type integer, IN room_price double precision, IN room_floor integer, IN room_hotel_fk integer)
 LANGUAGE sql
AS $procedure$
INSERT INTO hoteldb.room ("number", "type", price, floor, room_hotel_fk) 
VALUES (room_number, room_type, room_price, room_floor, room_hotel_fk);
$procedure$
;

-- DROP FUNCTION hoteldb.select_accounts_by_type(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_accounts_by_type(account_type integer)
 RETURNS SETOF hoteldb.account
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.account 
WHERE "type" = account_type;
$function$
;

-- DROP FUNCTION hoteldb.select_all_accounts();

CREATE OR REPLACE FUNCTION hoteldb.select_all_accounts()
 RETURNS SETOF hoteldb.account
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.account;
$function$
;

-- DROP FUNCTION hoteldb.select_all_amenities();

CREATE OR REPLACE FUNCTION hoteldb.select_all_amenities()
 RETURNS SETOF hoteldb.amenities
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.amenities;
$function$
;

-- DROP FUNCTION hoteldb.select_all_customers();

CREATE OR REPLACE FUNCTION hoteldb.select_all_customers()
 RETURNS SETOF hoteldb.customer
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.customer;
$function$
;

-- DROP FUNCTION hoteldb.select_all_hotels();

CREATE OR REPLACE FUNCTION hoteldb.select_all_hotels()
 RETURNS SETOF hoteldb.hotel
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.hotel;
$function$
;

-- DROP FUNCTION hoteldb.select_all_reservations();

CREATE OR REPLACE FUNCTION hoteldb.select_all_reservations()
 RETURNS SETOF hoteldb.reservation
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.reservation;
$function$
;

-- DROP FUNCTION hoteldb.select_all_rooms();

CREATE OR REPLACE FUNCTION hoteldb.select_all_rooms()
 RETURNS SETOF hoteldb.room
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.room;
$function$
;

-- DROP FUNCTION hoteldb.select_amenities_by_hotel(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_amenities_by_hotel(hotel_id integer)
 RETURNS SETOF hoteldb.amenities
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.amenities 
WHERE hotel_id = hotel_id;
$function$
;

-- DROP FUNCTION hoteldb.select_customers_by_lname(varchar);

CREATE OR REPLACE FUNCTION hoteldb.select_customers_by_lname(last_name character varying)
 RETURNS SETOF hoteldb.customer
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.customer 
WHERE lname = last_name;
$function$
;

-- DROP FUNCTION hoteldb.select_hotels_by_name(varchar);

CREATE OR REPLACE FUNCTION hoteldb.select_hotels_by_name(hotel_name character varying)
 RETURNS SETOF hoteldb.hotel
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.hotel 
WHERE "name" = hotel_name;
$function$
;

-- DROP FUNCTION hoteldb.select_reservations_by_status(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_reservations_by_status(reservation_status integer)
 RETURNS SETOF hoteldb.reservation
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.reservation 
WHERE status = reservation_status;
$function$
;

-- DROP FUNCTION hoteldb.select_rooms_by_floor(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_rooms_by_floor(room_floor integer)
 RETURNS SETOF hoteldb.room
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.room 
WHERE floor = room_floor;
$function$
;

-- DROP PROCEDURE hoteldb.update_account(int4, varchar, varchar, int4);

CREATE OR REPLACE PROCEDURE hoteldb.update_account(IN account_id integer, IN username character varying, IN password character varying, IN account_type integer)
 LANGUAGE sql
AS $procedure$
UPDATE hoteldb.account 
SET username = username, "password" = password, "type" = account_type 
WHERE id = account_id;
$procedure$
;

-- DROP PROCEDURE hoteldb.update_customer(int4, varchar, varchar, numeric, varchar);

CREATE OR REPLACE PROCEDURE hoteldb.update_customer(IN customer_id integer, IN fname character varying, IN lname character varying, IN phone numeric, IN email character varying)
 LANGUAGE sql
AS $procedure$
UPDATE hoteldb.customer 
SET fname = fname, lname = lname, phone = phone, email = email 
WHERE id = customer_id;
$procedure$
;

-- DROP PROCEDURE hoteldb.update_hotel(int4, varchar, varchar, int8);

null;

-- DROP PROCEDURE hoteldb.update_hotel(int4, varchar, varchar, int4);

null;

-- DROP PROCEDURE hoteldb.update_hotel(int4, varchar, varchar, numeric);

CREATE OR REPLACE PROCEDURE hoteldb.update_hotel(IN hotel_id integer, IN hotel_name character varying, IN hotel_address character varying, IN hotel_phone numeric)
 LANGUAGE sql
AS $procedure$
UPDATE hoteldb.hotel 
SET "name" = hotel_name, address = hotel_address, phone = hotel_phone 
WHERE id = hotel_id;
$procedure$
;

-- DROP PROCEDURE hoteldb.update_reservation(int4, date, date, float8, int4);

CREATE OR REPLACE PROCEDURE hoteldb.update_reservation(IN reservation_id integer, IN checkin date, IN checkout date, IN cost double precision, IN status integer)
 LANGUAGE sql
AS $procedure$
UPDATE hoteldb.reservation 
SET checkin = checkin, checkout = checkout, cost = cost, status = status 
WHERE id = reservation_id;
$procedure$
;

-- DROP PROCEDURE hoteldb.update_room(int4, int4, int4, float8, int4, bool);

CREATE OR REPLACE PROCEDURE hoteldb.update_room(IN room_id integer, IN room_number integer, IN room_type integer, IN room_price double precision, IN room_floor integer, IN room_occupied boolean)
 LANGUAGE sql
AS $procedure$
UPDATE hoteldb.room 
SET "number" = room_number, "type" = room_type, price = room_price, floor = room_floor, occupied = room_occupied 
WHERE id = room_id;
$procedure$
;