package io.github.dmgtechlabs.models;

import io.github.dmgtechlabs.Utils;
import io.github.dmgtechlabs.db.Dao;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;
import io.github.kdesp73.databridge.helpers.Adapter;
import io.github.kdesp73.databridge.helpers.QueryBuilder;
import io.github.kdesp73.databridge.helpers.SQLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		QUEEN(9);

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

	private int roomId;
	private int number;
	private int floor;
	private Type type;
	private int hotelId;
	private float price;
	private boolean occupied;

	public Room() {
	}

	public Room(int id) {
		this.roomId = id;
	}
	// For writing

	public Room(int floor, int number, Type type, float price, int hotelFk) {
		if (floor < 0) {
			throw new IllegalArgumentException("Room floor can't be a negative number");
		}
		this.floor = floor;

		if (number < 0) {
			throw new IllegalArgumentException("Room number can't be a negative number");
		}
		this.number = number;

		this.type = type;

		if (price <= 0) {
			throw new IllegalArgumentException("Room price can't be negative or zero");
		}
		this.price = price;

		this.hotelId = hotelFk;
		this.occupied = false;
	}
	// For loading

	public Room(int id, int floor, int number, Type type, float price, int hotelFk, boolean occupied) {
		this.roomId = id;
		this.floor = floor;
		this.number = number;
		this.type = type;
		this.price = price;
		this.hotelId = hotelFk;
		this.occupied = occupied;
	}

	public int getRoomId() {
		return roomId;
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

	public int getHotelId() {
		return hotelId;
	}

	public float getPrice() {
		return price;
	}

	@Override
	public boolean insert() {
		try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
			conn.callProcedure("insert_room", number, type.value, price, floor, hotelId);
		} catch (SQLException e) {
			SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Insert Room failed", e);
			return false;
		}
		return true;
	}

	/**
	 * Accepts exactly 4 values floor (int), number (int), type (int), price
	 * (float), occupied (boolean)
	 *
	 * update_room procedure should include the id (int) as the first parameter
	 *
	 * @param values
	 * @return success or not
	 */
	@Override
	public boolean update(Object... values) {
		if (values.length != 4) {
			throw new IllegalArgumentException(String.format("Invalid number of values (%s). Expected 4", values.length));
		}

		try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
			conn.callProcedure("update_room", Utils.appendFront(roomId, values));
		} catch (SQLException e) {
			SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Update Room failed", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean delete() {
		try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
			conn.callProcedure("delete_room", roomId);
		} catch (SQLException e) {
			SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Delete Room failed", e);
			return false;
		}
		return true;
	}

	public static Room parseRs(ResultSet rs) throws SQLException {
		return new Room(
			rs.getInt("id"),
			rs.getInt("floor"),
			rs.getInt("number"),
			Type.fromValue(rs.getInt("type")),
			rs.getFloat("price"),
			rs.getInt("room_hotel_fk"),
			rs.getBoolean("occupied")
		);
	}

	private static List<Room> select(String function, Object... values) {
		assert (function != null);
		assert (!function.isBlank());
		List<Room> result = new ArrayList<>();
		try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
			ResultSet rs = conn.callFunction(function, values);
			while (rs.next()) {
				result.add(parseRs(rs));
			}
			rs.close();
		} catch (Exception e) {
			SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Select " + function + " failed", e);
			return null;
		}
		return result;
	}

	public static List<Room> selectAll() {
		return select("select_all_rooms");
	}

	public static List<Room> selectById(int id, int hotelFk) {
		return select("select_rooms_by_id", id, hotelFk);
	}
	
	public static List<Room> selectByHotelId(int hotelFk) {
		return select("select_rooms_by_hotel_id", hotelFk);
	}
	
	public static List<Room> selectByReserved(int hotelFk) {
		return select("select_reserved_rooms", hotelFk);
	}

	public static List<Room> selectByFloor(int floor, int hotelFk) {
		return select("select_rooms_by_floor", floor, hotelFk);
	}

	public static List<Room> selectByType(Type type, int hotelFk) {
		return select("select_rooms_by_type", type.value, hotelFk);
	}

	public static List<Room> selectByPriceRange(float floor, float ceil) {
		return select("select_rooms_by_price_range", floor, ceil);
	}

	public boolean markOccupiedAs(boolean occ) {
		try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
			conn.executeUpdate(new QueryBuilder().update("room").set("occupied", occ).where("id = " + roomId).build());
		} catch (SQLException e) {
			SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Could not mark room occupancy", e);
			return false;
		}
		return true;
	}

	public String UIString() {
		return "Floor " + this.floor + " - " + "Room " + this.number + " - " + "Price " + this.price + "$" + " - " + "Type " + this.type.name();
	}
	
	@Override
	public String toString() {
		return this.floor + "-" + this.number + " $" + this.price + " (" + this.type + ")";
	}
	
	public String toHtml(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(Utils.HTML.header(1, "Room " + this.floor + "-" + this.number));
		sb.append(Utils.HTML.br());
		sb.append(Utils.HTML.header(2, "Info"));
		sb.append(Utils.HTML.ul(
			"Price: $" + this.price,
			"Type: " + this.type
		));
		
		return sb.toString();
	} 
}
