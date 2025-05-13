package simpleRugby.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import simpleRugby.controler.CoachController;
import simpleRugby.controler.ManagePlayersController;
import simpleRugby.controler.PlayerPerformanceController;
import simpleRugby.controler.SkillDevelopmentController;
import simpleRugby.model.PlayerPerformanceDAO;
import simpleRugby.model.SkillDevelopmentDAO;
import simpleRugby.view.*;

/**
 * CoachGUI is main screen shown to users with the Coach role.
 * 
 * It contains a side panel with navigation buttons for different features 
 * (like managing players or recording training), and a main panel that uses 
 * CardLayout to switch between views.
 */
public class CoachGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    
    // Coach controller (handles logic behind the scenes)
    private CoachController myCoachController;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel logo;
    
    // Panels for different features
    private SkillDevelopmentPanel skillDevelopmentPanel;
    private PlayerPerformancePanel playerPerformancePanel;
    private ManagePlayersPanel managePlayersPanel;

    // Constructor - builds the GUI
    public CoachGUI() {
        openGUI();
    }
    
    /**
     * Builds the GUI and sets everything up
     */
    private void openGUI() {
    	setTitle("Coach View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setLayout(new BorderLayout());

        // side panel
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(10, 1)); // 10 buttons same height
        sidePanel.setPreferredSize(new Dimension(200, 0)); // consistent width

        // logo panel
        logo = new JPanel();
        logo.setLayout(null);
        logo.setPreferredSize(new Dimension(200, 80));

        JLabel lblNewLabel = new JLabel("Simply Rugby");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblNewLabel.setBounds(10, 20, 180, 30);
        logo.add(lblNewLabel);

        JLabel lblCoach = new JLabel("Coach");
        lblCoach.setHorizontalAlignment(SwingConstants.CENTER);
        lblCoach.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblCoach.setBounds(10, 50, 180, 30);
        logo.add(lblCoach);

        sidePanel.add(logo);

        // buttons 
        JButton btnManagePlayers = new JButton("Manage Players");
        btnManagePlayers.setMnemonic(KeyEvent.VK_P);
        btnManagePlayers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnManagePlayers.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton btnSkillDevelopment = new JButton("Skill Development");
        btnSkillDevelopment.setMnemonic(KeyEvent.VK_S);
        btnSkillDevelopment.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSkillDevelopment.setFont(new Font("SansSerif", Font.BOLD, 14));
        
        JButton btnPlayerPerformance = new JButton("Player Performance");
        btnPlayerPerformance.setMnemonic(KeyEvent.VK_L);
        btnPlayerPerformance.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPlayerPerformance.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton btnTrainingRecord = new JButton("Training Record");
        btnTrainingRecord.setEnabled(false);
        btnTrainingRecord.setMnemonic(KeyEvent.VK_T);
        btnTrainingRecord.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnTrainingRecord.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton btnMatchPerformance = new JButton("Match Performance");
        btnMatchPerformance.setEnabled(false);
        btnMatchPerformance.setMnemonic(KeyEvent.VK_M);
        btnMatchPerformance.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMatchPerformance.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton btnViewMatchHistory = new JButton("View Match History");
        btnViewMatchHistory.setEnabled(false);
        btnViewMatchHistory.setMnemonic(KeyEvent.VK_V);
        btnViewMatchHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnViewMatchHistory.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton btnReassignment = new JButton("Reassignment");
        btnReassignment.setEnabled(false);
        btnReassignment.setMnemonic(KeyEvent.VK_R);
        btnReassignment.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReassignment.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton btnLogOut = new JButton("Log Out");
        btnLogOut.setMnemonic(KeyEvent.VK_L);
        btnLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogOut.setFont(new Font("SansSerif", Font.BOLD, 14));

        // adds buttons to side panel
        sidePanel.add(btnManagePlayers);
        sidePanel.add(btnSkillDevelopment);
        sidePanel.add(btnPlayerPerformance);
        sidePanel.add(btnTrainingRecord);
        sidePanel.add(btnMatchPerformance);
        sidePanel.add(btnViewMatchHistory);
        sidePanel.add(btnReassignment);
        sidePanel.add(btnLogOut);

        // main panel 
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setPreferredSize(new Dimension(800, 800));
        
        // Create and inject dependencies
        managePlayersPanel = new ManagePlayersPanel();
        ManagePlayersController playersController = new ManagePlayersController(managePlayersPanel);
        managePlayersPanel.setController(playersController);

        skillDevelopmentPanel = new SkillDevelopmentPanel();
        SkillDevelopmentController skillController = new SkillDevelopmentController(new SkillDevelopmentDAO(), skillDevelopmentPanel);
        skillDevelopmentPanel.setSkillDevelopmentController(skillController);

        playerPerformancePanel = new PlayerPerformancePanel();
        PlayerPerformanceController performanceController = new PlayerPerformanceController(playerPerformancePanel);
        playerPerformancePanel.setMyPlayerPerformanceController(performanceController);
        performanceController.displayPlayers();

        mainPanel.add(managePlayersPanel, "Manage Players");
        mainPanel.add(skillDevelopmentPanel, "Skill Development");
        mainPanel.add(playerPerformancePanel, "Player Performance");
        mainPanel.add(new TrainingRecordPanel(), "Training Record");
        mainPanel.add(new MatchPerformancePanel(), "Match Performance");
        mainPanel.add(new ViewMatchHistoryPanel(), "View Match History");
        mainPanel.add(new ReassignmentPanel(), "Reassignment");

        // action listeners
        // show the right panel when button is clicked
        btnManagePlayers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Manage Players");
            }
        });

        btnSkillDevelopment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Skill Development");
                skillController.reset(); // reset the form each time it's opened
            }
        });
        
        btnPlayerPerformance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardLayout.show(mainPanel, "Player Performance");
            	playerPerformancePanel.reset(); // reset the form each time it's opened
            }
        });
        
        btnTrainingRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Training Record");
            }
        });

        btnMatchPerformance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Match Performance");
            }
        });

        btnViewMatchHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "View Match History");
            }
        });

        btnReassignment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Reassignment");
            }
        });

        // Log out button closes the window
        btnLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // adds panels to frame
        getContentPane().add(sidePanel, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    
    /**
     * Lets another class set this controller (used for logic)
     */
    public void setController(CoachController controller) {
        this.myCoachController = controller;
    }
}

