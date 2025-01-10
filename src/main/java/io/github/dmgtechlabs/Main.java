package io.github.dmgtechlabs;

import io.github.dmgtechlabs.gui.MainFrame;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.sql.SQLException;

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
			connection.execute("SET search_path TO hoteldb;");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
}
