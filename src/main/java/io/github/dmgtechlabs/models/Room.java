package io.github.dmgtechlabs.models;

import io.github.dmgtechlabs.Utils;
import io.github.dmgtechlabs.db.Dao;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.OracleConnection;
import io.github.kdesp73.databridge.connections.OracleConnection;
import io.github.kdesp73.databridge.helpers.Adapter;
import io.github.kdesp73.databridge.helpers.SQLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Room implements Dao {
    public enum Type {
        SINGLE(1),
        DOUBLE(2),
        TWIN(3),
        SUITE(4),
        DELUXE(5),
        FAMILY(6),
        STUDIO(7),
        KING(8),
        QUEEN(9),
        PRESIDENTIAL_SUITE(10),
        EXECUTIVE(11),
        ACCESSIBLE(12),
        VILLA(13),
        CABANA(14),
        BUNGALOW(15);

        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Type fromValue(int value) {
            for (Type type : Type.values()) {
                if (type.value == value) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid value for Room Type: " + value);
        }
    }

    private int id;
    private int number;
    private int floor;
    private Type type;
    private int hotelFk;
    private float price;

    public Room() {}
    // For writing
    public Room(int floor, int number, Type type, float price, int hotelFk){
        if(floor < 0) throw new IllegalArgumentException("Room floor can't be a negative number");
        this.floor = floor;

        if(number < 0) throw new IllegalArgumentException("Room number can't be a negative number");
        this.number = number;

        this.type = type;

        if(price <= 0) throw new IllegalArgumentException("Room price can't be negative or zero");
        this.price = price;

        this.hotelFk = hotelFk;
    }
    // For loading
    public Room(int id, int floor, int number, Type type, float price, int hotelFk){
        this.id = id;
        this.floor = floor;
        this.number = number;
        this.type = type;
        this.price = price;
        this.hotelFk = hotelFk;
    }

	public int getId() {
		return id;
	}

	public int getNumber() {
		return number;
	}

	public int getFloor() {
		return floor;
	}

	public Type getType() {
		return type;
	}

	public int getHotelFk() {
		return hotelFk;
	}

	public float getPrice() {
		return price;
	}
	
	

    @Override
    public boolean insert() {
        try(OracleConnection conn = (OracleConnection) AvailableConnections.ORACLE.getConnection()) {
            //Test with insertRoom using oracle connection
            conn.callProcedure("insert_room", floor, number, type.value, price, hotelFk);
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Insert Room failed", e);
            return false;
        }
        return true;
    }

	/**
	 * Accepts exactly 4 values
	 * floor (int), number (int), type (int), price (float)
	 * 
	 * update_room procedure should include the id (int) as the first parameter
	 * 
	 * @param values
	 * @return success or not 
	 */
    @Override
    public boolean update(Object... values) {
        if(values.length != 4)
            throw new IllegalArgumentException(String.format("Invalid number of values (%s). Expected 4", values.length));

        try(OracleConnection conn = (OracleConnection) AvailableConnections.ORACLE.getConnection()) {
            conn.callProcedure("update_room", Utils.appendFront(id, values));
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Update Room failed", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete() {
        try(OracleConnection conn = (OracleConnection) AvailableConnections.ORACLE.getConnection()){
            conn.callProcedure("delete_room", id);
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Delete Room failed", e);
            return false;
        }
        return true;
    }

    private static List<Room> select(String function, Object... values){
        assert(function != null);
        assert(!function.isBlank());
        try(OracleConnection conn = (OracleConnection) AvailableConnections.ORACLE.getConnection()) {
            ResultSet rs = conn.callFunction(function, values);
            return Adapter.load(rs, Room.class);
        } catch (Exception e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Select " + function + " failed", e);
            return null;
        }
    }

    public static List<Room> selectAll() {
        return select("select_all_rooms");
    }

    public static List<Room> selectByHotel(int hotelFk){
        return select("select_rooms_by_hotel", hotelFk);
    }

    public static List<Room> selectByFloor(int floor){
        return select("select_rooms_by_floor", floor);
    }

    public static List<Room> selectByType(Type type){
        return select("select_rooms_by_type", type.value);
    }

    public static List<Room> selectByPriceRange(float floor, float ceil){
        return select("select_rooms_by_price_range", floor, ceil);
    }
}
