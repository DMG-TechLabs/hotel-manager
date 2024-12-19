/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.dmgtechlabs.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.function.Function;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kdesp73
 */
public class GUIUtils {
	public static void logUserError(Component parent, String err){
		JOptionPane.showMessageDialog(parent, err, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static DefaultTableModel makeTableModel(Object[][]data, String[] columns, boolean cellEditable) {
		DefaultTableModel model = new DefaultTableModel(data, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return cellEditable;
			}
		};

		return model;
	}

	public static void addKeyBinding(JComponent component, String keyStroke, Runnable action) {
		// Get the input map for the component in the WHEN_IN_FOCUSED_WINDOW condition
		InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = component.getActionMap();
		
		KeyStroke stroke = KeyStroke.getKeyStroke(keyStroke);

		// Set the input and action map to trigger the Runnable
		inputMap.put(stroke, "refreshAction");
		actionMap.put("refreshAction", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				action.run();
			}
		});
	}

	public static void addWindowClosedListener(JFrame frame, Runnable action) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				action.run();
			}
		});
	}

	public static void showFrame(JFrame frame) {
		if (frame.isShowing()) {
			return;
		}

		frame.setVisible(true);
	}

	public static void commonSetup(JFrame frame) {
		frame.setLocationRelativeTo(null);
		GUIMethods.setFontFamilyRecursively(frame, "sans-serif", Font.PLAIN);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static KeyAdapter getFloatFormattedAdapter(String text) {
		return new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!(Character.isDigit(c) || c == '.' && !text.contains(".")) || (c == '.' && !text.isEmpty())) {
					e.consume();
				}
			}
		};
	}

	public static void setPlaceholder(JTextField textField, String placeholder) {
		textField.setText(placeholder);
		textField.setForeground(Color.GRAY);

		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals(placeholder)) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().isEmpty()) {
					textField.setForeground(Color.GRAY);
					textField.setText(placeholder);
				}
			}
		});
	}
}
