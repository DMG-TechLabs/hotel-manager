package io.github.dmgtechlabs;

import io.github.dmgtechlabs.gui.MainFrame;
import io.github.dmgtechlabs.models.Reservation;
import io.github.dmgtechlabs.models.Reservation.Status;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author kdesp73
 */
public class Main {
	private static void runGUI() {
		System.setProperty("sun.java2d.uiScale", "1");
		try {
			UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
		} catch (UnsupportedLookAndFeelException ex) {
			System.err.println("Failed to initialize LaF");
		}
		java.awt.EventQueue.invokeLater(() -> {
			new MainFrame(-1).setVisible(true);
		});
	}

	public static void main(String[] args) {
		try(PostgresConnection connection = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()){
			//connection.execute("SET search_path TO hoteldb;");
			new Reservation(123, 2, "2025-01-10", "2025-01-13", 50.0f, Status.PENDING).insert();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
}
