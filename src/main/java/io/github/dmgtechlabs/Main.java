package io.github.dmgtechlabs;

import io.github.dmgtechlabs.gui.LoginFrame;
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
//			new MainFrame(new User(26, "test", "test", User.UserType.MANAGER.getValue(), 1), 1).setVisible(true);
                        new LoginFrame(1).setVisible(true);
		});
	}

	public static void main(String[] args) {
		runGUI();
//		boolean done = new Hotel("TestName", "TestAddress", 6969696969L).insert();
//		System.out.println(done);
	}
}
