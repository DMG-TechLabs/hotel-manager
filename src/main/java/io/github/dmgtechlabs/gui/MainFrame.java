package io.github.dmgtechlabs.gui;

import io.github.dmgtechlabs.State;
import io.github.dmgtechlabs.models.User;

import io.github.dmgtechlabs.Filters;
import io.github.dmgtechlabs.models.Room;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class MainFrame extends javax.swing.JFrame {

	private State state = new State();

	private HelpFrame helpFrame;
	private AboutFrame aboutFrame;
	private HotelFrame hotelFrame;
	private RoomFrame roomFrame;
	private UserFrame userFrame;
	private RoomActionsFrame roomActionsFrame;

	private List<JCheckBox> filterTypeCheckboxes = new ArrayList<>();

	/**
	 * Creates new form MainFrame
	 *
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

		if (user.isManager()) {
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
		applyFilters();
		
		if(user.isGuest()) {
			// Allow only Searching for guest user
			this.tabbedPane.setEnabledAt(1, false);
			this.tabbedPane.setEnabledAt(2, false);
		}
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

        tabbedPane = new javax.swing.JTabbedPane();
        searchPanel = new javax.swing.JPanel();
        filtersPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        minPriceFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        maxPriceFormattedTextField = new javax.swing.JFormattedTextField();
        applyFiltersButton = new javax.swing.JButton();
        resetFiltersButton = new javax.swing.JButton();
        singleRoomFilterCheckbox = new javax.swing.JCheckBox();
        deluxeRoomFilterCheckbox = new javax.swing.JCheckBox();
        doubleRoomFilterCheckbox = new javax.swing.JCheckBox();
        familyRoomFilterCheckbox = new javax.swing.JCheckBox();
        twinRoomFilterCheckbox = new javax.swing.JCheckBox();
        studioRoomFilterCheckbox = new javax.swing.JCheckBox();
        suiteRoomFilterCheckbox = new javax.swing.JCheckBox();
        queenRoomFilterCheckbox = new javax.swing.JCheckBox();
        kingRoomFilterCheckbox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultFilterList = new javax.swing.JList<>();
        reservationsPanel = new javax.swing.JPanel();
        statisticsPanel = new javax.swing.JPanel();
        optionsPanel = new javax.swing.JPanel();
        changePasswordPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        currentPasswordField = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        newPasswordField = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        confirmNewPasswordField = new javax.swing.JPasswordField();
        resetPasswordsButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        miscButtonsPanel = new javax.swing.JPanel();
        exitButton = new javax.swing.JButton();
        switchUserButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        addMenu = new javax.swing.JMenu();
        addRoomMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        editRoomMenuItem = new javax.swing.JMenuItem();
        editUserMenuItem = new javax.swing.JMenuItem();
        deleteMenu = new javax.swing.JMenu();
        deleteRoomMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jLabel1.setText("Filters");

        minPriceFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel2.setText("Price");

        maxPriceFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

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

        deluxeRoomFilterCheckbox.setText("Deluxe");

        doubleRoomFilterCheckbox.setText("Double");

        familyRoomFilterCheckbox.setText("Family");

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

        resultFilterList.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        resultFilterList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        resultFilterList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resultFilterList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultFilterListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(resultFilterList);

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addComponent(filtersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(filtersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Search", searchPanel);

        javax.swing.GroupLayout reservationsPanelLayout = new javax.swing.GroupLayout(reservationsPanel);
        reservationsPanel.setLayout(reservationsPanelLayout);
        reservationsPanelLayout.setHorizontalGroup(
            reservationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1021, Short.MAX_VALUE)
        );
        reservationsPanelLayout.setVerticalGroup(
            reservationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 689, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Reservations", reservationsPanel);

        javax.swing.GroupLayout statisticsPanelLayout = new javax.swing.GroupLayout(statisticsPanel);
        statisticsPanel.setLayout(statisticsPanelLayout);
        statisticsPanelLayout.setHorizontalGroup(
            statisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1021, Short.MAX_VALUE)
        );
        statisticsPanelLayout.setVerticalGroup(
            statisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 689, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Statistics", statisticsPanel);

        jLabel3.setFont(new java.awt.Font("sansserif", 2, 18)); // NOI18N
        jLabel3.setText("Change Password");

        jLabel4.setText("Current Password");

        jLabel5.setText("New Password");

        jLabel6.setText("Confirm New Password");

        resetPasswordsButton.setText("Reset");
        resetPasswordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetPasswordsButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Confirm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout changePasswordPanelLayout = new javax.swing.GroupLayout(changePasswordPanel);
        changePasswordPanel.setLayout(changePasswordPanelLayout);
        changePasswordPanelLayout.setHorizontalGroup(
            changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changePasswordPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)
                        .addComponent(currentPasswordField)
                        .addComponent(newPasswordField)
                        .addComponent(confirmNewPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(changePasswordPanelLayout.createSequentialGroup()
                        .addComponent(resetPasswordsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))))
        );
        changePasswordPanelLayout.setVerticalGroup(
            changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changePasswordPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(currentPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmNewPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(changePasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(resetPasswordsButton))
                .addContainerGap())
        );

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        switchUserButton.setText("Switch User");

        javax.swing.GroupLayout miscButtonsPanelLayout = new javax.swing.GroupLayout(miscButtonsPanel);
        miscButtonsPanel.setLayout(miscButtonsPanelLayout);
        miscButtonsPanelLayout.setHorizontalGroup(
            miscButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(miscButtonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(miscButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(switchUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                .addContainerGap())
        );
        miscButtonsPanelLayout.setVerticalGroup(
            miscButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(miscButtonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(switchUserButton)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(miscButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(changePasswordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(625, Short.MAX_VALUE))
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(changePasswordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(miscButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(395, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Options", optionsPanel);

        addMenu.setText("Add");

        addRoomMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
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

        editUserMenuItem.setText("User");
        editUserMenuItem.setToolTipText("");
        editUserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUserMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(editUserMenuItem);

        jMenuBar1.add(editMenu);

        deleteMenu.setText("Delete");

        deleteRoomMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        deleteRoomMenuItem.setText("Room");
        deleteRoomMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRoomMenuItemActionPerformed(evt);
            }
        });
        deleteMenu.add(deleteRoomMenuItem);

        jMenuBar1.add(deleteMenu);

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
		this.roomFrame = new RoomFrame(this.state.activeHotelId);
		GUIUtils.showFrame(this.roomFrame);
    }//GEN-LAST:event_addRoomMenuItemActionPerformed

    private void editRoomMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRoomMenuItemActionPerformed
		this.roomFrame = new RoomFrame(this.state.activeHotelId, getSelectedRoom());
		GUIUtils.showFrame(this.roomFrame);
    }//GEN-LAST:event_editRoomMenuItemActionPerformed

	private void addUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
		this.userFrame = new UserFrame(this.state.LoggedInUser.getAccountHotelFk());
		GUIUtils.showFrame(this.userFrame);
	}

    private void editUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUserMenuItemActionPerformed
		this.userFrame = new UserFrame(this.state.LoggedInUser);
		GUIUtils.showFrame(this.userFrame);
    }//GEN-LAST:event_editUserMenuItemActionPerformed

	private void resetFilters() {
		this.minPriceFormattedTextField.setText("");
		this.maxPriceFormattedTextField.setText("");
		GUIUtils.setPlaceholder(this.minPriceFormattedTextField, "Min");
		GUIUtils.setPlaceholder(this.maxPriceFormattedTextField, "Max");

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
		Filters filters = new Filters(state);

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
		filters.addType(this.twinRoomFilterCheckbox.isSelected()   || noneSelected ? Room.Type.TWIN   : null);
		filters.addType(this.suiteRoomFilterCheckbox.isSelected()  || noneSelected ? Room.Type.SUITE  : null);
		filters.addType(this.deluxeRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.DELUXE : null);
		filters.addType(this.familyRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.FAMILY : null);
		filters.addType(this.studioRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.STUDIO : null);
		filters.addType(this.kingRoomFilterCheckbox.isSelected()   || noneSelected ? Room.Type.KING   : null);
		filters.addType(this.queenRoomFilterCheckbox.isSelected()  || noneSelected ? Room.Type.QUEEN  : null);

		return filters;
	}
	
	public Room getSelectedRoom(){
		Filters filters = getFilters();
		List<Room> rooms = filters.search();
		Room room = rooms.get(this.resultFilterList.getSelectedIndex());
		return room;
	}
	
    private void resetFiltersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetFiltersButtonActionPerformed
		resetFilters();
    }//GEN-LAST:event_resetFiltersButtonActionPerformed

	private void setRoomsToResultList(List<Room> rooms) {
		var model = new DefaultListModel<String>();
		for (Room room : rooms) {
			model.addElement(room.toString());
		}
		this.resultFilterList.setModel(model);
	}

	private void applyFilters(){
		Filters filters = getFilters();
		List<Room> rooms = filters.search();
		setRoomsToResultList(rooms);
	}
    private void applyFiltersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyFiltersButtonActionPerformed
		applyFilters();
    }//GEN-LAST:event_applyFiltersButtonActionPerformed

    private void resultFilterListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultFilterListMouseClicked
		if(evt.getButton() != MouseEvent.BUTTON3) return;
		
		Room room = getSelectedRoom();
		GUIUtils.showFrame(new RoomActionsFrame(room));
    }//GEN-LAST:event_resultFilterListMouseClicked

    private void deleteRoomMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRoomMenuItemActionPerformed
		int option = JOptionPane.showConfirmDialog(this, "This action cannot be reversed", "Delete Room?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(option != 0) return;
		
		
        Room room = getSelectedRoom();
		if(room == null) {
			JOptionPane.showMessageDialog(this, "Please select a room first", "Failure", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(room.delete()){
			JOptionPane.showMessageDialog(this, "Room " + room.getFloor() + "-" + room.getNumber() + " deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Could not delete room", "Failure", JOptionPane.ERROR_MESSAGE);
		}
    }//GEN-LAST:event_deleteRoomMenuItemActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        this.dispose();
		new StartingFrame().setVisible(true);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void resetPasswordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetPasswordsButtonActionPerformed
		this.currentPasswordField.setText("");
		this.newPasswordField.setText("");
		this.confirmNewPasswordField.setText("");
    }//GEN-LAST:event_resetPasswordsButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO: change password logic @Mokas
    }//GEN-LAST:event_jButton1ActionPerformed

	private javax.swing.JMenuItem addUserMenuItem;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenu addMenu;
    private javax.swing.JMenuItem addRoomMenuItem;
    private javax.swing.JButton applyFiltersButton;
    private javax.swing.JPanel changePasswordPanel;
    private javax.swing.JPasswordField confirmNewPasswordField;
    private javax.swing.JPasswordField currentPasswordField;
    private javax.swing.JMenu deleteMenu;
    private javax.swing.JMenuItem deleteRoomMenuItem;
    private javax.swing.JCheckBox deluxeRoomFilterCheckbox;
    private javax.swing.JCheckBox doubleRoomFilterCheckbox;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem editRoomMenuItem;
    private javax.swing.JMenuItem editUserMenuItem;
    private javax.swing.JButton exitButton;
    private javax.swing.JCheckBox familyRoomFilterCheckbox;
    private javax.swing.JPanel filtersPanel;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox kingRoomFilterCheckbox;
    private javax.swing.JFormattedTextField maxPriceFormattedTextField;
    private javax.swing.JFormattedTextField minPriceFormattedTextField;
    private javax.swing.JPanel miscButtonsPanel;
    private javax.swing.JPasswordField newPasswordField;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JCheckBox queenRoomFilterCheckbox;
    private javax.swing.JPanel reservationsPanel;
    private javax.swing.JButton resetFiltersButton;
    private javax.swing.JButton resetPasswordsButton;
    private javax.swing.JList<String> resultFilterList;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JCheckBox singleRoomFilterCheckbox;
    private javax.swing.JPanel statisticsPanel;
    private javax.swing.JCheckBox studioRoomFilterCheckbox;
    private javax.swing.JCheckBox suiteRoomFilterCheckbox;
    private javax.swing.JButton switchUserButton;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JCheckBox twinRoomFilterCheckbox;
    // End of variables declaration//GEN-END:variables
}
