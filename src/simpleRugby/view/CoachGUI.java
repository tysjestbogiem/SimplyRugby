package simpleRugby.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import simpleRugby.model.CoachController;

/**
 * CoachGUI is main screen shown to users with the Coach role.
 * 
 * It contains a side panel with navigation buttons for different features 
 * (like managing players or recording training), and a main panel that uses 
 * CardLayout to switch between views.
 */

public class CoachGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private CoachController myCoachController;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public CoachGUI(CoachController coachController) {
        setTitle("Coach View");
        this.myCoachController = coachController;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setLayout(new BorderLayout());

        // side panel
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(9, 1)); // all buttons same height
        sidePanel.setPreferredSize(new Dimension(200, 0)); // consistent width

        // logo panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(200, 80));

        JLabel lblNewLabel = new JLabel("Simply Rugby");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblNewLabel.setBounds(10, 20, 180, 30);
        panel.add(lblNewLabel);

        JLabel lblCoach = new JLabel("Coach");
        lblCoach.setHorizontalAlignment(SwingConstants.CENTER);
        lblCoach.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblCoach.setBounds(10, 50, 180, 30);
        panel.add(lblCoach);

        sidePanel.add(panel);

        // buttons 
        JButton btnManagePlayers = new JButton("Manage Players");
        btnManagePlayers.setMnemonic(KeyEvent.VK_P);
        btnManagePlayers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnManagePlayers.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton btnSkillDevelopment = new JButton("Skill Development");
        btnSkillDevelopment.setMnemonic(KeyEvent.VK_S);
        btnSkillDevelopment.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSkillDevelopment.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton btnTrainingRecord = new JButton("Training Record");
        btnTrainingRecord.setMnemonic(KeyEvent.VK_T);
        btnTrainingRecord.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnTrainingRecord.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton btnMatchPerformance = new JButton("Match Performance");
        btnMatchPerformance.setMnemonic(KeyEvent.VK_M);
        btnMatchPerformance.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMatchPerformance.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton btnViewMatchHistory = new JButton("View Match History");
        btnViewMatchHistory.setMnemonic(KeyEvent.VK_V);
        btnViewMatchHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnViewMatchHistory.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton btnReassignment = new JButton("Reassignment");
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
        sidePanel.add(btnTrainingRecord);
        sidePanel.add(btnMatchPerformance);
        sidePanel.add(btnViewMatchHistory);
        sidePanel.add(btnReassignment);
        sidePanel.add(btnLogOut);

        // main panel 
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new ManagePlayersPanel(), "Manage Players");
        mainPanel.add(new SkillDevelopmentPanel(), "Skill Development");
        mainPanel.add(new TrainingRecordPanel(), "Training Record");
        mainPanel.add(new MatchPerformancePanel(), "Match Performance");
        mainPanel.add(new ViewMatchHistoryPanel(), "View Match History");
        mainPanel.add(new ReassignmentPanel(), "Reassignment");

        // action listeners
        btnManagePlayers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Manage Players");
            }
        });

        btnSkillDevelopment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Skill Development");
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
}

