package io.github.dmgtechlabs.gui;

import io.github.dmgtechlabs.State;
import io.github.dmgtechlabs.models.User;

import io.github.dmgtechlabs.Filters;
import io.github.dmgtechlabs.models.Hotel;
import io.github.dmgtechlabs.models.Reservation;
import io.github.dmgtechlabs.models.Room;
import io.github.dmgtechlabs.models.Statistics;
import io.github.dmgtechlabs.models.Customer;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class MainFrame extends javax.swing.JFrame {

	private State state = new State();

	private HelpFrame helpFrame;
	private AboutFrame aboutFrame;
	private HotelFrame hotelFrame;
	private RoomFrame roomFrame;
        private CustomerFrame customerFrame;
	private UserFrame userFrame;
	private ReservationFrame reservationFrame;
	private CreateReservationFrame createReservationFrame;

	private List<JCheckBox> filterTypeCheckboxes = new ArrayList<>();
	private List<Reservation> pendingReservations;
	private List<Reservation> acceptedReservations;
	private List<Customer> customers;
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
		GUIUtils.commonSetup(null, this);
		this.setTitle("Hotel Manager");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.helpFrame = new HelpFrame();
		this.aboutFrame = new AboutFrame();

		this.hotels = Hotel.selectById(hotelId);
		this.hotelNameLabel.setText(this.hotels.get(0).getName());

		setupFilters();
		applyFilters();

                
                
		if (user.isGuest()) {
			// Allow only Searching for guest user
                        addUserMenuItem.setEnabled(false);
                        editUserMenuItem.setEnabled(false);
                        
                        addHotelMenuItem.setEnabled(false);
                        editHotelMenuItem.setEnabled(false);
                        
                        editRoomMenuItem.setEnabled(false);
                        editCustomerMenuItem.setEnabled(false);
                        
                        addRoomMenuItem.setEnabled(false);
                        confirmChangePassword.setEnabled(false);
                        deleteRoomMenuItem.setEnabled(false);
                        deleteCustomerMenuItem.setEnabled(false);
                        
                        
			this.tabbedPane.setEnabledAt(2, false);
			this.tabbedPane.setEnabledAt(3, false);
                        this.tabbedPane.setEnabledAt(4, false);
		} else if (user.isEmployee()) {
			// Hide statistics from employees
			this.tabbedPane.setEnabledAt(3, false);
                        
                        addUserMenuItem.setEnabled(false);
                        editUserMenuItem.setEnabled(false);
                        
                        addHotelMenuItem.setEnabled(false);
                        editHotelMenuItem.setEnabled(false);
		} else if (user.isManager()){
                        addUserMenuItem.setEnabled(true);
                        editUserMenuItem.setEnabled(true);
                        
                        addHotelMenuItem.setEnabled(false);
                        editHotelMenuItem.setEnabled(false);
                }

		loadReservations();
		loadCustomers();
	}

	public MainFrame() {
		initComponents();
		this.setTitle("Hotel Manager");
		this.setLocationRelativeTo(null);

		this.helpFrame = new HelpFrame();
		this.aboutFrame = new AboutFrame();

		setupFilters();
		applyFilters();

		loadReservations();
		loadCustomers();
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
        undoButton = new javax.swing.JButton();
        customerPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        customersList = new javax.swing.JList<>();
        customerRefreshButton = new javax.swing.JButton();
        statisticsPanel = new javax.swing.JPanel();
        getStatistics = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        endDate = new javax.swing.JTextField();
        startDate = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        revenue = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        occupiedRooms = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        occupancyRate = new javax.swing.JLabel();
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
        confirmChangePassword = new javax.swing.JButton();
        miscButtonsPanel = new javax.swing.JPanel();
        exitButton = new javax.swing.JButton();
        switchUserButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        addMenu = new javax.swing.JMenu();
        addRoomMenuItem = new javax.swing.JMenuItem();
        addReservationMenuItem = new javax.swing.JMenuItem();
        addUserMenuItem = new javax.swing.JMenuItem();
        addHotelMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        editRoomMenuItem = new javax.swing.JMenuItem();
        editUserMenuItem = new javax.swing.JMenuItem();
        editCustomerMenuItem = new javax.swing.JMenuItem();
        editHotelMenuItem = new javax.swing.JMenuItem();
        deleteMenu = new javax.swing.JMenu();
        deleteRoomMenuItem = new javax.swing.JMenuItem();
        deleteCustomerMenuItem = new javax.swing.JMenuItem();
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
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

        acceptButton.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        acceptButton.setText("---->");
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

        undoButton.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        undoButton.setText("<----");
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoButtonActionPerformed(evt);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reservationsPanelLayout.createSequentialGroup()
                        .addContainerGap(118, Short.MAX_VALUE)
                        .addGroup(reservationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(declineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(reservationsPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(reservationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(reservationsPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(showInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(reservationsPanelLayout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addGroup(reservationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(acceptButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(undoButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(undoButton))
                    .addComponent(jScrollPane3))
                .addGap(18, 18, 18)
                .addComponent(declineButton)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Reservations", reservationsPanel);

        customersList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(customersList);

        customerRefreshButton.setText("Refresh");
        customerRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerRefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout customerPanelLayout = new javax.swing.GroupLayout(customerPanel);
        customerPanel.setLayout(customerPanelLayout);
        customerPanelLayout.setHorizontalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customerPanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(customerPanelLayout.createSequentialGroup()
                        .addGap(442, 442, 442)
                        .addComponent(customerRefreshButton)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        customerPanelLayout.setVerticalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(customerRefreshButton)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Customers", customerPanel);

        getStatistics.setText("Load Statistics");
        getStatistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getStatisticsActionPerformed(evt);
            }
        });

        endDate.setText("2025-02-01");

        startDate.setText("2025-01-01");

        jLabel7.setText("Revenue Period");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel7)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        revenue.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        revenue.setText("0 $");

        jLabel11.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel11.setText("Total Revenue:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(revenue)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(revenue)
                    .addComponent(jLabel11))
                .addGap(10, 10, 10))
        );

        occupiedRooms.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        occupiedRooms.setText("0");

        jLabel13.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel13.setText("Occupied Rooms:");

        jLabel14.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel14.setText("Occupancy Rate: ");

        occupancyRate.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        occupancyRate.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(occupiedRooms)
                .addGap(173, 173, 173)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(occupancyRate)
                .addContainerGap(425, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(occupiedRooms)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(occupancyRate))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout statisticsPanelLayout = new javax.swing.GroupLayout(statisticsPanel);
        statisticsPanel.setLayout(statisticsPanelLayout);
        statisticsPanelLayout.setHorizontalGroup(
            statisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statisticsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statisticsPanelLayout.createSequentialGroup()
                        .addGroup(statisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(statisticsPanelLayout.createSequentialGroup()
                                .addComponent(getStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statisticsPanelLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        statisticsPanelLayout.setVerticalGroup(
            statisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statisticsPanelLayout.createSequentialGroup()
                .addGroup(statisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statisticsPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(statisticsPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(getStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(495, Short.MAX_VALUE))
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

        confirmChangePassword.setText("Confirm");
        confirmChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmChangePasswordActionPerformed(evt);
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
                        .addComponent(confirmChangePassword))))
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
                    .addComponent(confirmChangePassword)
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
                .addContainerGap(459, Short.MAX_VALUE))
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

        addUserMenuItem.setText("User");
        addUserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserMenuItemActionPerformed(evt);
            }
        });
        addMenu.add(addUserMenuItem);

        addHotelMenuItem.setText("Hotel");
        addHotelMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addHotelMenuItemActionPerformed(evt);
            }
        });
        addMenu.add(addHotelMenuItem);

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

        editHotelMenuItem.setText("Hotel");
        editHotelMenuItem.setToolTipText("");
        editHotelMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editHotelMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(editHotelMenuItem);

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

	private void loadReservations() {
		this.pendingReservations = Reservation.selectByReservationStatus(Reservation.Status.PENDING.getValue());
		this.pendingList.setListData(Reservation.listToArray(this.pendingReservations.stream().map(reservation -> (Reservation) reservation).toList()));

		this.acceptedReservations = Reservation.selectByReservationStatus(Reservation.Status.ACCEPTED.getValue());
		this.acceptedList.setListData(Reservation.listToArray(this.acceptedReservations.stream().map(reservation -> (Reservation) reservation).toList()));
	}

	private void loadCustomers() {
		DefaultListModel<String> model = new DefaultListModel<>();
		List<Customer> customerList = Customer.selectAll();
		for (Customer customer : customerList) {
			String row = "Customer ID: " + customer.getId() + " " + customer.getFirstName() + " " + customer.getLastName()
				+ " " + customer.getPhone() + " " + customer.getEmail();
			model.addElement(row);
		}
		customersList.setModel(model);
	}

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
		GUIUtils.addWindowClosedListener(this.roomFrame, () -> { this.applyFilters(); });
		GUIUtils.showFrame(this.roomFrame);
    }//GEN-LAST:event_addRoomMenuItemActionPerformed

    private void editRoomMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRoomMenuItemActionPerformed
		if (this.resultFilterList.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(this, "Choose a room from the search tab first", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		this.roomFrame = new RoomFrame(this.state.activeHotelId, getSelectedRoom());
		GUIUtils.showFrame(this.roomFrame);
    }//GEN-LAST:event_editRoomMenuItemActionPerformed


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
        
    public Customer getSelectedCustomer() {
        // Get the selected index from the list
        int selectedIndex = customersList.getSelectedIndex();
        // Check if an item is selected
        if (selectedIndex == -1) {
            return null;
        }

        // Retrieve the selected item's text
        String selectedValue = customersList.getModel().getElementAt(selectedIndex);

        // Parse the customer ID from the selected item text
        try {
            String[] parts = selectedValue.split(" ");
            int customerId = -1;
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("ID:")) {
                    customerId = Integer.parseInt(parts[i + 1]);
                    break;
                }
            }
            return Customer.selectById(customerId).getFirst();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    private void resetPasswordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetPasswordsButtonActionPerformed
		this.currentPasswordField.setText("");
		this.newPasswordField.setText("");
		this.confirmNewPasswordField.setText("");
    }//GEN-LAST:event_resetPasswordsButtonActionPerformed

    private void confirmChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmChangePasswordActionPerformed
		String newPassword = this.newPasswordField.getText();

		if (!this.state.LoggedInUser.isGuest()
			&& (this.currentPasswordField.getText() == null
			? this.state.LoggedInUser.getPassword() == null
			: this.currentPasswordField.getText().equals(this.state.LoggedInUser.getPassword()))
			&& (newPassword == null
				? this.confirmNewPasswordField.getText() == null
				: newPassword.equals(this.confirmNewPasswordField.getText()))
			&& newPassword != null && !newPassword.isBlank()) {
			this.state.LoggedInUser.update(this.state.LoggedInUser.getUsername(), newPassword, this.state.LoggedInUser.getType(), this.state.LoggedInUser.getAccountHotelFk());
		}

    }//GEN-LAST:event_confirmChangePasswordActionPerformed

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptButtonActionPerformed
		if (pendingList.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(this, "Select a pending reservation first", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int selectedRoomId = this.pendingReservations.get(pendingList.getSelectedIndex()).getReservationRoomFk();
		Room selectedRoom = Room.selectById(selectedRoomId).get(0);

		this.pendingReservations.get(pendingList.getSelectedIndex()).update(2);
		selectedRoom.markOccupiedAs(true);

		loadReservations();

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

			loadReservations();
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
			Room reservedRoom = Room.selectById(this.acceptedReservations.get(this.acceptedList.getSelectedIndex()).getReservationRoomFk()).get(0);

			reservationFrame = new ReservationFrame(this.state.activeHotelId, reservedRoom);
			reservationFrame.setVisible(true);
			acceptedList.clearSelection();
			return;
		}

		Room reservedRoom = Room.selectById(this.pendingReservations.get(this.pendingList.getSelectedIndex()).getReservationRoomFk()).get(0);

		reservationFrame = new ReservationFrame(this.state.activeHotelId, reservedRoom);
		reservationFrame.setVisible(true);
		pendingList.clearSelection();
    }//GEN-LAST:event_showInfoButtonActionPerformed


    private void tabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneStateChanged

    }//GEN-LAST:event_tabbedPaneStateChanged

    private void getStatisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getStatisticsActionPerformed

		String sDate = startDate.getText();
		String eDate = endDate.getText();
		try {
			Statistics statistics = new Statistics();
			statistics.getStatistics(this.state.activeHotelId, sDate, eDate);
			revenue.setText(statistics.totalRevenue + " $");
			occupiedRooms.setText(String.valueOf(statistics.occupiedRooms));
			occupancyRate.setText(String.valueOf(statistics.occupancyRate) + " %");

//                TimeSeries series = new TimeSeries("Monthly Sales");
//                series.add(new Month(1, 2024), 200);
//                series.add(new Month(2, 2024), 150);
//                series.add(new Month(3, 2024), 180);
//
//                TimeSeriesCollection dataset = new TimeSeriesCollection();
//                dataset.addSeries(series);
//
//                JFreeChart chart = ChartFactory.createTimeSeriesChart(
//                    "Monthly Sales",
//                    "Date",
//                    "Sales",
//                    dataset,
//                    true,    // legend
//                    false,   // tooltips
//                    false);  // no URLs
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			System.out.println(statistics.reservationDistribution.size());
			for (Map.Entry<String, Integer> entry
				: statistics.reservationDistribution.entrySet()) {
				System.out.println(entry.getValue());
				System.out.println(entry.getKey());
				dataset.addValue(entry.getValue(), "Reservations", entry.getKey());
			}

			JFreeChart chart = ChartFactory.createBarChart("Reservation Distribution", "month", "Reservations", dataset);
			ChartPanel jFreeChartPanel = new ChartPanel(chart);
			jFreeChartPanel.setVisible(true);
			jFreeChartPanel.setBounds(5, 240, 1009, 470);
			SwingUtilities.invokeLater(() -> {
				statisticsPanel.add(jFreeChartPanel);
				statisticsPanel.revalidate();
				statisticsPanel.repaint();
			});

		} catch (Exception ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		}


    }//GEN-LAST:event_getStatisticsActionPerformed

    private void switchUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchUserButtonActionPerformed
		this.dispose();
		this.state.LoggedInUser = null;
		new LoginFrame(this.state.activeHotelId).setVisible(true);
    }//GEN-LAST:event_switchUserButtonActionPerformed

    private void addReservationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addReservationMenuItemActionPerformed
		if (this.resultFilterList.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(this, "Choose a room from the search tab first", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		this.createReservationFrame = new CreateReservationFrame(getSelectedRoom());
		GUIUtils.addWindowClosedListener(this.createReservationFrame, () -> { loadReservations(); loadCustomers(); });
		GUIUtils.showFrame(this.createReservationFrame);
    }//GEN-LAST:event_addReservationMenuItemActionPerformed

    private void editCustomerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCustomerMenuItemActionPerformed
        if (getSelectedCustomer() == null) {
                JOptionPane.showMessageDialog(this, "Please select a customer first", "Failure", JOptionPane.ERROR_MESSAGE);
                return;
        }
        this.customerFrame = new CustomerFrame(getSelectedCustomer());
        GUIUtils.addWindowClosedListener(this.customerFrame, () -> { this.loadCustomers();});
        GUIUtils.showFrame(this.customerFrame);
    }//GEN-LAST:event_editCustomerMenuItemActionPerformed

    private void deleteCustomerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCustomerMenuItemActionPerformed
		int option = JOptionPane.showConfirmDialog(this, "This action cannot be reversed", "Delete Customer?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (option != 0) {
			return;
		}

		Customer customer = getSelectedCustomer();
		if (customer == null) {
			JOptionPane.showMessageDialog(this, "Please select a customer first", "Failure", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (customer.delete()) {
                        loadCustomers();
			JOptionPane.showMessageDialog(this, "Customer " + customer.getFirstName() + " " + customer.getLastName() + " deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Could not delete Customer", "Failure", JOptionPane.ERROR_MESSAGE);
		}
    }//GEN-LAST:event_deleteCustomerMenuItemActionPerformed

    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
		if (acceptedList.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(this, "Select an accepted reservation first", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Reservation selectedAccepted = this.acceptedReservations.get(this.acceptedList.getSelectedIndex());
		Room room = Room.selectById(selectedAccepted.getReservationRoomFk()).get(0);
		assert (room != null);

		selectedAccepted.update(Reservation.Status.PENDING.getValue());
		room.markOccupiedAs(false);

		loadReservations();

		this.pendingList.clearSelection();
		this.acceptedList.clearSelection();
    }//GEN-LAST:event_undoButtonActionPerformed

    

       
    
    private void addUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserMenuItemActionPerformed
        this.userFrame = new UserFrame(this.state.activeHotelId);
		GUIUtils.showFrame(this.userFrame);
    }//GEN-LAST:event_addUserMenuItemActionPerformed

    private void addHotelMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addHotelMenuItemActionPerformed
        this.hotelFrame = new HotelFrame();
        GUIUtils.showFrame(this.hotelFrame);
    }//GEN-LAST:event_addHotelMenuItemActionPerformed

    private void editHotelMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editHotelMenuItemActionPerformed
        this.hotelFrame = new HotelFrame(new Hotel(this.state.activeHotelId));
        GUIUtils.showFrame(this.hotelFrame);
    }//GEN-LAST:event_editHotelMenuItemActionPerformed

    private void customerRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerRefreshButtonActionPerformed
        loadCustomers();
    }//GEN-LAST:event_customerRefreshButtonActionPerformed

//	private javax.swing.JMenuItem addUserMenuItem;
//        private javax.swing.JMenuItem addHotelMenuItem;
//        private javax.swing.JMenuItem editHotelMenuItem;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton acceptButton;
    private javax.swing.JLabel acceptedLabel;
    private javax.swing.JList<String> acceptedList;
    private javax.swing.JMenuItem addHotelMenuItem;
    private javax.swing.JMenu addMenu;
    private javax.swing.JMenuItem addReservationMenuItem;
    private javax.swing.JMenuItem addRoomMenuItem;
    private javax.swing.JMenuItem addUserMenuItem;
    private javax.swing.JButton applyFiltersButton;
    private javax.swing.JPanel changePasswordPanel;
    private javax.swing.JButton confirmChangePassword;
    private javax.swing.JPasswordField confirmNewPasswordField;
    private javax.swing.JPasswordField currentPasswordField;
    private javax.swing.JPanel customerPanel;
    private javax.swing.JButton customerRefreshButton;
    private javax.swing.JList<String> customersList;
    private javax.swing.JButton declineButton;
    private javax.swing.JMenuItem deleteCustomerMenuItem;
    private javax.swing.JMenu deleteMenu;
    private javax.swing.JMenuItem deleteRoomMenuItem;
    private javax.swing.JCheckBox deluxeRoomFilterCheckbox;
    private javax.swing.JCheckBox doubleRoomFilterCheckbox;
    private javax.swing.JMenuItem editCustomerMenuItem;
    private javax.swing.JMenuItem editHotelMenuItem;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JCheckBox kingRoomFilterCheckbox;
    private javax.swing.JFormattedTextField maxPriceFormattedTextField;
    private javax.swing.JFormattedTextField minPriceFormattedTextField;
    private javax.swing.JPanel miscButtonsPanel;
    private javax.swing.JPasswordField newPasswordField;
    private javax.swing.JLabel occupancyRate;
    private javax.swing.JLabel occupiedRooms;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JLabel pendingLabel;
    private javax.swing.JList<String> pendingList;
    private javax.swing.JCheckBox queenRoomFilterCheckbox;
    private javax.swing.JPanel reservationsPanel;
    private javax.swing.JButton resetFiltersButton;
    private javax.swing.JButton resetPasswordsButton;
    private javax.swing.JList<String> resultFilterList;
    private javax.swing.JLabel revenue;
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
    private javax.swing.JButton undoButton;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
