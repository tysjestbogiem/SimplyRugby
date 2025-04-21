package simpleRugby.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import simpleRugby.controler.MemberSecretaryController;
import simpleRugby.controler.RegisterController;

import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import simpleRugby.model.StaffRole;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.CardLayout;

/**
 * MemberSecretaryGUI is main GUI window for Member Secretary role.
 * 
 * It displays a full-screen interface with a side panel for navigation and 
 * a central panel that changes based on which button is clicked. The layout 
 * uses CardLayout to switch between views like "Add User", "Edit User".
 * 
 * Not all panels are active (enable) - just for prototype.
 */


//main GUI class for the application - extends JFrame to create a window
public class MemberSecretaryGUI extends JFrame {

 // defining member variables for the GUI components and controllers
 private JPanel contentPane; // main container panel
 private CardLayout cardLayout; // layout for switching between panels
 private JPanel mainPanel; // central panel for the app
 private RegisterPanel registerPanel; // panel for registration functionality
 private MemberSecretaryController myMemberSecretaryController; // handles member-related tasks
 private RegisterController myRegisterController; // handles registration tasks

 // constructor to initialise the GUI with the required controllers
 public MemberSecretaryGUI(MemberSecretaryController memberSecretaryController, RegisterController registerController) {
     // sets the title of the application window
     super("Application Manager");
		
     // assigning controllers to the class variables
     myMemberSecretaryController = memberSecretaryController;
     myRegisterController = registerController;
     
     // configure the main window properties
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close app when the window is closed
     setExtendedState(JFrame.MAXIMIZED_BOTH); // start app in maximised mode
     setBounds(100, 100, 1500, 1000); // set default size of the app
     
     // initialise the main content pane
     contentPane = new JPanel(new BorderLayout()); // uses BorderLayout to structure components
     contentPane.setBackground(SystemColor.activeCaption); 
     contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // padding for the main panel
     setContentPane(contentPane); // add content pane to the frame
        
     // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< SIDE PANEL >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     // side navigation bar for accessing different features
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(SystemColor.inactiveCaption); 
        sidePanel.setPreferredSize(new Dimension(200, 0)); // fixed width for side panel
        
     // header label for the side panel
        JLabel lblSimplyRugby = new JLabel("Simply Rugby");
        lblSimplyRugby.setHorizontalAlignment(SwingConstants.CENTER); 
        lblSimplyRugby.setFont(new Font("SansSerif", Font.BOLD, 16)); 
        
        // section for "Members" button
        JPanel pnlMembers = new JPanel();
        pnlMembers.setBackground(SystemColor.inactiveCaption); 
        pnlMembers.setLayout(null); 
        JButton btnMembers = new JButton("Members"); 
        btnMembers.addActionListener(new ActionListener() { // action listener to handle button click
            public void actionPerformed(ActionEvent e) {
                // add the member panel to the main panel and show it
                cardLayout.show(mainPanel, "Members");
                pnlMembers.setBackground(SystemColor.activeCaption); 
            }
        });
        btnMembers.setBounds(20, 25, 150, 50); 
        btnMembers.setMnemonic(KeyEvent.VK_M); // shortcut key (Alt + M)
        btnMembers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // change cursor to hand
        btnMembers.setFont(new Font("SansSerif", Font.BOLD, 14)); 
        pnlMembers.add(btnMembers); // add button to the panel
        
        
        // section for "Add Users" button
        JPanel pnlAddUser = new JPanel();
        pnlAddUser.setBackground(SystemColor.inactiveCaption);
        pnlAddUser.setLayout(null);
        JButton btnAddUser = new JButton("Add User");
        btnAddUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mainPanel.add(registerController.getRegisterPanel(), "Register");
                cardLayout.show(mainPanel, "Register");
                pnlAddUser.setBackground(SystemColor.activeCaption);
                
            }
        });
        btnAddUser.setBounds(20, 25, 150, 50);
        btnAddUser.setMnemonic(KeyEvent.VK_A);
        btnAddUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAddUser.setFont(new Font("SansSerif", Font.BOLD, 14));
        pnlAddUser.add(btnAddUser);
        
        
        // section for "Edit User" button
        JPanel pnlEditUsers = new JPanel();
        pnlEditUsers.setBackground(SystemColor.inactiveCaption);
        pnlEditUsers.setLayout(null);
        JButton btnEditUsers = new JButton("Edit Users");
        btnEditUsers.setBounds(20, 25, 150, 50);
        btnEditUsers.setMnemonic(KeyEvent.VK_E);
        btnEditUsers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEditUsers.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnEditUsers.setEnabled(false);
        pnlEditUsers.add(btnEditUsers);
        
        
        // section for "Report" button
        JPanel pnlReport = new JPanel();
        pnlReport.setBackground(SystemColor.inactiveCaption);
        pnlReport.setLayout(null);
        JButton btnReports = new JButton("Reports");
        btnReports.setBounds(20, 25, 150, 50);
        btnReports.setMnemonic(KeyEvent.VK_R);
        btnReports.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReports.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnReports.setEnabled(false);
        pnlReport.add(btnReports);
        
        // section for "Add Squad" button
        JPanel pnlAddSquad = new JPanel();
        pnlAddSquad.setBackground(SystemColor.inactiveCaption);
        pnlAddSquad.setLayout(null);
        JButton btnAddSquad = new JButton("Add Squad");
        btnAddSquad.setBounds(20, 25, 150, 50);
        btnAddSquad.setMnemonic(KeyEvent.VK_R);
        btnAddSquad.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAddSquad.setEnabled(false);
        pnlAddSquad.add(btnAddSquad);
        
        
        // layout for sidePanel using GroupLayout.
        GroupLayout gl_sidePanel = new GroupLayout(sidePanel);
        sidePanel.setLayout(gl_sidePanel);
        gl_sidePanel.setHorizontalGroup(
            gl_sidePanel.createParallelGroup(Alignment.LEADING)
                .addComponent(lblSimplyRugby, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addComponent(pnlMembers, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlAddUser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlEditUsers, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addComponent(pnlAddSquad, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addComponent(pnlReport, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        gl_sidePanel.setVerticalGroup(
            gl_sidePanel.createSequentialGroup()
                .addComponent(lblSimplyRugby, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMembers, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                .addGap(5)
                .addComponent(pnlAddUser, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEditUsers, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAddSquad, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlReport, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                .addGap(203)
        );
        
        contentPane.add(sidePanel, BorderLayout.WEST);
        
     
        
        
     // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MAIN PANEL >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

     // this is main area of the app that changes depending on which section is selected
     // it's controlled using a CardLayout to switch between different panels
     cardLayout = new CardLayout(); // this layout helps us switch between multiple panels
     mainPanel = new JPanel(cardLayout); // panel that holds all the panels
     mainPanel.setBackground(SystemColor.activeCaption); 


     // this is the RegisterPanel, which handles user registration
     registerPanel = new RegisterPanel(myRegisterController);

     // placeholder panel for editing users 
     JPanel editUserPanel = new JPanel();
     editUserPanel.add(new JLabel("Edit Users")); // just label

     // placeholder panel for adding a squad
     JPanel addSquadPanel = new JPanel();
     addSquadPanel.add(new JLabel("Add Squad")); 

     // placeholder panel for reports 
     JPanel reportsPanel = new JPanel();
     reportsPanel.add(new JLabel("Reports")); 

     // adding all panels to mainPanel with their names
     mainPanel.add(registerPanel, "Register"); 
     mainPanel.add(editUserPanel, "Edit User"); 
     mainPanel.add(addSquadPanel, "Add Squad"); 
     mainPanel.add(reportsPanel, "Reports"); 

     // set to display "Members"
     cardLayout.show(mainPanel, "Members");
     
     contentPane.add(mainPanel, BorderLayout.CENTER);
     }
 
     public MemberSecretaryGUI() {
	// TODO Auto-generated constructor stub
}

	// helper method to switch between panels
     public void showCard(String cardName) {
         cardLayout.show(mainPanel, cardName); // switches visible card in mainPanel
     }
}

