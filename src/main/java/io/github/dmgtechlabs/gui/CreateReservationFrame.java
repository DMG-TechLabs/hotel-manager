/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package io.github.dmgtechlabs.gui;

import io.github.dmgtechlabs.models.Customer;
import io.github.dmgtechlabs.models.Reservation;
import io.github.dmgtechlabs.models.Reservation.Status;
import io.github.dmgtechlabs.models.Room;
import java.awt.FlowLayout;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author thanasis
 */
public class CreateReservationFrame extends javax.swing.JFrame {

	private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	private int activeHotelfk;

	private JXDatePicker checkInPicker;
	private JXDatePicker checkOutPicker;

	private List<Room> rooms;
	private List<Reservation> reservations;

	/**
	 * Creates new form ReservationFrame
	 *
	 * @param activeHotelfk
	 */
	public CreateReservationFrame(int activeHotelfk) {
		initComponents();
		this.setLayout(null);
		this.reservationPanel.setLayout(null);
		initDatePanel();
		GUIUtils.commonSetup(null, this);
		this.setResizable(false);
		this.activeHotelfk = activeHotelfk;

		this.emailFormattedTextField.setInputVerifier(new EmailVerifier());

		loadRooms();
	}

	private void loadRooms() {
		this.rooms = Room.selectByHotelId(this.activeHotelfk);
		for (Room r : this.rooms) {
			this.roomComboBox.addItem(r.UIString());
		}
	}

	private void initDatePanel() {
		this.checkInPicker = new JXDatePicker();
		this.checkInPicker.setDate(Calendar.getInstance().getTime());
		this.checkInPicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
		this.checkInPicker.setBounds(5, 100, 200, 30);

		this.checkOutPicker = new JXDatePicker();
		this.checkOutPicker.setDate(Calendar.getInstance().getTime());
		this.checkOutPicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
		this.checkOutPicker.setBounds(260, 100, 200, 30);

		this.reservationPanel.add(this.checkInPicker);
		this.reservationPanel.add(this.checkOutPicker);

		SwingUtilities.invokeLater(() -> {
			this.reservationPanel.revalidate();
			this.add(this.reservationPanel);
			this.revalidate();
			this.repaint();
		});
	}

//	private void initDatePanel() {
//		SwingUtilities.invokeLater(() -> {
//			this.datePanel = new JPanel(null);
//			this.datePanel.setBounds(5, 50, 475, 100);
//
//			this.checkInPicker = new JXDatePicker();
//			this.checkInPicker.setDate(Calendar.getInstance().getTime());
//			this.checkInPicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
//			this.checkInPicker.setBounds(5, 50, 200, 30);
//
//			this.checkOutPicker = new JXDatePicker();
//			this.checkOutPicker.setDate(Calendar.getInstance().getTime());
//			this.checkOutPicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
//			this.checkOutPicker.setBounds(260, 50, 200, 30);
//
//			this.datePanel.add(this.checkInPicker);
//			this.datePanel.add(this.checkOutPicker);
//
//			this.datePanel.revalidate();
//			this.datePanel.repaint();
//			
//			this.add(this.datePanel);
//			
//			this.revalidate();
//		});
//	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reservationPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        roomLabel = new javax.swing.JLabel();
        roomComboBox = new javax.swing.JComboBox<>();
        okBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        personalInfoLabel = new javax.swing.JLabel();
        fNameIndicator = new javax.swing.JLabel();
        fNameTextField = new javax.swing.JTextField();
        lnameTextField = new javax.swing.JTextField();
        emailFormattedTextField = new javax.swing.JFormattedTextField();
        lnameIndicator = new javax.swing.JLabel();
        emailIndicator = new javax.swing.JLabel();
        phoneIndicator = new javax.swing.JLabel();
        phoneTextField = new javax.swing.JTextField();
        checkInLabel = new javax.swing.JLabel();
        checkOutLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("URW Gothic", 0, 30)); // NOI18N
        jLabel1.setText("Reservation Creation");

        roomLabel.setText("Select room");

        okBtn.setText("OK");
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        personalInfoLabel.setFont(new java.awt.Font("URW Gothic", 0, 24)); // NOI18N
        personalInfoLabel.setText("Personal information");

        fNameIndicator.setText("First name");

        lnameIndicator.setText("Last name");

        emailIndicator.setText("Email");

        phoneIndicator.setText("Phone");

        checkInLabel.setText("Select a check-In date");

        checkOutLabel.setText("Select a check-out date");

        javax.swing.GroupLayout reservationPanelLayout = new javax.swing.GroupLayout(reservationPanel);
        reservationPanel.setLayout(reservationPanelLayout);
        reservationPanelLayout.setHorizontalGroup(
            reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reservationPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okBtn)
                .addGap(12, 12, 12))
            .addGroup(reservationPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reservationPanelLayout.createSequentialGroup()
                        .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roomLabel)
                            .addComponent(jLabel1)
                            .addComponent(roomComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(personalInfoLabel)
                            .addGroup(reservationPanelLayout.createSequentialGroup()
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lnameIndicator)
                                    .addComponent(fNameIndicator)
                                    .addComponent(phoneIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailIndicator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fNameTextField)
                                    .addComponent(lnameTextField)
                                    .addComponent(emailFormattedTextField)
                                    .addComponent(phoneTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))))
                        .addContainerGap(41, Short.MAX_VALUE))
                    .addGroup(reservationPanelLayout.createSequentialGroup()
                        .addComponent(checkInLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkOutLabel)
                        .addGap(44, 44, 44))))
        );
        reservationPanelLayout.setVerticalGroup(
            reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkInLabel)
                    .addComponent(checkOutLabel))
                .addGap(80, 80, 80)
                .addComponent(roomLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roomComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(personalInfoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fNameIndicator)
                    .addComponent(fNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lnameIndicator))
                .addGap(18, 18, 18)
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailIndicator))
                .addGap(18, 18, 18)
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneIndicator)
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okBtn)
                    .addComponent(cancelBtn))
                .addGap(14, 14, 14))
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

	private boolean isEmailValid() {
		String email = this.emailFormattedTextField.getText();
		if (email.isBlank()) {
			return false;
		}

		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}

	private int validatePhone(String phone) {
		Integer integerValue;

		try {
			integerValue = Integer.valueOf(phone);
		} catch (NumberFormatException e) {
			return -1;
		}

		return integerValue;
	}

	private int validateDatesAndRoom(int[] checkInDate, int[] checkOutDate) {
		List<Room> reservedRooms;
		List<Reservation> reservationRooms;
		LocalDate currentDate = LocalDate.now();
		Date localDate = new Date(currentDate.getYear() - 1900, currentDate.getMonthValue() - 1, currentDate.getDayOfMonth());
		LocalDate newCheckIn = LocalDate.of(checkInDate[0], checkInDate[1], checkInDate[2]);
		LocalDate newCheckOut = LocalDate.of(checkOutDate[0], checkOutDate[1], checkOutDate[2]);
		LocalDate existingCheckIn;
		LocalDate existingCheckOut;
		boolean isNotOverLapping;
		boolean newIsSameWithExisting;
		int found = 0;

		if (this.checkInPicker.getDate().compareTo(localDate) < 0) {
			GUIUtils.logUserError(this, "Can't pick past dates");
			return -1;
		}

		if (checkInDate[0] > checkOutDate[0]) {
			GUIUtils.logUserError(this, "Select a valid date combination");
			return -1;
		}

		if (checkInDate[1] > checkOutDate[1]) {
			GUIUtils.logUserError(this, "Select a valid date combination");
			return -1;
		}

		if ((checkInDate[2] > checkOutDate[2]) && (checkInDate[1] >= checkOutDate[1])) {
			GUIUtils.logUserError(this, "Select a valid date combination");
			return -1;
		}

		this.reservations = Reservation.selectByHotel(activeHotelfk);
		if (this.reservations.isEmpty() || this.reservations == null) {
			return 1;
		} else {
			reservedRooms = Room.selectByReserved(activeHotelfk);
			for (Room room : reservedRooms) {
				if (this.roomComboBox.getSelectedItem().toString().equals(room.UIString())) {
					reservationRooms = Reservation.selectByRoomId(room.getRoomId());
					for (Reservation reservation : reservationRooms) {
						existingCheckIn = LocalDate.of(reservation.getCheckInYear(), reservation.getCheckInMonth(), reservation.getCheckInDay());
						existingCheckOut = LocalDate.of(reservation.getCheckOutYear(), reservation.getCheckOutMonth(), reservation.getCheckOutDay());

						isNotOverLapping = newCheckOut.isBefore(existingCheckIn) || newCheckIn.isAfter(existingCheckOut);
						newCheckOut.isEqual(newCheckIn);
						existingCheckIn.isEqual(existingCheckOut);
						newIsSameWithExisting = newCheckOut.isEqual(existingCheckOut) && newCheckIn.isEqual(existingCheckIn);

						if (!newIsSameWithExisting && isNotOverLapping) {
							continue;
						}
						found++;
						break;
					}
				}
				if (found > 0) {
					GUIUtils.logUserError(this, "Can't pick these dates for that room");
					return -1;
				} else {
					return 1;
				}
			}
		}

		return 1;
	}

	private int validatePersonalInfo(String fname, String lname, String email, String phone) {
		if (fname.isBlank()) {
			GUIUtils.logUserError(this, "Provide First Name");
			return -1;
		}

		if (lname.isBlank()) {
			GUIUtils.logUserError(this, "Provide Last Name");
			return -1;
		}

		if (email.isBlank()) {
			GUIUtils.logUserError(this, "Provide email");
			return -1;
		}

		if (!isEmailValid()) {
			GUIUtils.logUserError(this, "Invalid email");
			return -1;
		}

		if (phone.isBlank()) {
			GUIUtils.logUserError(this, "Provide phone number");
		}

		return 1;

	}

	private String formatCheckInDate(int checkInYear, int checkInMonth, int checkInDay) {
		if (checkInMonth < 10 && checkInDay < 10) {
			return checkInYear + "-0" + checkInMonth + "-0" + checkInDay;
		}

		if (checkInMonth < 10) {
			return checkInYear + "-0" + checkInMonth + "-" + checkInDay;
		}

		if (checkInDay < 10) {
			return checkInYear + "-" + checkInMonth + "-0" + checkInDay;
		}

		return checkInYear + "-" + checkInMonth + "-" + checkInDay;
	}

	private String formatCheckOutDate(int checkOutYear, int checkOutMonth, int checkOutDay) {
		if (checkOutMonth < 10 && checkOutDay < 10) {
			return checkOutYear + "-0" + checkOutMonth + "-0" + checkOutDay;
		}

		if (checkOutMonth < 10) {
			return checkOutYear + "-0" + checkOutMonth + "-" + checkOutDay;
		}

		if (checkOutDay < 10) {
			return checkOutYear + "-" + checkOutMonth + "-0" + checkOutDay;
		}

		return checkOutYear + "-" + checkOutMonth + "-" + checkOutDay;
	}

	private void createReservation(Customer customer, Room selectedRoom, String checkIn, String checkOut) {
		new Reservation(customer.getId(), selectedRoom.getRoomId(), checkIn, checkOut, selectedRoom.getPrice(), Status.PENDING).insert();
//		selectedRoom.update(selectedRoom.getFloor(), selectedRoom.getNumber(), selectedRoom.getType(), selectedRoom.getPrice());
	}

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
		this.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed
		String fname = this.fNameTextField.getText();
		String lname = this.lnameTextField.getText();
		String email = this.emailFormattedTextField.getText();
		String phone = this.phoneTextField.getText();
		int checkInYear = this.checkInPicker.getDate().getYear() + 1900;
		int checkInMonth = this.checkInPicker.getDate().getMonth() + 1;
		int checkInDay = this.checkInPicker.getDate().getDate();
		int checkOutYear = this.checkOutPicker.getDate().getYear() + 1900;
		int checkOutMonth = this.checkOutPicker.getDate().getMonth() + 1;
		int checkOutDay = this.checkOutPicker.getDate().getDate();
		String checkIn = formatCheckInDate(checkInYear, checkInMonth, checkInDay);
		String checkOut = formatCheckOutDate(checkOutYear, checkOutMonth, checkOutDay);

		int[] checkInDate = {
			checkInYear,
			checkInMonth,
			checkInDay
		};
		int[] checkOutDate = {
			checkOutYear,
			checkOutMonth,
			checkOutDay
		};
		Room selectedRoom = this.rooms.get(this.roomComboBox.getSelectedIndex());
		Customer customer;

		Integer integerValue = validatePhone(phone);
		BigInteger phoneNum;

		if (validateDatesAndRoom(checkInDate, checkOutDate) < 0) {
			return;
		}

		if (validatePersonalInfo(fname, lname, email, phone) < 0) {
			return;
		}

		if (integerValue < 0) {
			GUIUtils.logUserError(this, "Invalid phone number");
			return;
		}

		phoneNum = BigInteger.valueOf(integerValue);

		if (Customer.exists(email)) {
			customer = Customer.selectByEmail(email).get(0);
			customer.update(fname, lname, phoneNum, email);

			createReservation(customer, selectedRoom, checkIn, checkOut);

			JOptionPane.showMessageDialog(this, "You have successfully booked a reservation!\nWe will keep you informed about the acceptance of your reservation.", "Mesasge", JOptionPane.INFORMATION_MESSAGE);

			return;
		}

		new Customer(fname, lname, phoneNum, email).insert();
		customer = Customer.selectByEmail(email).get(0);

		createReservation(customer, selectedRoom, checkIn, checkOut);

		JOptionPane.showMessageDialog(this, "You have successfully booked a reservation\nWe will keep you informed about the acceptance of your reservation", "Mesasge", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_okBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel checkInLabel;
    private javax.swing.JLabel checkOutLabel;
    private javax.swing.JFormattedTextField emailFormattedTextField;
    private javax.swing.JLabel emailIndicator;
    private javax.swing.JLabel fNameIndicator;
    private javax.swing.JTextField fNameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lnameIndicator;
    private javax.swing.JTextField lnameTextField;
    private javax.swing.JButton okBtn;
    private javax.swing.JLabel personalInfoLabel;
    private javax.swing.JLabel phoneIndicator;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JPanel reservationPanel;
    private javax.swing.JComboBox<String> roomComboBox;
    private javax.swing.JLabel roomLabel;
    // End of variables declaration//GEN-END:variables
}
