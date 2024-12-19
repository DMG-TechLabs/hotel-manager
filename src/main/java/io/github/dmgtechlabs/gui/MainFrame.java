
package io.github.dmgtechlabs.gui;

public class MainFrame extends javax.swing.JFrame {
	private HelpFrame helpFrame;
	private AboutFrame aboutFrame;
	private HotelFrame hotelFrame;
	private RoomFrame roomFrame;
	
	private int activeHotelId = -1;
	
	/**
	 * Creates new form MainFrame
	 */
	public MainFrame(int hotelId) {
		initComponents();
		this.activeHotelId = hotelId;
		
		this.setTitle("Hotel Manager");
		this.setLocationRelativeTo(null);
		
		this.helpFrame = new HelpFrame();
		this.aboutFrame = new AboutFrame();
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        addMenu = new javax.swing.JMenu();
        addRoomMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        editRoomMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
            .addGap(0, 1132, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 689, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemActionPerformed
        if(helpFrame.isShowing()) return;
		
		helpFrame.setVisible(true);
    }//GEN-LAST:event_helpMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        if(aboutFrame.isShowing()) return;
		
		aboutFrame.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void addRoomMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRoomMenuItemActionPerformed
		this.roomFrame = new RoomFrame(this.activeHotelId);
		GUIUtils.showFrame(this.roomFrame);
    }//GEN-LAST:event_addRoomMenuItemActionPerformed

    private void editRoomMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRoomMenuItemActionPerformed
        this.roomFrame = new RoomFrame(this.activeHotelId, null); // TODO: get selected room
		GUIUtils.showFrame(this.roomFrame);
    }//GEN-LAST:event_editRoomMenuItemActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame(-1).setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenu addMenu;
    private javax.swing.JMenuItem addRoomMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem editRoomMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
