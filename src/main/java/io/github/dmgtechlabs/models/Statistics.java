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

/**
 *
 * @author kostas
 */
public class Statistics {
    public void getStatistics(int accountHotelFk, String start_date, String end_date) {
		System.out.println("ccccc");
		
			System.out.println("fffff");
			try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
				ResultSet rs = conn.callFunction("get_reservation_distribution", 1);
				SQLogger.getLogger().logResultSet(rs);
				rs.close();
			} catch (SQLException ex) {
				SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "get_reservation_distribution failed", ex);
			}

			try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
				System.out.println("2");
				var total_revenue = (float) conn.callFunctionValue("get_total_revenue", Types.REAL, accountHotelFk, start_date, end_date);
				System.out.println("Total Revenue: " + total_revenue);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "get_total_revenue failed", e);
			}
		
	}
}
