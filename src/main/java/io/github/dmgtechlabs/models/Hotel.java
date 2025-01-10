package io.github.dmgtechlabs.models;

import io.github.dmgtechlabs.Utils;
import io.github.dmgtechlabs.db.Dao;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.OracleConnection;
import io.github.kdesp73.databridge.helpers.Adapter;
import io.github.kdesp73.databridge.helpers.SQLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Hotel implements Dao {
    public enum Amenity {
        POOL(1),
        GYM(2),
        BAR(3),
        SPA(4),
        SAUNA(5),
        WIFI(6),
        PARKING(7);

        private final int value;

        Amenity(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Hotel.Amenity fromValue(int value) {
            for (Hotel.Amenity type : Hotel.Amenity.values()) {
                if (type.value == value) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid value for Hotel Amenity: " + value);
        }
    }


    private String name;
    private String address;
    private long phone;
    private int hotelId;
    //We should add
    //private String amenities;

    public Hotel(){}
    // For writing
    public Hotel(String name, String address, long phoneNumber){
        this.name = name;
        this.address = address;
        this.phone = phoneNumber;
    }
    // For loading
    public Hotel(int id, String name, String address, long phoneNumber){
        this.hotelId = id;
        this.name = name;
        
        this.address = address;
		this.phone = phoneNumber;
    }

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public long getPhone() {
		return phone;
	}

	public int getHotelId() {
		return hotelId;
	}
	
	

    @Override
    public boolean insert() {
        try(OracleConnection conn = (OracleConnection) AvailableConnections.ORACLE.getConnection()) {
            conn.callProcedure("INSERTHOTEL", name, address, phone);
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Insert Hotel failed", e);
            return false;
        }
        return true;
    }

	/**
	 * Accepts exactly 3 values
	 * name (string), address (string), phone (long)
	 * 
	 * update_hotel procedure should include the id (int) as the first parameter
	 * 
	 * @param values
	 * @return success or not 
	 */
    @Override
    public boolean update(Object... values) {
        final int expectedParams = 3;
		if(values.length != expectedParams)
            throw new IllegalArgumentException(String.format("Invalid number of values (%s). Expected %d", values.length, expectedParams));

        try(OracleConnection conn = (OracleConnection) AvailableConnections.ORACLE.getConnection()) {
            conn.callProcedure("update_hotel", Utils.appendFront(hotelId, values));
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Update hotel failed", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete() {
        try(OracleConnection conn = (OracleConnection) AvailableConnections.ORACLE.getConnection()){
            conn.callProcedure("delete_hotel", hotelId);
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Delete Hotel failed", e);
            return false;
        }
        return true;
    }

    private static List<Hotel> select(String function, Object... values){
        assert(function != null);
        assert(!function.isBlank());
        try(OracleConnection conn = (OracleConnection) AvailableConnections.ORACLE.getConnection()) {
            ResultSet rs = conn.callFunction(function, values);
            return Adapter.load(rs, Hotel.class);
        } catch (Exception e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Select " + function + " failed", e);
            return null;
        }
    }

    public static List<Hotel> selectById(int id){
        return select("select_hotel_by_id", id);
    }

    public static List<Hotel> selectByName(String name) {
        return select("select_hotel_by_name", name);
    }
}
