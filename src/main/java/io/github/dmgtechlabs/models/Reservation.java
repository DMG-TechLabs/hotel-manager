package io.github.dmgtechlabs.models;

import io.github.dmgtechlabs.Utils;
import io.github.dmgtechlabs.db.Dao;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;
import io.github.kdesp73.databridge.helpers.Adapter;
import io.github.kdesp73.databridge.helpers.SQLogger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thanasis
 */
public class Reservation implements Dao {

	public enum Status {
		PENDING(1),
		ACCEPTED(2);

		private final int value;

		Status(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static Reservation.Status fromValue(int value) {
			for (Reservation.Status status : Reservation.Status.values()) {
				if (status.value == value) {
					return status;
				}
			}
			throw new IllegalArgumentException("Invalid value for Reservation Status: " + value);
		}
	}

	private int id;
	private int reservationRoomFk;
	private int reservationCustomerFk;
	private String checkIn;
	private String checkOut;
	private float cost;
	private Status status;

	public Reservation() {
	}

	public Reservation(int id, int reservationCustomerFk, int reservationRoomFk, String checkIn, String checkOut, float cost, Status status) {
		this.id = id;
		this.reservationCustomerFk = reservationCustomerFk;
		this.reservationRoomFk = reservationRoomFk;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.cost = cost;
		this.status = status;
	}

	public Reservation(int reservationCustomerFk, int reservationRoomFk, String checkIn, String checkOut, float cost, Status status) {
		this.reservationCustomerFk = reservationCustomerFk;
		this.reservationRoomFk = reservationRoomFk;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.cost = cost;
		this.status = status;
	}

	public Reservation(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getReservationRoomFk() {
		return reservationRoomFk;
	}

	public int getReservationCustomerFk() {
		return reservationCustomerFk;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public float getCost() {
		return cost;
	}

	public Status getStatus() {
		return status;
	}
	
	public int getCheckInYear() {
		String checkInYear = checkIn.substring(0, 3);
		
		return Integer.parseInt(checkInYear);
	}
	
	public int getCheckInMonth() {
		String checkInMonth = checkIn.substring(5, 6);
		
		return Integer.parseInt(checkInMonth);
	}
	
	public int getCheckInDay() {
		String checkInDay = checkIn.substring(8, 9);
		
		return Integer.parseInt(checkInDay);
	}
	
	public int getCheckOutYear() {
		String checkOutYear = checkOut.substring(0, 3);
		
		return Integer.parseInt(checkOutYear);
	}
	
	public int getCheckOutMonth() {
		String checkOutMonth = checkOut.substring(5, 6).replaceAll("0", "");
		
		return Integer.parseInt(checkOutMonth);
	}
	
	public int getCheckOutDay() {
		String checkoOutDay = checkOut.substring(8, 9).replaceAll("0", "");
		
		return Integer.parseInt(checkoOutDay);
	}

	@Override
	public boolean insert() {
		try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
			conn.callProcedure("insert_reservation", reservationCustomerFk, reservationRoomFk, checkIn, checkOut, cost, status.value);
		} catch (SQLException e) {
			SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Insert Reservation failed", e);
			return false;
		}
		return true;
	}

	/**
	 * Accepts exactly 1 value status (int)
	 *
	 * update_reservation procedure should include the id (int) as the first
	 * parameter
	 *
	 * @param values
	 * @return success or not
	 */
	@Override
	public boolean update(Object... values) {
		if (values.length != 1) {
			throw new IllegalArgumentException(String.format("Invalid number of values (%s). Expected 1", values.length));
		}

		try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
			conn.callProcedure("update_reservation", Utils.appendFront(id, values));
		} catch (SQLException e) {
			SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Update Reservation failed", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean delete() {
		try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
			conn.callProcedure("delete_reservation", id);
		} catch (SQLException e) {
			SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Delete Reservation failed", e);
			return false;
		}
		return true;
	}

	private static List<Reservation> select(String function, Object... values) {
		assert (function != null);
		assert (!function.isBlank());
		List<Reservation> result = new ArrayList<>();
		try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
			ResultSet rs = conn.callFunction(function, values);
			while (rs.next()) {
				result.add(new Reservation(
					rs.getInt("id"),
					rs.getInt("reservation_customer_fk"),
					rs.getInt("reservation_room_fk"),
					rs.getString("checkin"),
					rs.getString("checkout"),
					rs.getFloat("cost"),
					Status.fromValue(rs.getInt("status"))
				));
			}
			rs.close();
		} catch (Exception e) {
			SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Select " + function + " failed", e);
			return null;
		}
		return result;
	}

	public static List<Reservation> selectAll() {
		return select("select_all_reservations");
	}

	public static List<Reservation> selectByReservationId(int id) {
		return select("select_reservations_by_reservation_id", id);
	}
	
	public static List<Reservation> selectByHotel(int reservationRoomFk) {
		return select("select_reservations_by_hotel", reservationRoomFk);
	}
	
	public static List<Reservation> selectByReservationStatus(int status) {
		return select("select_reservations_by_status", status);
	}

	public static List<Reservation> selectByCustomerId(int customerId) {
		return select("select_reservations_by_customer_id", customerId);
	}

	public static List<Reservation> selectByRoomId(int roomId) {
		return select("select_reservations_by_room_id", roomId);
	}

	public static List<Reservation> selectByCheckInCheckOut(int reservationRoomFk, String checkInDate, String checkOutDate) {
		return select("select_reservations_by_check_in_check_out", reservationRoomFk, checkInDate, checkOutDate);
	}
	
	public String UIString() {
		return "Reservation ID: " + id + " (" + checkIn + " -- " + checkOut + ")";
	}
	
	public static String[] listToArray(List<Reservation> list) {
		String[] result = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i).UIString();
		}

		return result;
	}

}
