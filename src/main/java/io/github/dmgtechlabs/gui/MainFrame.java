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
	 * @param user
	 * @param hotelId
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

        aboutMenuItem = new javax.swing.JMenuItem();
        addMenu = new javax.swing.JMenu();
        addRoomMenuItem = new javax.swing.JMenuItem();
        applyFiltersButton = new javax.swing.JButton();
        deluxeRoomFilterCheckbox = new javax.swing.JCheckBox();
        doubleRoomFilterCheckbox = new javax.swing.JCheckBox();
        editMenu = new javax.swing.JMenu();
        editRoomMenuItem = new javax.swing.JMenuItem();
        familyRoomFilterCheckbox = new javax.swing.JCheckBox();
        filtersPanel = new javax.swing.JPanel();
        helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        kingRoomFilterCheckbox = new javax.swing.JCheckBox();
        maxPriceFormattedTextField = new javax.swing.JFormattedTextField();
        minPriceFormattedTextField = new javax.swing.JFormattedTextField();
        queenRoomFilterCheckbox = new javax.swing.JCheckBox();
        resetFiltersButton = new javax.swing.JButton();
        resultFilterList = new javax.swing.JList<>();
        searchPanel = new javax.swing.JPanel();
        singleRoomFilterCheckbox = new javax.swing.JCheckBox();
        studioRoomFilterCheckbox = new javax.swing.JCheckBox();
        suiteRoomFilterCheckbox = new javax.swing.JCheckBox();
        tabbedPane = new javax.swing.JTabbedPane();
        twinRoomFilterCheckbox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    
        tabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jLabel1.setText("Filters");

        minPriceFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel2.setText("Price");

        maxPriceFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        maxPriceFormattedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxPriceFormattedTextFieldActionPerformed(evt);
            }
        });

        applyFiltersButton.setText("Apply");
        applyFiltersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyFiltersButtonActionPerformed(evt);
            }
        });

        resetFiltersButton.setText("Reset");
        resetFiltersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetFiltersButtonActionPerformed(evt);
            }
        });

        singleRoomFilterCheckbox.setText("Single");
        singleRoomFilterCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singleRoomFilterCheckboxActionPerformed(evt);
            }
        });

        deluxeRoomFilterCheckbox.setText("Deluxe");

        doubleRoomFilterCheckbox.setText("Double");

        familyRoomFilterCheckbox.setText("Family");
        familyRoomFilterCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                familyRoomFilterCheckboxActionPerformed(evt);
            }
        });

        twinRoomFilterCheckbox.setText("Twin");

        studioRoomFilterCheckbox.setText("Studio");

        suiteRoomFilterCheckbox.setText("Suite");

        queenRoomFilterCheckbox.setText("Queen");

        kingRoomFilterCheckbox.setText("King");

        javax.swing.GroupLayout filtersPanelLayout = new javax.swing.GroupLayout(filtersPanel);
        filtersPanel.setLayout(filtersPanelLayout);
        filtersPanelLayout.setHorizontalGroup(
            filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filtersPanelLayout.createSequentialGroup()
                .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(filtersPanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(minPriceFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(singleRoomFilterCheckbox)
                            .addComponent(doubleRoomFilterCheckbox)
                            .addComponent(twinRoomFilterCheckbox)
                            .addComponent(suiteRoomFilterCheckbox)
                            .addComponent(deluxeRoomFilterCheckbox))
                        .addGap(18, 18, 18)
                        .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maxPriceFormattedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addGroup(filtersPanelLayout.createSequentialGroup()
                                .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(studioRoomFilterCheckbox)
                                    .addComponent(familyRoomFilterCheckbox)
                                    .addComponent(queenRoomFilterCheckbox)
                                    .addComponent(kingRoomFilterCheckbox))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, filtersPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetFiltersButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(applyFiltersButton)))
                .addContainerGap())
        );
        filtersPanelLayout.setVerticalGroup(
            filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filtersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minPriceFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxPriceFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(singleRoomFilterCheckbox)
                    .addComponent(familyRoomFilterCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doubleRoomFilterCheckbox)
                    .addComponent(studioRoomFilterCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(twinRoomFilterCheckbox)
                    .addComponent(queenRoomFilterCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(suiteRoomFilterCheckbox)
                    .addComponent(kingRoomFilterCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deluxeRoomFilterCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(applyFiltersButton)
                    .addComponent(resetFiltersButton))
                .addContainerGap())
        );

        resultFilterList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(resultFilterList);

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addComponent(filtersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(filtersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Search", searchPanel);

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
                .addComponent(tabbedPane)
                );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tabbedPane)
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
				new MainFrame(null, -1).setVisible(true);
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
