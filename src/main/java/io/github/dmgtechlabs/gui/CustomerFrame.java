/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package io.github.dmgtechlabs.gui;

import io.github.dmgtechlabs.models.Customer;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class CustomerFrame extends javax.swing.JFrame {
	private Customer customer = null;
	

	public CustomerFrame(Customer customer){
                this.customer = customer;
                initComponents();
                GUIUtils.commonSetup(null, this);
		
                this.fNameTextField.setText(customer.getFirstName());
                this.lNameTextField.setText(customer.getLastName());
                this.phoneTextField.setText(String.valueOf(customer.getPhone()));
                this.emailTextField.setText(customer.getEmail());

		this.setTitle("Edit " + customer.getFirstName() + " " + customer.getLastName());
	}
	
	private void editCustomer(){
                String fName = (String) fNameTextField.getText();
                String lName = (String) lNameTextField.getText();
                BigInteger phone = new BigInteger(this.phoneTextField.getText());
                String email  = (String) emailTextField.getText();
		
		if(!validate(fName, lName, phone, email)) return;
		
		if(customer.update(fName, lName, phone, email)){
			JOptionPane.showMessageDialog(this, "Customer " + fName + " " + lName + " edited successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		} else {
			GUIUtils.logUserError(this, "Could not edit Customer");
		}
	}
        
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fNameTextField = new javax.swing.JTextField();
        lNameTextField = new javax.swing.JTextField();
        phoneTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        editButton = new javax.swing.JButton();
        cancleButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lNameTextFieldActionPerformed(evt);
            }
        });

        phoneTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneTextFieldActionPerformed(evt);
            }
        });
        phoneTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneTextFieldKeyTyped(evt);
            }
        });

        emailTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextFieldActionPerformed(evt);
            }
        });

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        cancleButton.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancleButton)
                        .addGap(18, 18, 18)
                        .addComponent(editButton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(fNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                        .addComponent(lNameTextField)
                        .addComponent(phoneTextField)
                        .addComponent(emailTextField)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(fNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(cancleButton))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lNameTextFieldActionPerformed

    private void phoneTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneTextFieldActionPerformed

    private void emailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextFieldActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        editCustomer();
    }//GEN-LAST:event_editButtonActionPerformed

    private void phoneTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneTextFieldKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_phoneTextFieldKeyTyped

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerFrame().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancleButton;
    private javax.swing.JButton editButton;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField fNameTextField;
    private javax.swing.JTextField lNameTextField;
    private javax.swing.JTextField phoneTextField;
    // End of variables declaration//GEN-END:variables
}
