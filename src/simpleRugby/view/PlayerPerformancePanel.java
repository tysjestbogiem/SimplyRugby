package simpleRugby.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

import simpleRugby.controler.PlayerPerformanceController;
import simpleRugby.controler.SkillPerformanceGraphController;
import simpleRugby.model.Player;
import simpleRugby.model.PlayerPerformanceDAO;
import simpleRugby.model.SessionManager;
import simpleRugby.model.Skill;
import simpleRugby.model.SkillDevelopmentDAO;
import simpleRugby.model.SkillPerformanceGraphDAO;
import simpleRugby.model.SquadDAO;
import javax.swing.JButton;
import javax.swing.SwingConstants;




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

	
	public void setMyPlayerPerformanceController(PlayerPerformanceController myPlayerPerformanceController) {
		this.myPlayerPerformanceController = myPlayerPerformanceController;
		
		
		SkillPerformanceGraph graphView = new SkillPerformanceGraph();
		SkillPerformanceGraphDAO graphDAO = new SkillPerformanceGraphDAO();
		SkillPerformanceGraphController graphController = new SkillPerformanceGraphController(graphDAO, graphView);

		// Save these in your panel class so you can use them later in button click
		this.mySkillPerformanceGraph = graphView;
		this.mySkillPerformanceGraphController = graphController;
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
		contentPanel.setPreferredSize(new Dimension(1200, 800));
		

		// Top Panel
		JPanel topPanel = new JPanel(new GridLayout(3, 1, 0, 10));
		topPanel.setBackground(Color.LIGHT_GRAY);
		topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

		JLabel lblTeam = new JLabel();
		lblTeam.setFont(new Font("SansSerif", Font.BOLD, 16));
		String teamName = SquadDAO.getSquadNameByCoach(SessionManager.getUserId());
		lblTeam.setText("Team: " + teamName);
		topPanel.add(lblTeam);

		JLabel lblPlayers = new JLabel("Select Player:");
		lblPlayers.setFont(new Font("SansSerif", Font.PLAIN, 16));
		topPanel.add(lblPlayers);

		cmbPlayers = new JComboBox<>();
		cmbPlayers.setFont(new Font("SansSerif", Font.PLAIN, 14)); 
		cmbPlayers.setPreferredSize(new Dimension(200, 30));
		cmbPlayers.addActionListener(e -> {
		    if (myPlayerPerformanceController != null) {
		        myPlayerPerformanceController.loadTable();
		        myPlayerPerformanceController.displayComments();
		    }
		});
		topPanel.add(cmbPlayers);

		// topPanel 
		contentPanel.add(topPanel, BorderLayout.NORTH);

		// table and ScrollPane
		model = new DefaultTableModel();
		model.addColumn("Date");
		table = new JTable(model);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.setFont(new Font("SansSerif", Font.PLAIN, 14)); 
        table.setRowHeight(20); 
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1000, 500));

		// scrollPane in CENTER
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setPreferredSize(new Dimension(1000, 300));
		bottomPanel.setBackground(Color.LIGHT_GRAY);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		
		// left: ScrollPane with comments
		txtComments = new JTextArea(10, 200); // 5 rows, 30 columns
		txtComments.setLineWrap(true);
		txtComments.setWrapStyleWord(true);
		txtComments.setPreferredSize(new Dimension(400, 400)); 
		txtComments.setFont(new Font("SansSerif", Font.PLAIN, 14)); 

		JScrollPane commentScrollPane = new JScrollPane(txtComments);
		//commentScrollPane.setPreferredSize(new Dimension(400, 800)); 

		bottomPanel.add(commentScrollPane, BorderLayout.CENTER);

		// right: buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // vertical
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));


		btnDisplayStatistics = new JButton("Display Statistics");
		btnDisplayStatistics.addActionListener(e -> {
		    int playerId = getSelectedPlayerId();
		    
		    if (playerId != -1) {
		        List<Skill> skills = SkillPerformanceGraphDAO.getLineStatistics(playerId);
		        
		        CategoryDataset dataset = mySkillPerformanceGraphController.createDataset(skills);
		        JPanel chartPanel = mySkillPerformanceGraph.createChartPanel(dataset); // use existing object!

		        JFrame frame = new JFrame("Skill Performance Graph");
		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        frame.setSize(800, 600);
		        frame.add(chartPanel);
		        frame.setVisible(true);
		    } else {
		        JOptionPane.showMessageDialog(null, "Please select a player first.", "Warning", JOptionPane.WARNING_MESSAGE);
		    }
		});


		btnDisplayStatistics.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnDisplayStatistics.setBounds(20, 25, 150, 50);
		
		JButton btnShowComments = new JButton("Show Comments");
		btnShowComments.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnShowComments.setBounds(20, 25, 150, 50);
		btnShowComments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//myPlayerPerformanceController.displayComments();
			}
		});

		buttonPanel.add(btnDisplayStatistics);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 20))); // space between
		buttonPanel.add(btnShowComments);


		bottomPanel.add(buttonPanel, BorderLayout.EAST);

		contentPanel.add(bottomPanel, BorderLayout.SOUTH);

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
	    
	    // get column names: first column "skill name", then dates
	    String[] columnNames = new String[dateColumns.size() + 1];
	    columnNames[0] = "Skill Name"; // first column is "skill name"
	    for (int i = 0; i < dateColumns.size(); i++) {
	        columnNames[i + 1] = dateColumns.get(i);
	    }
	    
	    // create new model
	    model = new DefaultTableModel(columnNames, 0);
	    table.setModel(model);
	    
	    // 5fill rows with skill names
	    for (String skillName : skillNames) {
	        Object[] row = new Object[columnNames.length];
	        row[0] = skillName; // first column is skill name
	        
	        for (int i = 0; i < dateColumns.size(); i++) {
	            String date = dateColumns.get(i);
	            // find the matching skill
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
	
	public JTextArea getTxtComments() {
		return txtComments;
	}
	
	




	
}
