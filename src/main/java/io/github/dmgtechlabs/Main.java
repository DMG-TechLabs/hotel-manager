package io.github.dmgtechlabs;

import io.github.dmgtechlabs.gui.MainFrame;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
		runGUI();
	}
}
