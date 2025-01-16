/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.dmgtechlabs.models;

import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;
import io.github.kdesp73.databridge.helpers.SQLogger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kostas
 */

public class Statistics {
    public float totalRevenue;
    public Map<String, Integer> reservationDistribution = new HashMap<String, Integer>();
    public int totalRooms;
    public int occupiedRooms;
    public float occupancyRate;
    
    public void getStatistics(int accountHotelFk, String start_date, String end_date) {
			try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
				ResultSet rs = conn.callFunction("get_reservation_distribution", accountHotelFk);
				SQLogger.getLogger().logResultSet(rs);
                                while (rs.next()){
                                    reservationDistribution.put(rs.getString(0), rs.getInt(1));
                                }
				rs.close();
			} catch (SQLException ex) {
				SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "get_reservation_distribution failed", ex);
			} catch (Exception e) {System.out.println(e.getMessage());}

			try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
				this.totalRevenue = (float) conn.callFunctionValue("get_total_revenue", Types.REAL, accountHotelFk, start_date, end_date);
				System.out.println("Total Revenue: " + totalRevenue);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "get_total_revenue failed", e);
			} catch (Exception e) {System.out.println(e.getMessage());}
                        
                        try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
				ResultSet rs = conn.callFunction("get_occupancy_rate", accountHotelFk);
//				SQLogger.getLogger().logResultSet(rs);
                                while (rs.next()){
                                    this.totalRooms = rs.getInt("total_rooms");
                                    this.occupiedRooms = rs.getInt("occupied_rooms");
                                    this.occupancyRate = rs.getFloat("occupancy_rate");
                                }
				rs.close();
			} catch (SQLException ex) {
				SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "get_occupancy_rate failed", ex);
			} catch (Exception e) {System.out.println(e.getMessage());}
                        
                        System.out.println(this.totalRooms);
                        System.out.println(this.occupiedRooms);
                        System.out.println(this.occupancyRate);
                                
		
	}
}


;