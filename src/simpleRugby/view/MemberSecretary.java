package simpleRugby.view;

import javax.swing.*;

import simpleRugby.controler.RegisterController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * MemberSecretary is main dashboard for Member Secretary.
 * 
 * It uses a side navigation panel (on the left) with buttons to switch between
 * different management views (e.g. members, user creation, squads, reports).
 * 
 * The main content area uses a CardLayout, meaning only one panel is shown at a time,
 * and the user can switch between them using the buttons.
 */

public class MemberSecretary extends JFrame {
    private CardLayout cardLayout; // layout manager for switching panels
    private JPanel mainPanel; // main panel for displaying content

    public MemberSecretary() {
        setTitle("Member Secretary View"); // title of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close app when window is closed
        setSize(800, 600);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH); // full-screen mode
        getContentPane().setLayout(new BorderLayout()); // BorderLayout for the frame

        // side panel for navigation
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(8, 1)); // 8 rows, 1 column for buttons
        sidePanel.setPreferredSize(new Dimension(200, 0)); // fixed width for the side panel

        // buttons for navigation
        JButton btnMembers = new JButton("Members");
        btnMembers.setMnemonic(KeyEvent.VK_M);
        btnMembers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        btnMembers.setFont(new Font("SansSerif", Font.BOLD, 14)); 
        
        JButton btnAddUser = new JButton("Add User");
        btnAddUser.setMnemonic(KeyEvent.VK_A);
        btnAddUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        btnAddUser.setFont(new Font("SansSerif", Font.BOLD, 14)); 
        
        JButton btnEditUser = new JButton("Edit User"); // do i need this? <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        btnEditUser.setEnabled(false);
        btnEditUser.setMnemonic(KeyEvent.VK_E);
        btnEditUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        btnEditUser.setFont(new Font("SansSerif", Font.BOLD, 14)); 
        
        JButton btnAddSquad = new JButton("Add Squad");
        btnAddSquad.setEnabled(false);
        btnAddSquad.setMnemonic(KeyEvent.VK_S);
        btnAddSquad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        btnAddSquad.setFont(new Font("SansSerif", Font.BOLD, 14)); 
        
        JButton btnReports = new JButton("Reports");
        btnReports.setEnabled(false);
        btnReports.setMnemonic(KeyEvent.VK_R);
        btnReports.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        btnReports.setFont(new Font("SansSerif", Font.BOLD, 14)); 
        
        JButton btnLogOut = new JButton("Log Out");
        btnLogOut.setMnemonic(KeyEvent.VK_L);
        btnLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        btnLogOut.setFont(new Font("SansSerif", Font.BOLD, 14)); 
        
        JPanel panel = new JPanel();
        sidePanel.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Simply Rugby");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblNewLabel.setBounds(10, 18, 180, 30);
        panel.add(lblNewLabel);
        
        JLabel lblAdministrator = new JLabel("Administrator");
        lblAdministrator.setHorizontalAlignment(SwingConstants.CENTER);
        lblAdministrator.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblAdministrator.setBounds(10, 46, 180, 30);
        panel.add(lblAdministrator);
        

        // adds buttons to side panel
        sidePanel.add(btnMembers);
        sidePanel.add(btnAddUser);
        sidePanel.add(btnEditUser);
        sidePanel.add(btnAddSquad);
        sidePanel.add(btnReports);
        sidePanel.add(btnLogOut);
        

        // main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // instances of the panel classes to the main panel
        //mainPanel.add(new MembersPanel(), "Members"); 
        
        // create a controller for registration and give it to AddUserPanel
        RegisterController myRegisterController = new RegisterController(); 
        // mainPanel.add(new AddUserPanel(myRegisterController), "Add User"); 
        
        //mainPanel.add(new EditUserPanel(), "Edit User"); 
        
        //mainPanel.add(new AddSquadPanel(), "Add Squad");
        
        //mainPanel.add(new ReportsPanel(), "Reports");

        // action listeners for buttons to switch panels
        btnMembers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Members"); 
            }
        });

        btnAddUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Add User"); 
            }
        });

        btnEditUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Edit User"); 
            }
        });
        
        btnAddSquad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Add Squad"); 
            }
        });
        
        btnReports.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Reports"); 
            }
        });
        
        btnLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        

        // adding panels to the frame
        getContentPane().add(sidePanel, BorderLayout.WEST); // side panel to the left
        getContentPane().add(mainPanel, BorderLayout.CENTER); // main panel to the center

        setVisible(true); // frame visible
    }
}

