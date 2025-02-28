/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package io.github.dmgtechlabs.gui;

import io.github.dmgtechlabs.exceptions.PermissionDenied;
import io.github.dmgtechlabs.models.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author kostas
 */
public class UserFrame extends javax.swing.JFrame {
    private int hotelid;
    private List<User> users = new ArrayList<User>();
    private int selectedUser = -1;
    
    private void setFields(){
        this.selectedUser = userSelect.getSelectedIndex();
        usernameField.setText(this.users.get(this.selectedUser).getUsername());
        passwordField.setText("");
        userType.setSelectedIndex(this.users.get(this.selectedUser).getType()-2);
        if(this.selectedUser == 0) userType.setEnabled(false);
        else userType.setEnabled(true);
    }
    /**
     * Creates new form CreateUserFrame
     */
    public UserFrame(int hotelId) {
        initComponents();
        this.hotelid = hotelId;
        this.setLocationRelativeTo(null);
        userSelect.setEnabled(false);
        deleteUserButton.setEnabled(false);
    }
    
    public UserFrame(User user) {
        this.users.add(user);
        initComponents();
        userSelect.addItem(user.getUsername());
        userSelect.setSelectedIndex(0);
        this.hotelid = user.getAccountHotelFk();
        this.setLocationRelativeTo(null);
        setFields();
        accountButton.setText("Edit Account");
        accountButton.removeActionListener(accountButton.getActionListeners()[0]);
        accountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformedUpdate(evt);
            }
        });
        
        if(user.getType() == User.UserType.GUEST.getValue()){
            usernameField.setEnabled(false);
            passwordField.setEnabled(false);
            accountButton.setEnabled(false);
            userSelect.setEnabled(false);
            deleteUserButton.setEnabled(false);
        }else if(user.isAdmin() || user.isManager() ){
            try{
                
                for (User u : user.Manager_SelectAllUsers()) {
                    System.out.println(u);
                    if (this.users.get(0).isAdmin() || !u.isAdmin()){
                        users.add(u);
                        userSelect.addItem(u.getUsername());
                    }
                    
                    
                }
                
            } catch (PermissionDenied e){
                JDialog j = new JDialog(this, "Error");
                JLabel l = new JLabel(e.getMessage());
                j.add(l);
                j.setSize(100, 100);
                j.setLocationRelativeTo(null);
                j.setVisible(true);
            }
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        userType = new javax.swing.JComboBox<>();
        accountButton = new javax.swing.JButton();
        userSelect = new javax.swing.JComboBox<>();
        deleteUserButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(385, 360));
        setMinimumSize(new java.awt.Dimension(385, 360));
        setPreferredSize(new java.awt.Dimension(385, 360));
        setResizable(false);
        setSize(new java.awt.Dimension(385, 360));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel3.setText("Username:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 100, 30));

        usernameField.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        usernameField.setText("guest");
        usernameField.setName("username_textfield"); // NOI18N
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });
        getContentPane().add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 210, 30));

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel4.setText("Type:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 100, 30));

        passwordField.setText("guest");
        passwordField.setName("password_textfield"); // NOI18N
        getContentPane().add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 210, 30));

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel5.setText("Password:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 100, 30));

        userType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Guest", "Employee", "Manager" }));
        getContentPane().add(userType, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 210, 30));

        accountButton.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        accountButton.setText("Create Account");
        accountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountButtonActionPerformed(evt);
            }
        });
        getContentPane().add(accountButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 330, 40));

        userSelect.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                userSelectItemStateChanged(evt);
            }
        });
        getContentPane().add(userSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 310, -1));

        deleteUserButton.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        deleteUserButton.setText("Delete");
        deleteUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deleteUserButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 160, 40));

        cancelButton.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 160, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void jButton3ActionPerformedUpdate(java.awt.event.ActionEvent evt) {  
        System.out.println("Update account");
        try{
            if(this.users == null || usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) throw new IllegalArgumentException("Empty username or password");
//            User newUser = new User(0, usernameField.getText(), passwordField.getPassword().toString(), this.user.getType(), this.hotelid);
            this.users.get(this.selectedUser).update(usernameField.getText(), passwordField.getText(), this.users.get(this.selectedUser).getType());
            this.dispose();
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountButtonActionPerformed
        try{
            if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) throw new IllegalArgumentException("Empty username or password");
            User newUser = new User(0, usernameField.getText(), passwordField.getText(), userType.getSelectedIndex()+2, this.hotelid);
            newUser.insert();
            this.dispose();
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_accountButtonActionPerformed

    private void deleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserButtonActionPerformed
        this.users.get(this.selectedUser).delete();
        this.dispose();
    }//GEN-LAST:event_deleteUserButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void userSelectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_userSelectItemStateChanged
        setFields();
    }//GEN-LAST:event_userSelectItemStateChanged

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
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserFrame(-1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accountButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton deleteUserButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JComboBox<String> userSelect;
    private javax.swing.JComboBox<String> userType;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
