/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package io.github.dmgtechlabs.gui;

import io.github.dmgtechlabs.models.Customer;
import javax.swing.JOptionPane;
import java.math.BigInteger;

public class CustomerFrame extends javax.swing.JFrame {
	private Customer customer = null;
	
	/**
	 * Creates new form CustomerFrame
	 */
	public CustomerFrame() {
		initComponents();
		GUIUtils.commonSetup(null, this);
	
		GUIUtils.setPlaceholder(this.fNameTextField, "First Name");
		GUIUtils.setPlaceholder(this.lNameTextField, "Last Name");
		GUIUtils.setPlaceholder(this.phoneNumberFormattedTextField, "Phone");
                GUIUtils.setPlaceholder(this.emailFormattedTextField, "Email");
		
		this.actionButton.setText("Add");
		this.setTitle("Add a Customer");
	}
	
	public CustomerFrame(Customer customer){
		this();
		this.actionButton.setText("Apply");
		this.setTitle("Edit " +customer.getFirstName() + customer.getLastName());
			
		this.fNameTextField.setText(customer.getFirstName());
		this.lNameTextField.setText(customer.getLastName());
		this.phoneNumberFormattedTextField.setText(String.valueOf(customer.getPhone()));
                this.emailFormattedTextField.setText(customer.getEmail());
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
        fNameTextField = new javax.swing.JTextField();
        lNameTextField = new javax.swing.JTextField();
        phoneNumberFormattedTextField = new javax.swing.JFormattedTextField();
        emailFormattedTextField = new javax.swing.JFormattedTextField();

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

        emailFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addGap(18, 18, 18)
                        .addComponent(actionButton))
                    .addComponent(fNameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lNameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneNumberFormattedTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(emailFormattedTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(fNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(phoneNumberFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(emailFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
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

	
	private boolean validate(String fName, String lName, BigInteger phone, String email){
		if(fName.isBlank() || fName.equals("First Name")) {
			GUIUtils.logUserError(this, "First Name is empty");
			return false;
		}
                
		if(lName.isBlank() || lName.equals("Last Name")) {
			GUIUtils.logUserError(this, "Last Name is empty");
			return false;
                }
		
		if(phone.longValue() < 1000000000L || phone.longValue() > 9999999999L){
			GUIUtils.logUserError(this, "Invalid phone number");
			return false;
		}
                
		if(email.isBlank() || email.equals("Email")) {
			GUIUtils.logUserError(this, "Email is empty");
			return false;
                }
		return true;
	}
        
	private void addCustomer(){

                String fName = this.fNameTextField.getText();
		String lName = this.lNameTextField.getText();
		BigInteger phone = BigInteger.valueOf(Integer.parseInt(this.phoneNumberFormattedTextField.getText()));
                String email = this.emailFormattedTextField.getText();
		
		if(!validate(fName, lName, phone, email)) return;
		
		if(new Customer(fName, lName, phone, email).insert()){
			JOptionPane.showMessageDialog(this, "Customer " + fName + lName + " added successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			GUIUtils.logUserError(this, "Could not add Customer");
		}
	}
	
	private void editCustomer(){
		if(customer == null) return;

		String fName = this.fNameTextField.getText();
		String lName = this.lNameTextField.getText();
		BigInteger phone = BigInteger.valueOf(Integer.parseInt(this.phoneNumberFormattedTextField.getText()));
                String email = this.emailFormattedTextField.getText();
		
		if(!validate(fName, lName, phone, email)) return;
		
		if(customer.update(fName, lName, phone, email)){
			JOptionPane.showMessageDialog(null, "Customer " + fName + lName + " updated successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		} else {
			GUIUtils.logUserError(this, "Could not update Customer");
		}
	}
    private void actionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionButtonActionPerformed
		if(customer != null) addCustomer();
		else editCustomer();
    }//GEN-LAST:event_actionButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
		this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

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
			java.util.logging.Logger.getLogger(CustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(CustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(CustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(CustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new CustomerFrame().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actionButton;
    private javax.swing.JPanel bg;
    private javax.swing.JButton cancelButton;
    private javax.swing.JFormattedTextField emailFormattedTextField;
    private javax.swing.JTextField fNameTextField;
    private javax.swing.JTextField lNameTextField;
    private javax.swing.JFormattedTextField phoneNumberFormattedTextField;
    // End of variables declaration//GEN-END:variables
}
