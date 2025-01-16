/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package io.github.dmgtechlabs.gui;

import io.github.dmgtechlabs.models.Customer;
import io.github.dmgtechlabs.models.Hotel;
import io.github.dmgtechlabs.models.Reservation;
import io.github.dmgtechlabs.models.Room;
import java.util.List;

/**
 *
 * @author thanasis
 */
public class ReservationFrame extends javax.swing.JFrame {
	
	private List<Reservation> reservations;
	private List<Hotel> hotels;
	private List<Room> rooms;
	private List<Customer> customers;
	
	private Reservation reservation;
	
	/**
	 * Creates new form ReservationFrame
	 */
	public ReservationFrame() {
		initComponents();
		this.setLocationRelativeTo(null);
	}
	
	public ReservationFrame(int activeHotelId, String selectedValue) {
		initComponents();
		this.setLocationRelativeTo(null);
		
		//TABLE(hotelid integer, name character varying, address character varying, phone bigint)
		
		this.reservations = Reservation.selectAll();
		for (int i = 0; i < reservations.size(); i++) {
			if (selectedValue.equals(this.reservations.get(i).UIString())) {
				this.reservation = this.reservations.get(i);
				break;
			}
		}
		this.hotels = Hotel.selectById(activeHotelId);
		this.rooms = Room.selectById(this.reservation.getReservationRoomFk());
		this.customers = Customer.selectById(this.reservation.getReservationCustomerFk());
		
		loadInfo();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reservationPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        customerInfoLabel = new javax.swing.JLabel();
        serviceInfoLabel = new javax.swing.JLabel();
        fnameIndicator = new javax.swing.JLabel();
        lnameIndicator = new javax.swing.JLabel();
        emailIndicator = new javax.swing.JLabel();
        phoneIndicator = new javax.swing.JLabel();
        hotelNameIndicator = new javax.swing.JLabel();
        hotelAddressIndicator = new javax.swing.JLabel();
        hotelPhoneIndicator = new javax.swing.JLabel();
        roomNumIndicator = new javax.swing.JLabel();
        roomFloorIndicator = new javax.swing.JLabel();
        roomTypeIndicator = new javax.swing.JLabel();
        roomPriceIndicator = new javax.swing.JLabel();
        checkOutIndicator = new javax.swing.JLabel();
        checkInIndicator = new javax.swing.JLabel();
        fnameLabel = new javax.swing.JLabel();
        lnameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        checkInLabel = new javax.swing.JLabel();
        checkOutLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        roomTypeLabel = new javax.swing.JLabel();
        roomPriceLabel = new javax.swing.JLabel();
        hotelPhoneLabel = new javax.swing.JLabel();
        roomFloorLabel = new javax.swing.JLabel();
        roomNumLabel = new javax.swing.JLabel();
        hotelNameLabel = new javax.swing.JLabel();
        hotelAddressLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("URW Gothic", 0, 36)); // NOI18N
        titleLabel.setText("Reservation Information");

        customerInfoLabel.setFont(new java.awt.Font("URW Gothic", 0, 24)); // NOI18N
        customerInfoLabel.setText("Customer Information");

        serviceInfoLabel.setFont(new java.awt.Font("URW Gothic", 0, 24)); // NOI18N
        serviceInfoLabel.setText("Service Information");

        fnameIndicator.setText("First name :");

        lnameIndicator.setText("Last name :");

        emailIndicator.setText("Email :");

        phoneIndicator.setText("Phone :");

        hotelNameIndicator.setText("Name :");

        hotelAddressIndicator.setText("Address :");

        hotelPhoneIndicator.setText("Phone :");

        roomNumIndicator.setText("Number :");

        roomFloorIndicator.setText("Floor :");

        roomTypeIndicator.setText("Type :");

        roomPriceIndicator.setText("Price :");

        checkOutIndicator.setText("Check-Out date :");

        checkInIndicator.setText("Check-In date :");

        fnameLabel.setText("jLabel11");

        lnameLabel.setText("jLabel11");

        emailLabel.setText("jLabel11");

        checkInLabel.setText("jLabel11");

        checkOutLabel.setText("jLabel11");

        phoneLabel.setText("jLabel11");

        roomTypeLabel.setText("jLabel11");

        roomPriceLabel.setText("jLabel11");

        hotelPhoneLabel.setText("jLabel11");

        roomFloorLabel.setText("jLabel11");

        roomNumLabel.setText("jLabel11");

        hotelNameLabel.setText("jLabel11");

        hotelAddressLabel.setText("jLabel11");

        jLabel4.setFont(new java.awt.Font("URW Gothic", 0, 22)); // NOI18N
        jLabel4.setText("Room");

        jLabel5.setFont(new java.awt.Font("URW Gothic", 0, 22)); // NOI18N
        jLabel5.setText("Hotel");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout reservationPanelLayout = new javax.swing.GroupLayout(reservationPanel);
        reservationPanel.setLayout(reservationPanelLayout);
        reservationPanelLayout.setHorizontalGroup(
            reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reservationPanelLayout.createSequentialGroup()
                        .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(reservationPanelLayout.createSequentialGroup()
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hotelNameIndicator)
                                    .addComponent(hotelAddressIndicator)
                                    .addComponent(hotelPhoneIndicator))
                                .addGap(80, 80, 80)
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(hotelNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(hotelAddressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(hotelPhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5)
                            .addComponent(serviceInfoLabel)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(reservationPanelLayout.createSequentialGroup()
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roomTypeIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(roomPriceIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(checkOutIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(roomFloorIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(roomNumIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(checkInIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(20, 20, 20)
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roomTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(roomFloorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(roomNumLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(roomPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkInLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkOutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(customerInfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, reservationPanelLayout.createSequentialGroup()
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fnameIndicator)
                                    .addComponent(lnameIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(emailIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(phoneIndicator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fnameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                        .addComponent(lnameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jSeparator3))
                        .addGap(36, 36, 36))
                    .addGroup(reservationPanelLayout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        reservationPanelLayout.setVerticalGroup(
            reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reservationPanelLayout.createSequentialGroup()
                        .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(reservationPanelLayout.createSequentialGroup()
                                .addComponent(customerInfoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(reservationPanelLayout.createSequentialGroup()
                                .addComponent(serviceInfoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(16, 16, 16)
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hotelNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hotelNameIndicator)
                                    .addComponent(fnameIndicator)
                                    .addComponent(fnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hotelAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hotelAddressIndicator)
                                    .addComponent(lnameIndicator)
                                    .addComponent(lnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hotelPhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hotelPhoneIndicator)
                                    .addComponent(emailIndicator)
                                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(reservationPanelLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel4)
                                .addGap(16, 16, 16)
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(roomFloorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roomFloorIndicator))
                                .addGap(18, 18, 18)
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(roomNumIndicator)
                                    .addComponent(roomNumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(roomTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roomTypeIndicator))
                                .addGap(18, 18, 18)
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(roomPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roomPriceIndicator))
                                .addGap(40, 40, 40)
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(checkInLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkInIndicator)))
                            .addGroup(reservationPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(phoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phoneIndicator))))
                        .addGap(18, 18, 18)
                        .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkOutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkOutIndicator)))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reservationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reservationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void loadInfo() {		
		// Hotel info
		this.hotelNameLabel.setText(this.hotels.get(0).getName());
		this.hotelAddressLabel.setText(this.hotels.get(0).getAddress());
		this.hotelPhoneLabel.setText(this.hotels.get(0).getPhone() + "");

		// Room info
		this.roomFloorLabel.setText(this.rooms.get(0).getFloor() + "");
		this.roomNumLabel.setText(this.rooms.get(0).getNumber() + "");
		this.roomTypeLabel.setText(this.rooms.get(0).getType().name());
		this.roomPriceLabel.setText(this.rooms.get(0).getPrice() + "");
		this.checkInLabel.setText(this.reservation.getCheckIn());
		this.checkOutLabel.setText(this.reservation.getCheckOut());
		
		//Customer info
		this.fnameLabel.setText(this.customers.get(0).getFirstName());
		this.lnameLabel.setText(this.customers.get(0).getLastName());
		this.emailLabel.setText(this.customers.get(0).getEmail());
		this.phoneLabel.setText(this.customers.get(0).getPhone() + "");
	}
	
	/**
	 * @param args the command line arguments
	 */
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
			java.util.logging.Logger.getLogger(ReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ReservationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ReservationFrame().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel checkInIndicator;
    private javax.swing.JLabel checkInLabel;
    private javax.swing.JLabel checkOutIndicator;
    private javax.swing.JLabel checkOutLabel;
    private javax.swing.JLabel customerInfoLabel;
    private javax.swing.JLabel emailIndicator;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel fnameIndicator;
    private javax.swing.JLabel fnameLabel;
    private javax.swing.JLabel hotelAddressIndicator;
    private javax.swing.JLabel hotelAddressLabel;
    private javax.swing.JLabel hotelNameIndicator;
    private javax.swing.JLabel hotelNameLabel;
    private javax.swing.JLabel hotelPhoneIndicator;
    private javax.swing.JLabel hotelPhoneLabel;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lnameIndicator;
    private javax.swing.JLabel lnameLabel;
    private javax.swing.JLabel phoneIndicator;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JPanel reservationPanel;
    private javax.swing.JLabel roomFloorIndicator;
    private javax.swing.JLabel roomFloorLabel;
    private javax.swing.JLabel roomNumIndicator;
    private javax.swing.JLabel roomNumLabel;
    private javax.swing.JLabel roomPriceIndicator;
    private javax.swing.JLabel roomPriceLabel;
    private javax.swing.JLabel roomTypeIndicator;
    private javax.swing.JLabel roomTypeLabel;
    private javax.swing.JLabel serviceInfoLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
