/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package io.github.dmgtechlabs.gui;

import io.github.dmgtechlabs.models.Hotel;
import javax.swing.JOptionPane;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author kdesp73
 */
public class HotelFrame extends javax.swing.JFrame {
	private Hotel hotel = null;
	
	/**
	 * Creates new form HotelFrame
	 */
	public HotelFrame() {
		initComponents();
		GUIUtils.commonSetup(null, this);
	
		GUIUtils.setPlaceholder(this.nameTextField, "Name");
		GUIUtils.setPlaceholder(this.addressTextField, "Address");
		GUIUtils.setPlaceholder(this.phoneNumberFormattedTextField, "Phone");
		
		this.actionButton.setText("Add");
		this.setTitle("Add a Hotel");
	}
	
	public HotelFrame(Hotel hotel){
		this();
		this.actionButton.setText("Apply");
		this.setTitle("Edit " + hotel.getName());
			
		this.nameTextField.setText(hotel.getName());
		this.addressTextField.setText(hotel.getAddress());
		this.phoneNumberFormattedTextField.setText(String.valueOf(hotel.getPhone()));
		
		List<Hotel.Amenity> amenities = hotel.selectAmenities();
		this.wifiCheckbox.setSelected(amenities.contains(Hotel.Amenity.WIFI));
		this.barCheckbox.setSelected(amenities.contains(Hotel.Amenity.BAR));
		this.parkingCheckbox.setSelected(amenities.contains(Hotel.Amenity.PARKING));
		this.poolCheckbox.setSelected(amenities.contains(Hotel.Amenity.POOL));
		this.spaCheckbox.setSelected(amenities.contains(Hotel.Amenity.SPA));
		this.gymCheckbox.setSelected(amenities.contains(Hotel.Amenity.GYM));
		this.saunaCheckbox.setSelected(amenities.contains(Hotel.Amenity.SAUNA));
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        actionButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        nameTextField = new javax.swing.JTextField();
        addressTextField = new javax.swing.JTextField();
        phoneNumberFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        spaCheckbox = new javax.swing.JCheckBox();
        gymCheckbox = new javax.swing.JCheckBox();
        barCheckbox = new javax.swing.JCheckBox();
        saunaCheckbox = new javax.swing.JCheckBox();
        wifiCheckbox = new javax.swing.JCheckBox();
        parkingCheckbox = new javax.swing.JCheckBox();
        poolCheckbox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        actionButton.setText("Action");
        actionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        phoneNumberFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel1.setText("Amenities");

        spaCheckbox.setText("Spa");
        spaCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spaCheckboxActionPerformed(evt);
            }
        });

        gymCheckbox.setText("Gym");

        barCheckbox.setText("Bar");

        saunaCheckbox.setText("Sauna");

        wifiCheckbox.setText("WiFi");

        parkingCheckbox.setText("Parking");

        poolCheckbox.setText("Pool");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saunaCheckbox)
                    .addComponent(jLabel1)
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(nameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(addressTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(phoneNumberFormattedTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(bgLayout.createSequentialGroup()
                            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(bgLayout.createSequentialGroup()
                                    .addComponent(spaCheckbox)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                    .addComponent(cancelButton))
                                .addGroup(bgLayout.createSequentialGroup()
                                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(gymCheckbox)
                                        .addComponent(barCheckbox))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(actionButton)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(wifiCheckbox)
                                    .addComponent(parkingCheckbox)
                                    .addComponent(poolCheckbox))))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(phoneNumberFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spaCheckbox)
                    .addComponent(wifiCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gymCheckbox)
                    .addComponent(parkingCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(barCheckbox)
                    .addComponent(poolCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saunaCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actionButton)
                    .addComponent(cancelButton))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	
	private boolean validate(String name, String address, BigInteger phone){
		if(name.isBlank() || name.equals("Name")) {
			GUIUtils.logUserError(this, "Name is empty");
			return false;
		}
		
		if(address.isBlank() || address.equals("Address")) {
			GUIUtils.logUserError(this, "Address is empty");
			return false;
		}
		
		if(phone.longValue() < 1000000000L || phone.longValue() > 9999999999L){
			GUIUtils.logUserError(this, "Invalid phone number");
			return false;
		}
		return true;
	}

	private void updateAmenities(Hotel hotel) {
		hotel.updateAmenities(
				wifiCheckbox.isSelected() ? Hotel.Amenity.WIFI : null,
				spaCheckbox.isSelected() ? Hotel.Amenity.SPA: null,
				saunaCheckbox.isSelected() ? Hotel.Amenity.SAUNA : null,
				poolCheckbox.isSelected() ? Hotel.Amenity.POOL: null,
				gymCheckbox.isSelected() ? Hotel.Amenity.GYM : null,
				parkingCheckbox.isSelected() ? Hotel.Amenity.PARKING : null,
				barCheckbox.isSelected() ? Hotel.Amenity.BAR : null
		);
	}
	
	private void addHotel(){
		String name = this.nameTextField.getText();
		String address = this.addressTextField.getText();
		BigInteger phone = new BigInteger(this.phoneNumberFormattedTextField.getText());
		
		if(!validate(name, address, phone)) return;
		
		Hotel newHotel = new Hotel(name, address, phone);
		BigInteger id = newHotel.insertWithReturn();
		if(id.longValue() > 0){
			newHotel.setId(id.intValue());
			updateAmenities(newHotel);
			JOptionPane.showMessageDialog(this, "Hotel " + name + " added successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			GUIUtils.logUserError(this, "Could not add Hotel");
		}
	}
	
	private void editHotel(){
		if(hotel == null) return;
		
		String name = this.nameTextField.getText();
		String address = this.addressTextField.getText();
		BigInteger phone = new BigInteger(this.phoneNumberFormattedTextField.getText());
		
		if(!validate(name, address, phone)) return;
		
		if(hotel.update(name, address, phone)){
			updateAmenities(hotel);
			JOptionPane.showMessageDialog(null, "Hotel " + name + " updated successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		} else {
			GUIUtils.logUserError(this, "Could not add Hotel");
		}
	}
	
    private void actionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionButtonActionPerformed
		if(hotel != null) addHotel();
		else editHotel();
    }//GEN-LAST:event_actionButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
		this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void spaCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spaCheckboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_spaCheckboxActionPerformed

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
			java.util.logging.Logger.getLogger(HotelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(HotelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(HotelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(HotelFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new HotelFrame().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actionButton;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JCheckBox barCheckbox;
    private javax.swing.JPanel bg;
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox gymCheckbox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JCheckBox parkingCheckbox;
    private javax.swing.JFormattedTextField phoneNumberFormattedTextField;
    private javax.swing.JCheckBox poolCheckbox;
    private javax.swing.JCheckBox saunaCheckbox;
    private javax.swing.JCheckBox spaCheckbox;
    private javax.swing.JCheckBox wifiCheckbox;
    // End of variables declaration//GEN-END:variables
}
