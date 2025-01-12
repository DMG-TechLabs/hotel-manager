package io.github.dmgtechlabs.gui;

import io.github.dmgtechlabs.State;
import io.github.dmgtechlabs.models.User;

import io.github.dmgtechlabs.Filters;
import io.github.dmgtechlabs.models.Room;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;

public class MainFrame extends javax.swing.JFrame {

	private State state = new State();

	private HelpFrame helpFrame;
	private AboutFrame aboutFrame;
	private HotelFrame hotelFrame;
	private RoomFrame roomFrame;
	private UserFrame userFrame;
	private List<JCheckBox> filterTypeCheckboxes = new ArrayList<>();
	private int activeHotelId;
	
	/**
	 * Creates new form MainFrame
	 */
	public MainFrame(User user, int hotelId) {
		this.state.activeHotelId = hotelId;
		this.state.LoggedInUser = user;
		initComponents();
		this.setTitle("Hotel Manager");
		this.setLocationRelativeTo(null);
		this.helpFrame = new HelpFrame();
		this.aboutFrame = new AboutFrame();
		if (user.getType() == User.UserType.MANAGER.getValue()) {
			addUserMenuItem = new javax.swing.JMenuItem();
			addUserMenuItem.setText("User");
			addUserMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					addUserMenuItemActionPerformed(evt);
				}
			});
			addMenu.add(addUserMenuItem);

		}
	}

	public MainFrame(int hotelId) {
		initComponents();

		this.setTitle("Hotel Manager");
		this.setLocationRelativeTo(null);

		this.helpFrame = new HelpFrame();
		this.aboutFrame = new AboutFrame();

		setupFilters();
	}

	private void setupFilters() {
		GUIUtils.setPlaceholder(this.minPriceFormattedTextField, "Min");
		GUIUtils.setPlaceholder(this.maxPriceFormattedTextField, "Max");

		this.filterTypeCheckboxes.add(kingRoomFilterCheckbox);
		this.filterTypeCheckboxes.add(queenRoomFilterCheckbox);
		this.filterTypeCheckboxes.add(singleRoomFilterCheckbox);
		this.filterTypeCheckboxes.add(doubleRoomFilterCheckbox);
		this.filterTypeCheckboxes.add(twinRoomFilterCheckbox);
		this.filterTypeCheckboxes.add(suiteRoomFilterCheckbox);
		this.filterTypeCheckboxes.add(deluxeRoomFilterCheckbox);
		this.filterTypeCheckboxes.add(studioRoomFilterCheckbox);
		this.filterTypeCheckboxes.add(familyRoomFilterCheckbox);
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        addMenu = new javax.swing.JMenu();
        addRoomMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        editRoomMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addMenu.setText("Add");

        addRoomMenuItem.setText("Room");
        addRoomMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRoomMenuItemActionPerformed(evt);
            }
        });
        addMenu.add(addRoomMenuItem);

        jMenuBar1.add(addMenu);

        editMenu.setText("Edit");

        editRoomMenuItem.setText("Room");
        editRoomMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editRoomMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(editRoomMenuItem);

        jMenuBar1.add(editMenu);

        helpMenu.setText("Help");

        helpMenuItem.setText("Help");
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(helpMenuItem);

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1132, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 689, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemActionPerformed
		if (helpFrame.isShowing()) {
			return;
		}

		helpFrame.setVisible(true);
    }//GEN-LAST:event_helpMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
		if (aboutFrame.isShowing()) {
			return;
		}

		aboutFrame.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void addRoomMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRoomMenuItemActionPerformed
		this.roomFrame = new RoomFrame(this.activeHotelId);
		GUIUtils.showFrame(this.roomFrame);
    }//GEN-LAST:event_addRoomMenuItemActionPerformed

    private void editRoomMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRoomMenuItemActionPerformed
		this.roomFrame = new RoomFrame(this.state.activeHotelId, null); // TODO: get selected room
		GUIUtils.showFrame(this.roomFrame);
    }//GEN-LAST:event_editRoomMenuItemActionPerformed

	 private void addUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                
		this.userFrame = new UserFrame(this.state.LoggedInUser.getAccountHotelFk());
		GUIUtils.showFrame(this.userFrame);
    }
	
    private void editUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUserMenuItemActionPerformed
		this.userFrame = new UserFrame(this.state.LoggedInUser); // TODO: get selected room
		GUIUtils.showFrame(this.userFrame);
    }//GEN-LAST:event_editUserMenuItemActionPerformed

    private void maxPriceFormattedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxPriceFormattedTextFieldActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_maxPriceFormattedTextFieldActionPerformed

	private void resetFilters() {
		this.minPriceFormattedTextField.setText("");
		this.maxPriceFormattedTextField.setText("");

		this.singleRoomFilterCheckbox.setSelected(false);
		this.doubleRoomFilterCheckbox.setSelected(false);
		this.twinRoomFilterCheckbox.setSelected(false);
		this.suiteRoomFilterCheckbox.setSelected(false);
		this.deluxeRoomFilterCheckbox.setSelected(false);
		this.familyRoomFilterCheckbox.setSelected(false);
		this.studioRoomFilterCheckbox.setSelected(false);
		this.queenRoomFilterCheckbox.setSelected(false);
		this.kingRoomFilterCheckbox.setSelected(false);
	}

	private Filters getFilters() {
		Filters filters = new Filters();

		float min, max;
		try {
			min = Float.parseFloat(this.minPriceFormattedTextField.getText());
		} catch (NumberFormatException e) {
			min = 0;
		}
		try {
			max = Float.parseFloat(this.maxPriceFormattedTextField.getText());
		} catch (NumberFormatException e) {
			max = 1000;
		}

		filters.setRange(min, max);

		boolean noneSelected = true;
		for (JCheckBox c : this.filterTypeCheckboxes) {
			if (c.isSelected()) {
				noneSelected = false;
				break;
			}
		}

		filters.addType(this.singleRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.SINGLE : null);
		filters.addType(this.doubleRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.DOUBLE : null);
		filters.addType(this.twinRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.TWIN : null);
		filters.addType(this.suiteRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.SUITE : null);
		filters.addType(this.deluxeRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.DELUXE : null);
		filters.addType(this.familyRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.FAMILY : null);
		filters.addType(this.studioRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.STUDIO : null);
		filters.addType(this.kingRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.KING : null);
		filters.addType(this.queenRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.QUEEN : null);

		return filters;
	}
    private void resetFiltersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetFiltersButtonActionPerformed
		resetFilters();
    }//GEN-LAST:event_resetFiltersButtonActionPerformed

    private void singleRoomFilterCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singleRoomFilterCheckboxActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_singleRoomFilterCheckboxActionPerformed

    private void familyRoomFilterCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_familyRoomFilterCheckboxActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_familyRoomFilterCheckboxActionPerformed

	private void setRoomsToResultList(List<Room> rooms) {
		var model = new DefaultListModel<String>();
		for (Room room : rooms) {
			model.addElement(room.toString());
		}
		this.resultFilterList.setModel(model);
	}

    private void applyFiltersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyFiltersButtonActionPerformed
		Filters filters = getFilters();

		List<Room> rooms = filters.search();
		setRoomsToResultList(rooms);
    }//GEN-LAST:event_applyFiltersButtonActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame(-1).setVisible(true);
			}
		});
	}

	private javax.swing.JMenuItem addUserMenuItem;
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyFiltersButton;
    private javax.swing.JButton resetFiltersButton;
    private javax.swing.JCheckBox deluxeRoomFilterCheckbox;
    private javax.swing.JCheckBox doubleRoomFilterCheckbox;
    private javax.swing.JCheckBox familyRoomFilterCheckbox;
    private javax.swing.JCheckBox kingRoomFilterCheckbox;
    private javax.swing.JCheckBox queenRoomFilterCheckbox;
    private javax.swing.JCheckBox singleRoomFilterCheckbox;
    private javax.swing.JCheckBox studioRoomFilterCheckbox;
    private javax.swing.JCheckBox suiteRoomFilterCheckbox;
    private javax.swing.JCheckBox twinRoomFilterCheckbox;
    private javax.swing.JFormattedTextField maxPriceFormattedTextField;
    private javax.swing.JFormattedTextField minPriceFormattedTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> resultFilterList;
    private javax.swing.JMenu addMenu;
    private javax.swing.JMenu deleteMenu;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem addRoomMenuItem;
    private javax.swing.JMenuItem editRoomMenuItem;
    private javax.swing.JMenuItem editUserMenuItem;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JPanel filtersPanel;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
