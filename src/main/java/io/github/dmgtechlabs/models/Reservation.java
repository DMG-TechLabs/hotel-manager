package io.github.dmgtechlabs.models;

import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.OracleConnection;
import io.github.kdesp73.databridge.helpers.Adapter;
import io.github.kdesp73.databridge.helpers.SQLogger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author thanasis
 */
public class Reservation {

	public static enum Status {
		PENDING, ACCEPTED, DECLINED
	}
	
	private int roomId;
	private int customerId;
	private String checkInDate;
	private String checkOutDate;
	private float cost;
	private Status status;

	public Reservation() {
	}

	public Reservation(int roomId, int customerId, String checkInDate, String checkOutDate, float cost, Status status) {
		this.roomId = roomId;
		this.customerId = customerId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.cost = cost;
		this.status = status;
	}
	
	public int getRoomId(){
		return roomId;
	}
	
	public int getCustomerId(){
		return customerId;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public static Status int2Status(int status) {
		for (Status g : Status.values()) {
			if (status == g.ordinal()) {
				return g;
			}
		}
		return null;
	}
	
    public boolean insert() {
        try(OracleConnection conn = (OracleConnection) AvailableConnections.ORACLE.getConnection()) {
            
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
    public boolean update(Object... values) {
        if(values.length != 4)
            throw new IllegalArgumentException(String.format("Invalid number of values (%s). Expected 4", values.length));

        try(OracleConnection conn = (OracleConnection) AvailableConnections.ORACLE.getConnection()) {
            
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Update Room failed", e);
            return false;
        }
        return true;
    }

    public boolean delete() {
        try(OracleConnection conn = (OracleConnection) AvailableConnections.ORACLE.getConnection()){
            
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Delete Room failed", e);
            return false;
        }
        return true;
    }

    private static List<Reservation> select(String function, Object... values){
        assert(function != null);
        assert(!function.isBlank());
        try(OracleConnection conn = (OracleConnection) AvailableConnections.ORACLE.getConnection()) {
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
	
	public static List<Reservation> selectByCustomerId(int customerId) {
        return select("select_reservations_by_customer_id");
    }
	
	public static List<Reservation> selectByRoomId(int roomId) {
        return select("select_reservations_by_room_id");
    }
	
	public static List<Reservation> selectByCheckInCheckOut(String checkInDate, String checkOutDate) {
        return select("select_reservations_by_check_in_check_out");
    }

}
