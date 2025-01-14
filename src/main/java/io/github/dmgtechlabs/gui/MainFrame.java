package io.github.dmgtechlabs.gui;

import io.github.dmgtechlabs.State;
import io.github.dmgtechlabs.models.User;

import io.github.dmgtechlabs.Filters;
import io.github.dmgtechlabs.models.Hotel;
import io.github.dmgtechlabs.models.Reservation;
import io.github.dmgtechlabs.models.Room;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class MainFrame extends javax.swing.JFrame {

	private State state = new State();

	private HelpFrame helpFrame;
	private AboutFrame aboutFrame;
	private HotelFrame hotelFrame;
	private RoomFrame roomFrame;
	private UserFrame userFrame;
	private RoomActionsFrame roomActionsFrame;
	private ReservationFrame reservationFrame;
	private CreateReservationFrame createReservationFrame;

	private List<JCheckBox> filterTypeCheckboxes = new ArrayList<>();
	private List<Reservation> pendingReservations;
	private List<Reservation> acceptedReservations;
	private List<Room> rooms;
	private List<Hotel> hotels;


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
		
		this.hotels = Hotel.selectById(hotelId);
		this.hotelNameLabel.setText(this.hotels.get(0).getName());

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

		if (user.isGuest()) {
			// Allow only Searching for guest user
			this.tabbedPane.setEnabledAt(2, false);
			this.tabbedPane.setEnabledAt(3, false);
		} else if(user.isEmployee()){
			// Hide statistics from employees
			this.tabbedPane.setEnabledAt(3, false);
		}

		this.pendingReservations = Reservation.selectByReservationStatus(1);
		this.pendingList.setListData(Reservation.listToArray(this.pendingReservations.stream().map(reservation -> (Reservation) reservation).toList()));

		this.acceptedReservations = Reservation.selectByReservationStatus(2);
		this.acceptedList.setListData(Reservation.listToArray(this.acceptedReservations.stream().map(reservation -> (Reservation) reservation).toList()));
	}

	public MainFrame() {
		initComponents();
		this.setTitle("Hotel Manager");
		this.setLocationRelativeTo(null);

		this.helpFrame = new HelpFrame();
		this.aboutFrame = new AboutFrame();

		setupFilters();
		applyFilters();

		this.pendingReservations = Reservation.selectByReservationStatus(1);
		this.pendingList.setListData(Reservation.listToArray(this.pendingReservations.stream().map(reservation -> (Reservation) reservation).toList()));

		this.acceptedReservations = Reservation.selectByReservationStatus(2);
		this.acceptedList.setListData(Reservation.listToArray(this.acceptedReservations.stream().map(reservation -> (Reservation) reservation).toList()));
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
        homePanel = new javax.swing.JPanel();
        welcomeLabel = new javax.swing.JLabel();
        hotelNameLabel = new javax.swing.JLabel();
        hotelLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
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
        pendingLabel = new javax.swing.JLabel();
        acceptedLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pendingList = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        acceptedList = new javax.swing.JList<>();
        declineButton = new javax.swing.JButton();
        acceptButton = new javax.swing.JButton();
        showInfoButton = new javax.swing.JButton();
        statisticsPanel = new javax.swing.JPanel();
        startDate = new javax.swing.JTextField();
        endDate = new javax.swing.JTextField();
        getStatistics = new javax.swing.JButton();
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
        addReservationMenuItem = new javax.swing.JMenuItem();
        addCustomerMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        editRoomMenuItem = new javax.swing.JMenuItem();
        editUserMenuItem = new javax.swing.JMenuItem();
        editCustomerMenuItem = new javax.swing.JMenuItem();
        deleteMenu = new javax.swing.JMenu();
        deleteRoomMenuItem = new javax.swing.JMenuItem();
        deleteCustomerMenuItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        viewCustomerMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPaneStateChanged(evt);
            }
        });

        welcomeLabel.setFont(new java.awt.Font("URW Gothic", 1, 60)); // NOI18N
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        welcomeLabel.setText("WELCOME TO");

        hotelNameLabel.setFont(new java.awt.Font("URW Gothic", 1, 60)); // NOI18N
        hotelNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        hotelNameLabel.setText("jLabel8");

        hotelLabel.setFont(new java.awt.Font("URW Gothic", 1, 60)); // NOI18N
        hotelLabel.setText("HOTEL");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/output-onlinepngtools.png"))); // NOI18N

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hotelLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hotelNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(welcomeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jLabel10))
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(welcomeLabel)
                        .addGap(55, 55, 55)
                        .addComponent(hotelNameLabel)
                        .addGap(55, 55, 55)
                        .addComponent(hotelLabel)))
                .addContainerGap(223, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Home", homePanel);

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Search", searchPanel);

        pendingLabel.setFont(new java.awt.Font("URW Gothic", 0, 36)); // NOI18N
        pendingLabel.setText("Pending");

        acceptedLabel.setFont(new java.awt.Font("URW Gothic", 0, 36)); // NOI18N
        acceptedLabel.setText("Accepted");

        jScrollPane3.setViewportView(pendingList);

        jScrollPane5.setViewportView(acceptedList);

        declineButton.setText("Decline");
        declineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineButtonActionPerformed(evt);
            }
        });

        acceptButton.setText("Accept");
        acceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptButtonActionPerformed(evt);
            }
        });

        showInfoButton.setText("Show info");
        showInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showInfoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reservationsPanelLayout = new javax.swing.GroupLayout(reservationsPanel);
        reservationsPanel.setLayout(reservationsPanelLayout);
        reservationsPanelLayout.setHorizontalGroup(
            reservationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationsPanelLayout.createSequentialGroup()
                .addGroup(reservationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reservationsPanelLayout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(pendingLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(reservationsPanelLayout.createSequentialGroup()
                        .addContainerGap(118, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(reservationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(reservationsPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(showInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(reservationsPanelLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(reservationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acceptButton)
                                    .addComponent(declineButton))))
                        .addGap(18, 18, 18)))
                .addGroup(reservationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reservationsPanelLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(acceptedLabel))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
        );
        reservationsPanelLayout.setVerticalGroup(
            reservationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationsPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(reservationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pendingLabel)
                    .addComponent(acceptedLabel))
                .addGap(18, 18, 18)
                .addGroup(reservationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                    .addGroup(reservationsPanelLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(showInfoButton)
                        .addGap(63, 63, 63)
                        .addComponent(acceptButton)
                        .addGap(18, 18, 18)
                        .addComponent(declineButton))
                    .addComponent(jScrollPane3))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Reservations", reservationsPanel);

        startDate.setText("2025-01-01");

        endDate.setText("2025-02-01");

        getStatistics.setText("jButton2");
        getStatistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getStatisticsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout statisticsPanelLayout = new javax.swing.GroupLayout(statisticsPanel);
        statisticsPanel.setLayout(statisticsPanelLayout);
        statisticsPanelLayout.setHorizontalGroup(
            statisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statisticsPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(statisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(getStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endDate, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(startDate))
                .addContainerGap(703, Short.MAX_VALUE))
        );
        statisticsPanelLayout.setVerticalGroup(
            statisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statisticsPanelLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(getStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(488, Short.MAX_VALUE))
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
        switchUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchUserButtonActionPerformed(evt);
            }
        });

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
                .addContainerGap(432, Short.MAX_VALUE))
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

        addReservationMenuItem.setText("Reservation");
        addReservationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addReservationMenuItemActionPerformed(evt);
            }
        });
        addMenu.add(addReservationMenuItem);

        addCustomerMenuItem.setText("Customer");
        addCustomerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustomerMenuItemActionPerformed(evt);
            }
        });
        addMenu.add(addCustomerMenuItem);

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

        editCustomerMenuItem.setText("Customer");
        editCustomerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCustomerMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(editCustomerMenuItem);

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

        deleteCustomerMenuItem.setText("Customer");
        deleteCustomerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCustomerMenuItemActionPerformed(evt);
            }
        });
        deleteMenu.add(deleteCustomerMenuItem);

        jMenuBar1.add(deleteMenu);

        viewMenu.setText("View");

        viewCustomerMenuItem.setText("Customer");
        viewCustomerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCustomerMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(viewCustomerMenuItem);

        jMenuBar1.add(viewMenu);

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
		filters.addType(this.twinRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.TWIN : null);
		filters.addType(this.suiteRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.SUITE : null);
		filters.addType(this.deluxeRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.DELUXE : null);
		filters.addType(this.familyRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.FAMILY : null);
		filters.addType(this.studioRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.STUDIO : null);
		filters.addType(this.kingRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.KING : null);
		filters.addType(this.queenRoomFilterCheckbox.isSelected() || noneSelected ? Room.Type.QUEEN : null);

		return filters;
	}

	public Room getSelectedRoom() {
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

	private void applyFilters() {
		Filters filters = getFilters();
		List<Room> rooms = filters.search();
		setRoomsToResultList(rooms);
	}
    private void applyFiltersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyFiltersButtonActionPerformed
		applyFilters();
    }//GEN-LAST:event_applyFiltersButtonActionPerformed

    private void resultFilterListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultFilterListMouseClicked
		if (evt.getButton() != MouseEvent.BUTTON3) {
			return;
		}

		Room room = getSelectedRoom();
		GUIUtils.showFrame(new RoomActionsFrame(room));
    }//GEN-LAST:event_resultFilterListMouseClicked

    private void deleteRoomMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRoomMenuItemActionPerformed
		int option = JOptionPane.showConfirmDialog(this, "This action cannot be reversed", "Delete Room?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (option != 0) {
			return;
		}

		Room room = getSelectedRoom();
		if (room == null) {
			JOptionPane.showMessageDialog(this, "Please select a room first", "Failure", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (room.delete()) {
			JOptionPane.showMessageDialog(this, "Room " + room.getFloor() + "-" + room.getNumber() + " deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Could not delete room", "Failure", JOptionPane.ERROR_MESSAGE);
		}
    }//GEN-LAST:event_deleteRoomMenuItemActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
		this.dispose();
		new StartingFrame().setVisible(true);
    }//GEN-LAST:event_exitButtonActionPerformed

	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(CreateReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(CreateReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(CreateReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(CreateReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

    private void resetPasswordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetPasswordsButtonActionPerformed
		this.currentPasswordField.setText("");
		this.newPasswordField.setText("");
		this.confirmNewPasswordField.setText("");
    }//GEN-LAST:event_resetPasswordsButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String newPassword = this.newPasswordField.getText();
                
        if(!this.state.LoggedInUser.isGuest() && 
                this.currentPasswordField.getText() == this.state.LoggedInUser.getPassword() && 
                newPassword == this.confirmNewPasswordField.getText() &&
                newPassword != "" 
           ){
            this.state.LoggedInUser.update(this.state.LoggedInUser.getUsername(), newPassword, this.state.LoggedInUser.getType(), this.state.LoggedInUser.getAccountHotelFk());
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptButtonActionPerformed
		if (pendingList.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(this, "Select a pending reservation first", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to accept this reservation?", "Accept", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			this.pendingReservations.get(pendingList.getSelectedIndex()).update(2);
			
			this.rooms = Room.selectById(this.pendingReservations.get(pendingList.getSelectedIndex()).getReservationRoomFk(), this.state.activeHotelId);
			this.rooms.get(0).markOccupiedAs(true);

			this.pendingReservations = Reservation.selectByReservationStatus(1);
			this.pendingList.setListData(Reservation.listToArray(this.pendingReservations.stream().map(reservation -> (Reservation) reservation).toList()));

			this.acceptedReservations = Reservation.selectByReservationStatus(2);
			this.acceptedList.setListData(Reservation.listToArray(this.acceptedReservations.stream().map(reservation -> (Reservation) reservation).toList()));
		}
		
		pendingList.clearSelection();
		acceptedList.clearSelection();
    }//GEN-LAST:event_acceptButtonActionPerformed

    private void declineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineButtonActionPerformed
		if (pendingList.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(this, "Select a pending reservation first", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to decline this reservation?\nThis means that this will be deleted!", "Accept", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			this.pendingReservations.get(pendingList.getSelectedIndex()).delete();

			this.pendingReservations = Reservation.selectByReservationStatus(1);
			this.pendingList.setListData(Reservation.listToArray(this.pendingReservations.stream().map(reservation -> (Reservation) reservation).toList()));

			this.acceptedReservations = Reservation.selectByReservationStatus(2);
			this.acceptedList.setListData(Reservation.listToArray(this.acceptedReservations.stream().map(reservation -> (Reservation) reservation).toList()));
		}
		
		pendingList.clearSelection();
		acceptedList.clearSelection();
    }//GEN-LAST:event_declineButtonActionPerformed

    private void showInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showInfoButtonActionPerformed
		if (reservationFrame != null) {
			reservationFrame.dispose();
		}

		if (pendingList.getSelectedIndex() < 0 && acceptedList.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(this, "Select a reservation first", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (pendingList.getSelectedIndex() < 0) {
			String selectedValue = acceptedList.getSelectedValue();

			reservationFrame = new ReservationFrame(this.state.activeHotelId, selectedValue);
			reservationFrame.setVisible(true);
			acceptedList.clearSelection();
			return;
		}

		String selectedValue = pendingList.getSelectedValue();

		reservationFrame = new ReservationFrame(this.state.activeHotelId, selectedValue);
		reservationFrame.setVisible(true);
		pendingList.clearSelection();
    }//GEN-LAST:event_showInfoButtonActionPerformed


    private void tabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneStateChanged
        if(tabbedPane.getSelectedIndex() == 2 && this.state.LoggedInUser.getType() == User.UserType.MANAGER.getValue()){
            
        }
    }//GEN-LAST:event_tabbedPaneStateChanged

    private void getStatisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getStatisticsActionPerformed
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
        
        String sDate = startDate.getText();
        String eDate = endDate.getText();
            try {
                Date sdate = formatter.parse(sDate);
                Date edate = formatter.parse(eDate);
                System.out.println("bbbbb");
                this.state.LoggedInUser.getStatistics(sdate, edate);
            } catch (ParseException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_getStatisticsActionPerformed

    private void switchUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchUserButtonActionPerformed
        this.dispose();
        this.state.LoggedInUser = null;
        new LoginFrame(this.state.activeHotelId).setVisible(true);
    }//GEN-LAST:event_switchUserButtonActionPerformed

    private void addReservationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addReservationMenuItemActionPerformed
       this.createReservationFrame = new CreateReservationFrame(this.state.activeHotelId);
	   GUIUtils.showFrame(this.createReservationFrame);
    }//GEN-LAST:event_addReservationMenuItemActionPerformed

    private void editCustomerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCustomerMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editCustomerMenuItemActionPerformed

    private void addCustomerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustomerMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addCustomerMenuItemActionPerformed

    private void viewCustomerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCustomerMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewCustomerMenuItemActionPerformed

    private void deleteCustomerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCustomerMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteCustomerMenuItemActionPerformed


	private javax.swing.JMenuItem addUserMenuItem;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton acceptButton;
    private javax.swing.JLabel acceptedLabel;
    private javax.swing.JList<String> acceptedList;
    private javax.swing.JMenuItem addCustomerMenuItem;
    private javax.swing.JMenu addMenu;
    private javax.swing.JMenuItem addReservationMenuItem;
    private javax.swing.JMenuItem addRoomMenuItem;
    private javax.swing.JButton applyFiltersButton;
    private javax.swing.JPanel changePasswordPanel;
    private javax.swing.JPasswordField confirmNewPasswordField;
    private javax.swing.JPasswordField currentPasswordField;
    private javax.swing.JButton declineButton;
    private javax.swing.JMenuItem deleteCustomerMenuItem;
    private javax.swing.JMenu deleteMenu;
    private javax.swing.JMenuItem deleteRoomMenuItem;
    private javax.swing.JCheckBox deluxeRoomFilterCheckbox;
    private javax.swing.JCheckBox doubleRoomFilterCheckbox;
    private javax.swing.JMenuItem editCustomerMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem editRoomMenuItem;
    private javax.swing.JMenuItem editUserMenuItem;
    private javax.swing.JTextField endDate;
    private javax.swing.JButton exitButton;
    private javax.swing.JCheckBox familyRoomFilterCheckbox;
    private javax.swing.JPanel filtersPanel;
    private javax.swing.JButton getStatistics;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JPanel homePanel;
    private javax.swing.JLabel hotelLabel;
    private javax.swing.JLabel hotelNameLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JCheckBox kingRoomFilterCheckbox;
    private javax.swing.JFormattedTextField maxPriceFormattedTextField;
    private javax.swing.JFormattedTextField minPriceFormattedTextField;
    private javax.swing.JPanel miscButtonsPanel;
    private javax.swing.JPasswordField newPasswordField;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JLabel pendingLabel;
    private javax.swing.JList<String> pendingList;
    private javax.swing.JCheckBox queenRoomFilterCheckbox;
    private javax.swing.JPanel reservationsPanel;
    private javax.swing.JButton resetFiltersButton;
    private javax.swing.JButton resetPasswordsButton;
    private javax.swing.JList<String> resultFilterList;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JButton showInfoButton;
    private javax.swing.JCheckBox singleRoomFilterCheckbox;
    private javax.swing.JTextField startDate;
    private javax.swing.JPanel statisticsPanel;
    private javax.swing.JCheckBox studioRoomFilterCheckbox;
    private javax.swing.JCheckBox suiteRoomFilterCheckbox;
    private javax.swing.JButton switchUserButton;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JCheckBox twinRoomFilterCheckbox;
    private javax.swing.JMenuItem viewCustomerMenuItem;
    private javax.swing.JMenu viewMenu;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
