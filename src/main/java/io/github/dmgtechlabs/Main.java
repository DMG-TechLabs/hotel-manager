package io.github.dmgtechlabs;

import io.github.dmgtechlabs.gui.MainFrame;
import io.github.dmgtechlabs.models.Hotel;
import io.github.dmgtechlabs.models.Room;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;
import io.github.kdesp73.databridge.helpers.QueryBuilder;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.math.BigInteger;
import java.sql.SQLException;

import io.github.dmgtechlabs.models.Customer;

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
            
            Customer c =  new Customer(1, "First name", "Last name", 123, "email");
            c.insert();
		try(PostgresConnection connection = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()){
			new Room(1, 1, Room.Type.DOUBLE, 40.2f, 1).insert();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
