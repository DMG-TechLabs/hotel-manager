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
	price float4 NOT NULL,
	floor int4 NOT NULL,
	occupied bool DEFAULT false NOT NULL,
	room_hotel_fk int4 NOT NULL,
	CONSTRAINT room_pk PRIMARY KEY (id),
	CONSTRAINT room_unique UNIQUE (number, floor, room_hotel_fk),
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
	"cost" float4 NOT NULL,
	status int4 NOT NULL,
	CONSTRAINT reservation_pk PRIMARY KEY (id),
	CONSTRAINT reservation_customer_fk FOREIGN KEY (reservation_customer_fk) REFERENCES hoteldb.customer(id) ON DELETE CASCADE ON UPDATE CASCADE,
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

-- DROP FUNCTION hoteldb.fun_insert_hotel(varchar, varchar, numeric);

CREATE OR REPLACE FUNCTION hoteldb.fun_insert_hotel(hotel_name character varying, hotel_address character varying, hotel_phone numeric)
 RETURNS bigint
 LANGUAGE sql
AS $function$
INSERT INTO hoteldb.hotel ("name", address, phone) 
VALUES (hotel_name, hotel_address, hotel_phone)
RETURNING id;
$function$
;

-- DROP FUNCTION hoteldb.get_occupancy_rate(int4);

CREATE OR REPLACE FUNCTION hoteldb.get_occupancy_rate(hotel_id_input integer)
 RETURNS TABLE(total_rooms integer, occupied_rooms integer, occupancy_rate numeric)
 LANGUAGE plpgsql
AS $function$
BEGIN

    RETURN QUERY(
    SELECT 
        CAST(COUNT(id) AS integer) AS total_rooms, -- Total number of rooms in the hotel
        CAST(COUNT(id) FILTER (WHERE occupied) AS integer) AS occupied_rooms, -- Number of occupied rooms
        ROUND(
            (COUNT(*) FILTER (WHERE occupied)::NUMERIC / NULLIF(COUNT(*), 0)) * 100, 
            2
        ) AS occupancy_rate -- Percentage of occupied rooms
    FROM hoteldb.room
    WHERE room_hotel_fk = hotel_id_input);

END;
$function$
;

-- DROP FUNCTION hoteldb.get_reservation_distribution(numeric);

CREATE OR REPLACE FUNCTION hoteldb.get_reservation_distribution(hotel_id_input numeric)
 RETURNS TABLE(month_year text, reservation_count integer)
 LANGUAGE plpgsql
AS $function$
BEGIN
    RETURN QUERY
    SELECT 
        TO_CHAR(res.checkin, 'YYYY-MM-DD') AS month_year,
        COUNT(*)::integer AS reservation_count
    FROM 
        hoteldb.reservation res
    JOIN hoteldb.room r ON res.reservation_room_fk = r.id
    WHERE r.room_hotel_fk = hotel_id_input
    GROUP BY month_year
    ORDER BY month_year;
END;
$function$
;

-- DROP FUNCTION hoteldb.get_reservation_distribution(int4);

CREATE OR REPLACE FUNCTION hoteldb.get_reservation_distribution(hotel_id_input integer)
 RETURNS TABLE(month_year text, reservation_count integer)
 LANGUAGE plpgsql
AS $function$
    BEGIN
return query(
    SELECT 
        TO_CHAR(res.checkin, 'YYYY-MM-DD') AS month_year,
        COUNT(*)::integer AS reservation_count
    FROM 
        hoteldb.reservation res
    JOIN hoteldb.room r ON res.reservation_room_fk = r.id
    WHERE r.room_hotel_fk = hotel_id_input
    GROUP BY month_year
    ORDER BY month_year);
END;
$function$
;

-- DROP FUNCTION hoteldb.get_total_revenue(int4, varchar, varchar);

CREATE OR REPLACE FUNCTION hoteldb.get_total_revenue(hotel_id_input integer, start_date character varying, end_date character varying)
 RETURNS real
 LANGUAGE plpgsql
AS $function$
DECLARE
    total_revenue float4;
BEGIN
    SELECT 
        SUM(res.cost) INTO total_revenue
    FROM 
        hoteldb.reservation res
    JOIN hoteldb.room r ON res.reservation_room_fk = r.id
    WHERE r.room_hotel_fk = hotel_id_input
    AND res.checkin BETWEEN to_date(start_date, 'YYYY-MM-DD') AND to_date(end_date, 'YYYY-MM-DD');

    RETURN total_revenue;
END;
$function$
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

-- DROP PROCEDURE hoteldb.insert_hotel(varchar, varchar, numeric);

CREATE OR REPLACE PROCEDURE hoteldb.insert_hotel(IN hotel_name character varying, IN hotel_address character varying, IN hotel_phone numeric)
 LANGUAGE sql
AS $procedure$
INSERT INTO hoteldb.hotel ("name", address, phone) 
VALUES (hotel_name, hotel_address, hotel_phone);
$procedure$
;

-- DROP PROCEDURE hoteldb.insert_reservation(int4, int4, varchar, varchar, float4, int4);

CREATE OR REPLACE PROCEDURE hoteldb.insert_reservation(IN customer_fk integer, IN room_fk integer, IN checkin character varying, IN checkout character varying, IN cost real, IN status integer)
 LANGUAGE sql
AS $procedure$
INSERT INTO hoteldb.reservation (reservation_customer_fk, reservation_room_fk, checkin, checkout, cost, status) 
VALUES (customer_fk, room_fk, to_date(checkin, 'YYYY-MM-DD'), to_date(checkout, 'YYYY-MM-DD'), cost, status);
$procedure$
;

-- DROP PROCEDURE hoteldb.insert_room(int4, int4, float4, int4, int4);

CREATE OR REPLACE PROCEDURE hoteldb.insert_room(IN room_number integer, IN room_type integer, IN room_price real, IN room_floor integer, IN room_hotel_fk integer)
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
 LANGUAGE plpgsql
AS $function$
BEGIN
	RETURN QUERY (
		SELECT * FROM hoteldb.hotel	
	);
END;
$function$
;

-- DROP FUNCTION hoteldb.select_all_reservations();

CREATE OR REPLACE FUNCTION hoteldb.select_all_reservations()
 RETURNS SETOF hoteldb.reservation
 LANGUAGE plpgsql
AS $function$
BEGIN
	RETURN query (
		SELECT * FROM hoteldb.reservation
	);
END;
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

-- DROP FUNCTION hoteldb.select_all_users_except_this(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_all_users_except_this(excluded_id integer)
 RETURNS SETOF hoteldb.account
 LANGUAGE plpgsql
AS $function$
	BEGIN
RETURN QUERY
    SELECT username, password, type, account_hotel_fk, id
    FROM hoteldb.account
    WHERE id != excluded_id;
	END;
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

-- DROP FUNCTION hoteldb.select_customer_by_email(varchar);

CREATE OR REPLACE FUNCTION hoteldb.select_customer_by_email(customer_email character varying)
 RETURNS SETOF hoteldb.customer
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.customer 
WHERE email = customer_email;
$function$
;

-- DROP FUNCTION hoteldb.select_customers_by_id(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_customers_by_id(customer_id integer)
 RETURNS SETOF hoteldb.customer
 LANGUAGE plpgsql
AS $function$
BEGIN
	RETURN QUERY (
		SELECT * FROM hoteldb.customer c 
		WHERE c.id = customer_id
	);
END;
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

-- DROP FUNCTION hoteldb.select_hotels_by_id(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_hotels_by_id(hotel_id integer)
 RETURNS SETOF hoteldb.hotel
 LANGUAGE plpgsql
AS $function$
BEGIN
    RETURN QUERY (
	    SELECT *
	    FROM hoteldb.hotel h
	    WHERE h.id = hotel_id
	);
END;
$function$
;

-- DROP FUNCTION hoteldb.select_hotels_by_name(varchar);

CREATE OR REPLACE FUNCTION hoteldb.select_hotels_by_name(hotel_name character varying)
 RETURNS TABLE(name character varying, address character varying, phone numeric)
 LANGUAGE plpgsql
AS $function$
begin	
return query(
SELECT * FROM hoteldb.hotel 
WHERE "name" = hotel_name
);
end;
$function$
;

-- DROP FUNCTION hoteldb.select_reservations_by_check_in_check_out(int4, varchar, varchar);

CREATE OR REPLACE FUNCTION hoteldb.select_reservations_by_check_in_check_out(_reservation_room_fk integer, reservation_check_in character varying, reservation_check_out character varying)
 RETURNS SETOF hoteldb.reservation
 LANGUAGE plpgsql
AS $function$
BEGIN
	RETURN query (
		SELECT * FROM hoteldb.reservation 
		WHERE reservation_room_fk = _reservation_room_fk AND checkin = to_date(reservation_check_in, 'YYYY-MM-DD') AND checkout = to_date(reservation_check_out, 'YYYY-MM-DD')
	);
END;
$function$
;

-- DROP FUNCTION hoteldb.select_reservations_by_hotel(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_reservations_by_hotel(hotel_fk integer)
 RETURNS SETOF hoteldb.reservation
 LANGUAGE plpgsql
AS $function$
BEGIN
	RETURN query (
		SELECT 
			r.id,
			r.reservation_customer_fk,
			r.reservation_room_fk,
			r.checkin,
			r.checkout,
			r.cost,
			r.status 
		FROM hoteldb.reservation r INNER JOIN hoteldb.room rm ON r.reservation_room_fk = rm.id
		WHERE rm.room_hotel_fk = hotel_fk
	);
END;
$function$
;

-- DROP FUNCTION hoteldb.select_reservations_by_room_id(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_reservations_by_room_id(room_id integer)
 RETURNS SETOF hoteldb.reservation
 LANGUAGE plpgsql
AS $function$
BEGIN
	RETURN query (
		SELECT * FROM hoteldb.reservation 
		WHERE reservation_room_fk = room_id
	);
END;
$function$
;

-- DROP FUNCTION hoteldb.select_reservations_by_status(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_reservations_by_status(reservation_status integer)
 RETURNS SETOF hoteldb.reservation
 LANGUAGE plpgsql
AS $function$
BEGIN
	RETURN query (
		SELECT * FROM hoteldb.reservation 
		WHERE status = reservation_status
	);
END;
$function$
;

-- DROP FUNCTION hoteldb.select_reserved_rooms(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_reserved_rooms(hotel_fk integer)
 RETURNS SETOF hoteldb.room
 LANGUAGE plpgsql
AS $function$
BEGIN
	RETURN query (
		SELECT 
			rm.id,
			rm.number,
			rm.type,
			rm.price,
			rm.floor,
			rm.occupied,
			rm.room_hotel_fk 
		FROM hoteldb.room rm INNER JOIN hoteldb.reservation r ON rm.id = r.reservation_room_fk
		WHERE rm.room_hotel_fk = hotel_fk
	);
END;
$function$
;

-- DROP FUNCTION hoteldb.select_rooms_by_floor(int4, int4);

CREATE OR REPLACE FUNCTION hoteldb.select_rooms_by_floor(room_floor integer, hotel_fk integer)
 RETURNS SETOF hoteldb.room
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.room 
WHERE floor = room_floor AND room_hotel_fk = hotel_fk;
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

-- DROP FUNCTION hoteldb.select_rooms_by_hotel_id(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_rooms_by_hotel_id(hotel_fk integer)
 RETURNS SETOF hoteldb.room
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.room 
WHERE room_hotel_fk = hotel_fk;
$function$
;

-- DROP FUNCTION hoteldb.select_rooms_by_id(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_rooms_by_id(room_id integer)
 RETURNS SETOF hoteldb.room
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.room 
WHERE "id" = room_id;
$function$
;

-- DROP FUNCTION hoteldb.select_rooms_by_occupied_and_hotel_id(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_rooms_by_occupied_and_hotel_id(hotel_id integer)
 RETURNS SETOF hoteldb.room
 LANGUAGE plpgsql
AS $function$
BEGIN
	RETURN QUERY (
		SELECT * FROM hoteldb.room r 
		WHERE r.room_hotel_fk = hotel_id AND r.occupied = FALSE
	);
END;
$function$
;

-- DROP FUNCTION hoteldb.select_rooms_by_price_range(float4, float4, int4);

CREATE OR REPLACE FUNCTION hoteldb.select_rooms_by_price_range(floor_ real, ceil_ real, hotel_fk integer)
 RETURNS SETOF hoteldb.room
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.room 
WHERE 
	price >= floor_ AND price <= ceil_
	AND room_hotel_fk = hotel_fk;
$function$
;

-- DROP FUNCTION hoteldb.select_rooms_by_price_range(float4, float4);

CREATE OR REPLACE FUNCTION hoteldb.select_rooms_by_price_range(floor_ real, ceil_ real)
 RETURNS SETOF hoteldb.room
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.room 
WHERE 
	price >= floor_ AND price <= ceil_;
$function$
;

-- DROP FUNCTION hoteldb.select_rooms_by_type(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_rooms_by_type(room_type integer)
 RETURNS SETOF hoteldb.room
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.room 
WHERE "type" = room_type;
$function$
;

-- DROP FUNCTION hoteldb.select_rooms_by_type(int4, int4);

CREATE OR REPLACE FUNCTION hoteldb.select_rooms_by_type(room_type integer, hotel_fk integer)
 RETURNS SETOF hoteldb.room
 LANGUAGE sql
AS $function$
SELECT * FROM hoteldb.room 
WHERE "type" = room_type
AND room_hotel_fk = hotel_fk;
$function$
;

-- DROP FUNCTION hoteldb.select_user_by_id(int4);

CREATE OR REPLACE FUNCTION hoteldb.select_user_by_id(user_id integer)
 RETURNS SETOF hoteldb.account
 LANGUAGE sql
AS $function$
	select * from hoteldb.account where "id"=user_id
$function$
;

-- DROP FUNCTION hoteldb.select_user_with_username_password(varchar, varchar, numeric);

CREATE OR REPLACE FUNCTION hoteldb.select_user_with_username_password(user_username character varying, user_password character varying, hotel_id numeric)
 RETURNS SETOF hoteldb.account
 LANGUAGE plpgsql
AS $function$
	BEGIN
return query(
select * from hoteldb.account where "username"=user_username and "password"=user_password and "account_hotel_fk"=hotel_id);
	END;
$function$
;

-- DROP FUNCTION hoteldb.select_user_with_username_password(varchar, varchar);

CREATE OR REPLACE FUNCTION hoteldb.select_user_with_username_password(user_username character varying, user_password character varying)
 RETURNS SETOF hoteldb.account
 LANGUAGE plpgsql
AS $function$
	BEGIN
return query(
select * from hoteldb.account where "username"=user_username and "password"=user_password);
	END;
$function$
;

-- DROP PROCEDURE hoteldb.update_account(int4, varchar, varchar, int4);

CREATE OR REPLACE PROCEDURE hoteldb.update_account(IN account_id integer, IN user_username character varying, IN user_password character varying, IN account_type integer)
 LANGUAGE sql
AS $procedure$
UPDATE hoteldb.account 
SET "username" = user_username, "password" = user_password, "type" = account_type 
WHERE id = account_id;
$procedure$
;

-- DROP PROCEDURE hoteldb.update_customer(int4, varchar, varchar, numeric, varchar);

CREATE OR REPLACE PROCEDURE hoteldb.update_customer(IN customer_id integer, IN customer_fname character varying, IN customer_lname character varying, IN customer_phone numeric, IN customer_email character varying)
 LANGUAGE sql
AS $procedure$
UPDATE hoteldb.customer 
SET fname = customer_fname, lname = customer_lname, phone = customer_phone, email = customer_email
WHERE id = customer_id;
$procedure$
;

-- DROP PROCEDURE hoteldb.update_hotel(int4, varchar, varchar, numeric);

CREATE OR REPLACE PROCEDURE hoteldb.update_hotel(IN hotel_id integer, IN hotel_name character varying, IN hotel_address character varying, IN hotel_phone numeric)
 LANGUAGE sql
AS $procedure$
UPDATE hoteldb.hotel 
SET "name" = hotel_name, address = hotel_address, phone = hotel_phone 
WHERE id = hotel_id;
$procedure$
;

-- DROP PROCEDURE hoteldb.update_reservation(int4, int4);

CREATE OR REPLACE PROCEDURE hoteldb.update_reservation(IN reservation_id integer, IN reservation_status integer)
 LANGUAGE sql
AS $procedure$
UPDATE hoteldb.reservation 
SET status = reservation_status 
WHERE id = reservation_id;
$procedure$
;

-- DROP PROCEDURE hoteldb.update_room(int4, int4, int4, int4, float4);

CREATE OR REPLACE PROCEDURE hoteldb.update_room(IN room_id integer, IN room_floor integer, IN room_number integer, IN room_type integer, IN room_price real)
 LANGUAGE sql
AS $procedure$
UPDATE hoteldb.room 
SET "number" = room_number, "type" = room_type, price = room_price, floor = room_floor
WHERE id = room_id;
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