package io.github.dmgtechlabs;

import io.github.dmgtechlabs.gui.LoginFrame;
import io.github.dmgtechlabs.gui.StartingFrame;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import io.github.kdesp73.databridge.helpers.SQLogger;

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
			new StartingFrame().setVisible(true);
		});
	}

	public static void main(String[] args) {
		runGUI();
	}
}
