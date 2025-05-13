package simpleRugby.view;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.CategoryDataset;

import simpleRugby.controler.PlayerPerformanceController;
import simpleRugby.controler.SkillPerformanceGraphController;
import simpleRugby.model.*;


/**
 * This panel lets coaches view how a player has performed in training.
 * 
 * Coaches can:
 * - Pick a player to view their skill ratings over time
 * - Read comments left during training
 * - Choose a skill and see a graph showing performance changes
 */

public class PlayerPerformancePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private PlayerPerformanceController myPlayerPerformanceController;
	private PlayerPerformanceDAO myPlayerPerformanceDAO;
	private JComboBox<Player> cmbPlayers;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JPanel bottomPanel;
	private JTextArea txtComments;
	private JButton btnDisplayStatistics;
	private SkillPerformanceGraph mySkillPerformanceGraph;
	private SkillPerformanceGraphController mySkillPerformanceGraphController;
	private JComboBox<Skill> cmbSkills;
	private boolean uploadPlayers = false; // shows empty table 

	public PlayerPerformancePanel() {

		// Set up panel layout and background
		setLayout(new BorderLayout());
		setBackground(Color.LIGHT_GRAY);

		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setPreferredSize(new Dimension(1200, 800));

		// Top panel
		// Shows team name and let the coach choose a player from the dropdown
		JPanel topPanel = new JPanel(new GridLayout(3, 1, 0, 10));
		topPanel.setBackground(Color.LIGHT_GRAY);
		topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

		// Team name label
		JLabel lblTeam = new JLabel("Team: " + SquadDAO.getSquadNameByCoach(SessionManager.getUserId()));
		lblTeam.setFont(new Font("SansSerif", Font.BOLD, 16));
		topPanel.add(lblTeam);

		// Player selection label and combo box
		JLabel lblPlayers = new JLabel("Select Player:");
		lblPlayers.setFont(new Font("SansSerif", Font.PLAIN, 16));
		topPanel.add(lblPlayers);

		cmbPlayers = new JComboBox<>();
		cmbPlayers.setFont(new Font("SansSerif", Font.PLAIN, 14));
		cmbPlayers.setPreferredSize(new Dimension(200, 30));
		// When a player is selected, updates table and comment section
		cmbPlayers.addActionListener(e -> {
		    if (!uploadPlayers) {
		    	return; // empty table when default (empty) player
		    }

		    if (myPlayerPerformanceController != null) {
		        myPlayerPerformanceController.loadTable();
		        myPlayerPerformanceController.displayComments();
		    }
		});

		topPanel.add(cmbPlayers);

		contentPanel.add(topPanel, BorderLayout.NORTH);

		// Middle - Skill table
		// Create a table to display the player's past skill ratings
		model = new DefaultTableModel();
		model.addColumn("Date");
		table = new JTable(model);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
		table.setFont(new Font("SansSerif", Font.PLAIN, 14));
		table.setRowHeight(20);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1000, 500));
		contentPanel.add(scrollPane, BorderLayout.CENTER);

		// This section has comment box and a panel to show skill performance charts
		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setPreferredSize(new Dimension(1000, 300));
		bottomPanel.setBackground(Color.LIGHT_GRAY);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		// Comment box
		txtComments = new JTextArea(10, 200);
		txtComments.setLineWrap(true);
		txtComments.setWrapStyleWord(true);
		txtComments.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JScrollPane commentScrollPane = new JScrollPane(txtComments);
		bottomPanel.add(commentScrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));

		// Lets the coach choose which skill to show on the graph
		cmbSkills = new JComboBox<>();
		cmbSkills.setFont(new Font("SansSerif", Font.PLAIN, 14));
		cmbSkills.setMaximumSize(new Dimension(200, 30)); 
		cmbSkills.setAlignmentX(Component.CENTER_ALIGNMENT); 
		cmbSkills.setToolTipText("Select a skill to view performance graph");

		// When clicked, it shows a graph of how the player has performed in the selected skill
		btnDisplayStatistics = new JButton("Display Statistics");
		btnDisplayStatistics.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnDisplayStatistics.setMaximumSize(new Dimension(200, 30));
		btnDisplayStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnDisplayStatistics.setMnemonic(KeyEvent.VK_D);
		btnDisplayStatistics.setToolTipText("Generate chart for selected skill");
		
		// Display specific skills <-- looks better, 
		btnDisplayStatistics.addActionListener((ActionEvent e) -> {
		    int playerId = getSelectedPlayerId();
		    Skill selectedSkill = (Skill) cmbSkills.getSelectedItem();

		    if (playerId != -1 && selectedSkill != null) {
		        String skillName = selectedSkill.getSkillName(); // get selected skill

		        // get only specific skill for player
		        List<Skill> skillData = SkillPerformanceGraphDAO.getLineStatisticsForSkill(playerId, skillName);

		        if (skillData.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "No data available for this skill.", "Info", JOptionPane.INFORMATION_MESSAGE);
		            return;
		        }

		        CategoryDataset dataset = mySkillPerformanceGraphController.createDataset(skillData);
		        JPanel chartPanel = mySkillPerformanceGraph.createChartPanel(dataset);

		        JFrame frame = new JFrame("Player performance: " + skillName);
		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        frame.setSize(800, 600);
		        frame.getContentPane().add(chartPanel);
		        frame.setVisible(true);
		    } else {
		        JOptionPane.showMessageDialog(null, "Please select a player and a skill.", "Warning", JOptionPane.WARNING_MESSAGE);
		    }
		});


		// ad skill combo and button to panel
		buttonPanel.add(cmbSkills);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // spacing
		buttonPanel.add(btnDisplayStatistics);

		// a whole button area to bottom right
		bottomPanel.add(buttonPanel, BorderLayout.EAST);
		contentPanel.add(bottomPanel, BorderLayout.SOUTH);

		// add full layout to main panel
		add(contentPanel, BorderLayout.CENTER);
	}

	/**
	 * Setter called after the controller is created.
	 * Connects the controller to the view and fills in player/skill data.
	 */
	public void setMyPlayerPerformanceController(PlayerPerformanceController myPlayerPerformanceController) {
		this.myPlayerPerformanceController = myPlayerPerformanceController;

		// set up skill graph components
		mySkillPerformanceGraph = new SkillPerformanceGraph();
		SkillPerformanceGraphDAO graphDAO = new SkillPerformanceGraphDAO();
		mySkillPerformanceGraphController = new SkillPerformanceGraphController(graphDAO, mySkillPerformanceGraph);

		// load player and skill data for combo boxes
		myPlayerPerformanceController.displayPlayers();
		myPlayerPerformanceController.displaySkills();
	}

	// Allows controller to update the table
	public void updateTableModel(DefaultTableModel model) {
		table.setModel(model);
	}

	// Fill the player dropdown with the list of players
	public void populateCmb(List<Player> players) {
		cmbPlayers.removeAllItems();
		for (Player player : players) {
			cmbPlayers.addItem(player);
		}
		cmbPlayers.setSelectedIndex(-1);
	}

	// Fill the skill dropdown with the list of available skills
	public void populateSkillCmb(List<Skill> skills) {
		cmbSkills.removeAllItems();
		for (Skill skill : skills) {
			cmbSkills.addItem(skill);
		}
		 
	}

	// Get the ID of the selected player, or -1 if none is picked
	public int getSelectedPlayerId() {
		Player selectedPlayer = (Player) cmbPlayers.getSelectedItem();
		if (selectedPlayer != null) {
			return selectedPlayer.getMemberId();
		}
		//System.out.println("No player selected.");
		return -1;
	}

	// Return the comment text area so the controller can update it
	public JTextArea getTxtComments() {
		return txtComments;
	}

	// Tells the view whether data is ready to load
	public void setUploadPlayers(boolean upload) {
		this.uploadPlayers = upload;
	}
	
	// Clear the table content
	public void clearTable() {
		String[] columnNames = {"Skill Name"}; 
	    Object[][] emptyData = {};
	    DefaultTableModel emptyModel = new DefaultTableModel(emptyData, columnNames);
	    table.setModel(emptyModel);
	}
	
	// Clear the comment box
	public void clearComment() {
		txtComments.setText(" ");
	}
	
	// Reset everything: table, comments, and selected player
	public void reset() {
	    clearTable();
	    clearComment();
	    cmbPlayers.setSelectedIndex(-1); 
	}
}

