
package com.mycompany.app.gui;

import com.mycompany.app.files.FileOperations;
import com.mycompany.app.files.Images;
import dorkbox.desktop.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.UIManager;

public final class AboutFrame extends javax.swing.JFrame {

	private String name = "Name";
	private String mail = "example@email.com";
	private String githubURL = "https://github.com/username";
	private String description = "My awesome project";
	private String font = "sans-serif";
	private String projectName = "my-app";
	private String version = "1.0.0-SNAPSHOT";
	private String licensePath = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/LICENSE";
	
	public AboutFrame() {
		initComponents();
		
		this.setResizable(false);
		this.rootPane.requestFocus();
		this.setLocationRelativeTo(null);
		this.setTitle("About");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setFrameFont(font);
		this.setDeveloperName(name);
		this.setProjectName(projectName);
		this.setDescription(description);
		this.setLicense(licensePath);
		this.setVersion(version);
		this.setDescription(description);
		this.setMail(mail);
		this.setGithubURL(githubURL);
		
		int size = 20;
		GUIMethods.loadImage(nameLabel, Images.developerWhite, new Dimension(size, size));
		GUIMethods.loadImage(emailLabel, Images.mailWhite, new Dimension(size, size));
		GUIMethods.loadImage(githubLabel, Images.githubWhite, new Dimension(size, size));
	}
	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        projectNameLabel = new javax.swing.JLabel();
        versionLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        licenseIndicatorLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        licenseTextArea = new javax.swing.JTextArea();
        contactInfoIndicatorLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        githubLabel = new javax.swing.JLabel();
        messageLabel = new javax.swing.JLabel();
        descriptionIndicatorLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setName("bg"); // NOI18N

        projectNameLabel.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        projectNameLabel.setText("Project Name");
        projectNameLabel.setName("fg"); // NOI18N

        versionLabel.setText("version");
        versionLabel.setName("fg"); // NOI18N

        descriptionTextArea.setEditable(false);
        descriptionTextArea.setColumns(20);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setName("textbox"); // NOI18N
        jScrollPane1.setViewportView(descriptionTextArea);

        licenseIndicatorLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        licenseIndicatorLabel.setText("License");
        licenseIndicatorLabel.setName("fg"); // NOI18N

        licenseTextArea.setEditable(false);
        licenseTextArea.setColumns(20);
        licenseTextArea.setLineWrap(true);
        licenseTextArea.setRows(5);
        licenseTextArea.setWrapStyleWord(true);
        licenseTextArea.setName("textbox"); // NOI18N
        jScrollPane2.setViewportView(licenseTextArea);

        contactInfoIndicatorLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        contactInfoIndicatorLabel.setText("Contact Info");
        contactInfoIndicatorLabel.setName("fg"); // NOI18N

        nameLabel.setText("Name");
        nameLabel.setName("fg"); // NOI18N

        emailLabel.setText("Email");
        emailLabel.setName("fg"); // NOI18N

        githubLabel.setText("Github");
        githubLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        githubLabel.setName("fg"); // NOI18N
        githubLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                githubLabelMouseClicked(evt);
            }
        });

        messageLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        messageLabel.setText("Thank you for using my application!");
        messageLabel.setName("fg"); // NOI18N

        descriptionIndicatorLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        descriptionIndicatorLabel.setText("Description");
        descriptionIndicatorLabel.setName("fg"); // NOI18N

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(descriptionIndicatorLabel)
                    .addComponent(messageLabel)
                    .addComponent(githubLabel)
                    .addComponent(emailLabel)
                    .addComponent(contactInfoIndicatorLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(licenseIndicatorLabel)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(projectNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(versionLabel))
                    .addComponent(nameLabel))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(versionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(descriptionIndicatorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(licenseIndicatorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(contactInfoIndicatorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(githubLabel)
                .addGap(18, 18, 18)
                .addComponent(messageLabel)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void githubLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_githubLabelMouseClicked
		if (githubURL == null || githubURL.isBlank()) {
			return;
		}
		
		try {
			Desktop.browseURL(this.githubURL);
		} catch (IOException ex) {
			Logger.getLogger(AboutFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
    }//GEN-LAST:event_githubLabelMouseClicked

	public void setLicense(String licensePath) {
		this.licensePath = licensePath;
		licenseTextArea.setText(FileOperations.readFile(licensePath));
		licenseTextArea.setCaretPosition(0);
	}
	
	public void setDeveloperName(String name) {
		this.name = name;
		nameLabel.setText(name);
	}
	
	public void setMail(String mail) {
		this.mail = mail;
		emailLabel.setText(mail);
	}
	
	public void setGithubURL(String githubURL) {
		this.githubURL = githubURL;
		this.githubLabel.setText(githubURL);
	}
	
	public void setDescription(String description) {
		this.description = description;
		descriptionTextArea.setText(description);
		descriptionTextArea.setCaretPosition(0);
	}
	
	public void setFrameFont(String font) {
		this.font = font;
		GUIMethods.setFontFamilyRecursively(this, font, Font.PLAIN);
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
		projectNameLabel.setText(projectName);
	}
	
	public void setVersion(String version) {
		this.version = version;
		versionLabel.setText(version);
	}
	
	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1");
		try {
			UIManager.setLookAndFeel(new com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new AboutFrame().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLabel contactInfoIndicatorLabel;
    private javax.swing.JLabel descriptionIndicatorLabel;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel githubLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel licenseIndicatorLabel;
    private javax.swing.JTextArea licenseTextArea;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel projectNameLabel;
    private javax.swing.JLabel versionLabel;
    // End of variables declaration//GEN-END:variables
}
