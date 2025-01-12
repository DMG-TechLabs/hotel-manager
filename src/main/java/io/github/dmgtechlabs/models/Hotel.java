package io.github.dmgtechlabs.models;

import io.github.dmgtechlabs.Utils;
import io.github.dmgtechlabs.db.Dao;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;
import io.github.kdesp73.databridge.helpers.Adapter;
import io.github.kdesp73.databridge.helpers.SQLogger;
import io.github.kdesp73.databridge.helpers.QueryBuilder;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private BigInteger phone;
    private int id;
    private List<Amenity> amenities;

    public Hotel(){}
    // For writing
    public Hotel(String name, String address, BigInteger phoneNumber){
        this.name = name;
        this.address = address;
        this.phone = phoneNumber;
    }
    // For loading
    public Hotel(int id, String name, String address, BigInteger phoneNumber){
        this.id = id;
        this.name = name;
        
        this.address = address;
		this.phone = phoneNumber;
        this.amenities = selectAmenities();
    }

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public BigInteger getPhone() {
		return phone;
	}

	public int getId() {
		return id;
	}

    public List<Amenity> getAmenities() {
        return amenities;
    }

    @Override
    public boolean insert() {
        try(PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
            conn.callProcedure("insert_hotel", name, address, phone);
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Insert Hotel failed", e);
            return false;
        }
        return true;
    }

	/**
	 * Accepts exactly 3 values
	 * name (string), address (string), phone (BigInteger)
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

        try(PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
            conn.callProcedure("update_hotel", Utils.appendFront(id, values));
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Update hotel failed", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete() {
        try(PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()){
            conn.callProcedure("delete_hotel", id);
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Delete Hotel failed", e);
            return false;
        }
        return true;
    }

    private static List<Hotel> select(String function, Object... values){
        assert(function != null);
        assert(!function.isBlank());
        try(PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
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

    public List<Amenity> selectAmenities() {
        List<Amenity> result = new ArrayList<>();
        try(PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
            ResultSet rs = conn.callFunction("select_amenities", id);
            while(rs.next()){
                result.add(Amenity.fromValue(rs.getInt("AMENITY")));
            }
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Select amenities failed", e);
            return null;
        }
        this.amenities = result;
        return result;
    }

    public boolean insertAmenities(Amenity... amenities) {
        if(amenities.length == 0) return false;
        for(Amenity a : amenities) {
            try(PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
                String query = new QueryBuilder().insertInto("amenities").columns("amenity", "hotel_id").values(a.value, this.id).build();
                conn.executeUpdate(query);
            } catch (Exception e) {
                SQLogger.getLogger().log(SQLogger.LogLevel.INFO, "Insert Amenities failed", e);
                return false;
            }
        }
        return true;
    }

    public boolean updateAmenities(Amenity... amenities) {
        List<Amenity> current = selectAmenities();

        for(Amenity a : amenities) {

        }
        return true;
    }
}
