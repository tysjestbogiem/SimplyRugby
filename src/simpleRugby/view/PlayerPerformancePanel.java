package simpleRugby.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import simpleRugby.controler.PlayerPerformanceController;
import simpleRugby.model.Player;
import simpleRugby.model.PlayerPerformanceDAO;
import simpleRugby.model.SessionManager;
import simpleRugby.model.Skill;
import simpleRugby.model.SkillDevelopmentDAO;
import simpleRugby.model.SquadDAO;




public class PlayerPerformancePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private PlayerPerformanceController myPlayerPerformanceController;
	private PlayerPerformanceDAO myPlayerPerformanceDAO;
	private JComboBox<Player> cmbPlayers;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

	
	public void setMyPlayerPerformanceController(PlayerPerformanceController myPlayerPerformanceController) {
		this.myPlayerPerformanceController = myPlayerPerformanceController;
	}
    
	public void setCmbPlayers(JComboBox<Player> cmbPlayers) {
		this.cmbPlayers = cmbPlayers;
	}
	
	public void setTable(JTable table) {
		this.table = table;
	}
	
	public PlayerPerformancePanel() {
		
		setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        contentPanel.setBackground(Color.LIGHT_GRAY);

        // top panel
        JPanel topPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0)); // gap under labels

        JLabel lblTeam = new JLabel();
        lblTeam.setFont(new Font("SansSerif", Font.BOLD, 16));
        String teamName = SquadDAO.getSquadNameByCoach(SessionManager.getUserId());
        lblTeam.setText("Team: " + teamName);
        topPanel.add(lblTeam);

        JLabel lblPlayers = new JLabel("Select Player:");
        lblPlayers.setFont(new Font("SansSerif", Font.PLAIN, 16));
        topPanel.add(lblPlayers);

        cmbPlayers = new JComboBox<>();
        cmbPlayers.setPreferredSize(new Dimension(200, 30));
        cmbPlayers.addActionListener(e -> {
            if (myPlayerPerformanceController != null) {
                myPlayerPerformanceController.loadTable();
            }
        });
        topPanel.add(cmbPlayers);

        contentPanel.add(topPanel, BorderLayout.NORTH);

        // table
        model = new DefaultTableModel();
        model.addColumn("Skill Name"); // default columns


	    model = new DefaultTableModel();
	    
	    model.addColumn("Date");
	    table = new JTable(model);
	    // scroll pane
	    scrollPane = new JScrollPane(table);
	    scrollPane.setPreferredSize(new Dimension(1000, 500));
	    
	    // adding scrollPane to content panel
	    GridBagConstraints gbc_table = new GridBagConstraints();
	    gbc_table.fill = GridBagConstraints.BOTH;
	    gbc_table.gridx = 0;
	    gbc_table.gridy = 1;
	    gbc_table.gridwidth = 2; // stretch horizontally
	    gbc_table.weightx = 1.0;
	    gbc_table.weighty = 1.0;
	    
	    contentPanel.add(scrollPane, BorderLayout.CENTER); 


	    add(contentPanel, BorderLayout.CENTER);
	}
	
	public void populateTable(List<Skill> skillList) {
	    // get unique dates
	    List<String> dateColumns = new ArrayList<>();
	    for (Skill skill : skillList) {
	        String date = skill.getTrainingDateChanged().toString();
	        if (!dateColumns.contains(date)) {
	            dateColumns.add(date);
	        }
	    }
	    
	    // get unique skill names
	    List<String> skillNames = new ArrayList<>();
	    for (Skill skill : skillList) {
	        if (!skillNames.contains(skill.getSkillName())) {
	            skillNames.add(skill.getSkillName());
	        }
	    }
	    
	    // get column names: first column "Skill Name", then dates
	    String[] columnNames = new String[dateColumns.size() + 1];
	    columnNames[0] = "Skill Name"; // first column is "Skill Name"
	    for (int i = 0; i < dateColumns.size(); i++) {
	        columnNames[i + 1] = dateColumns.get(i);
	    }
	    
	    // create new model
	    model = new DefaultTableModel(columnNames, 0);
	    table.setModel(model);
	    
	    // 5fill rows with skill names
	    for (String skillName : skillNames) {
	        Object[] row = new Object[columnNames.length];
	        row[0] = skillName; // First column is skill name
	        
	        for (int i = 0; i < dateColumns.size(); i++) {
	            String date = dateColumns.get(i);
	            // Find the matching skill
	            boolean found = false;
	            for (Skill skill : skillList) {
	                if (skill.getSkillName().equals(skillName) &&
	                    skill.getTrainingDateChanged().toString().equals(date)) {
	                    row[i + 1] = skill.getLevel(); // skill level
	                    found = true;
	                    break;
	                }
	            }
	            if (!found) {
	                row[i + 1] = "-"; // no data
	            }
	        }
	        
	        model.addRow(row);
	    }
	    

	}



	
	public void populateCmb(List<Player> players) {
        cmbPlayers.removeAllItems();

        for (Player player : players) {
            cmbPlayers.addItem(player); // add whole player object
        }
    }
	
	
	
	public int getSelectedPlayerId() {
        Player selectedPlayer = (Player) cmbPlayers.getSelectedItem();

        if (selectedPlayer != null) {
            int playerId = selectedPlayer.getMemberId();
            //System.out.println("Selected Player ID: " + playerId);
            return playerId;
        }

        System.out.println("No player selected.");
        return -1;
    }

	
}
