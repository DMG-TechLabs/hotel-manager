package io.github.dmgtechlabs.models;

import io.github.dmgtechlabs.Utils;
import io.github.dmgtechlabs.db.Dao;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;
import io.github.kdesp73.databridge.helpers.Adapter;
import io.github.kdesp73.databridge.helpers.SQLogger;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	private int reservationId;
	private int roomId;
	private int customerId;
	private String checkInDate;
	private String checkOutDate;
	private float cost;
	private Status status;

	public Reservation() {
	}

	public Reservation(int reservationId, int roomId, int customerId, String checkInDate, String checkOutDate, float cost, Status status) {
		this.reservationId = reservationId;
		this.roomId = roomId;
		this.customerId = customerId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.cost = cost;
		this.status = status;
	}

	public int getReservationId() {
		return reservationId;
	}

	public int getRoomId() {
		return roomId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public float getCost() {
		return cost;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public boolean insert() {
		try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
			conn.callProcedure("insert_reservation", customerId, roomId, checkInDate, checkOutDate, cost, status.value);
		} catch (SQLException e) {
			SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Insert Reservation failed", e);
			return false;
		}
		return true;
	}

	/**
	 * Accepts exactly 4 values checkInDate (String), checkOutDate (String),
	 * cost (float), status (int)
	 *
	 * update_reservation procedure should include the id (int) as the first
	 * parameter
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
			conn.callProcedure("update_rerservation", Utils.appendFront(reservationId, values));
		} catch (SQLException e) {
			SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Update Reservation failed", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean delete() {
		try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
			conn.callProcedure("delete_reservation", reservationId);
		} catch (SQLException e) {
			SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Delete Reservation failed", e);
			return false;
		}
		return true;
	}

	private static List<Reservation> select(String function, Object... values) {
		assert (function != null);
		assert (!function.isBlank());
		try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
			ResultSet rs = conn.callFunction(function, values);
			return Adapter.load(rs, Reservation.class);
		} catch (Exception e) {
			SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Select " + function + " failed", e);
			return null;
		}
	}

	public static List<Reservation> selectAll() {
		return select("select_all_reservations");
	}

	public static List<Reservation> selectByReservationId(int id) {
		return select("select_reservations_by_reservation_id", id);
	}

	public static List<Reservation> selectByCustomerId(int customerId) {
		return select("select_reservations_by_customer_id", customerId);
	}

	public static List<Reservation> selectByRoomId(int roomId) {
		return select("select_reservations_by_room_id", roomId);
	}

	public static List<Reservation> selectByCheckInCheckOut(String checkInDate, String checkOutDate) {
		return select("select_reservations_by_check_in_check_out", checkInDate, checkOutDate);
	}

}
