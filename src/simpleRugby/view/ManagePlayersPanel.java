package simpleRugby.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import simpleRugby.controler.ManagePlayersController;


/**
 * ManagePlayersPanel is the GUI panel where a coach can view list of players.
 * 
 * This panel is part of CoachGUI layout and gets filled with player data by 
 * ManagePlayersPanelController. 
 */

public class ManagePlayersPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;

	
	// Constructor – builds the interface
	public ManagePlayersPanel() {
	    setLayout(null);
	    setBackground(Color.LIGHT_GRAY); 

	    JLabel lblTeam = new JLabel("Teams name");
	    lblTeam.setFont(new Font("SansSerif", Font.BOLD, 16));
	    lblTeam.setBounds(50, 50, 300, 50);
	    add(lblTeam);

	    JLabel lblPlayers = new JLabel("Players");
	    lblPlayers.setFont(new Font("SansSerif", Font.PLAIN, 16));
	    lblPlayers.setBounds(50, 100, 200, 50);
	    add(lblPlayers);

	    JPanel panel = new JPanel();
	    panel.setBounds(36, 175, 894, 451);
	    panel.setLayout(null); // so we can use setBounds
	    add(panel);

	    // panel to hold scrollable table
	    model = new DefaultTableModel();
	    model.addColumn("First Name");
	    model.addColumn("Surname");
	    model.addColumn("Squad");
	    
	    // set up table model with three columns - for now, that will chanage <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	    model = new DefaultTableModel(new Object[]{"First Name", "Surname", "Squad"}, 0) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; 
	        }
	    };

	    // create table and wrap it in a scroll pane
	    table = new JTable(model); 
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(0, 0, 880, 450);
	    panel.add(scrollPane);

	    // load data into table via controller
	    new ManagePlayersController(this);
	}


	// called by controller to fill table with player data
    public void populateTable(List<Object[]> players) {
        model.setRowCount(0); // clear table first
        for (Object[] row : players) {
            model.addRow(row);
        }
    }
}
