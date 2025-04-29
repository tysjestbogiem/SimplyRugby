package simpleRugby.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import simpleRugby.controler.ManagePlayersController;
import simpleRugby.model.Player;
import simpleRugby.model.SessionManager;
import simpleRugby.model.SquadDAO;

/**
 * ManagePlayersPanel is the GUI panel where a coach can view list of players.
 * 
 * This panel is part of CoachGUI layout and gets filled with player data by 
 * ManagePlayersPanelController. 
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ManagePlayersPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel model;

    public ManagePlayersPanel() {
    	
        setLayout(new BorderLayout()); // main layout
        setBackground(Color.LIGHT_GRAY);

        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(50, 50, 50, 50)); 
        contentPanel.setBackground(Color.LIGHT_GRAY);

        // top panel for labels
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1, 0, 20)); 
        topPanel.setBackground(Color.LIGHT_GRAY);
        
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

        JLabel lblTeam = new JLabel();
        lblTeam.setFont(new Font("SansSerif", Font.BOLD, 16));
        String teamName = SquadDAO.getSquadNameByCoach(SessionManager.getUserId());
        lblTeam.setText("Team: " + teamName);
        topPanel.add(lblTeam);

        JLabel lblPlayers = new JLabel("Players");
        lblPlayers.setFont(new Font("SansSerif", Font.PLAIN, 16));
        topPanel.add(lblPlayers);

        contentPanel.add(topPanel, BorderLayout.NORTH);

        // Table setup
        model = new DefaultTableModel();
        String col[] = {"Name", "Surname", "Team", "Position"};
        model.setColumnIdentifiers(col);

        table = new JTable(model) {
        	@Override
            public boolean isCellEditable(int row, int column) {
                // only column 4 is editable
                return column == 4; 
            }
        };
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true); // table sorting
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        table.setFont(new Font("SansSerif", Font.PLAIN, 14)); 
        table.setRowHeight(20); 
        JScrollPane scrollPane = new JScrollPane(table);

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // add content panel to main panel
        add(contentPanel, BorderLayout.CENTER);
    }

    public void populateTable(List<Player> players) {
        model.setRowCount(0); // clear table first
        for (Player player : players) {
            model.addRow(new Object[]{
                player.getFirstName(),
                player.getSurname(),
                player.getTeamName(),
                player.getPosition()
            });
        }
    }
}

