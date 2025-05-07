package simpleRugby.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import simpleRugby.model.ManagePlayersDAO;
import simpleRugby.model.Player;
import simpleRugby.model.PlayerPerformanceDAO;
import simpleRugby.model.SessionManager;
import simpleRugby.model.Skill;
import simpleRugby.view.PlayerPerformancePanel;

public class PlayerPerformanceController {
	
	private PlayerPerformancePanel myPlayerPerformancePanel;

	// constructor
	public PlayerPerformanceController(PlayerPerformancePanel myPlayerPerformancePanel) {
		
		this.myPlayerPerformancePanel = myPlayerPerformancePanel;
		
	}


	public PlayerPerformanceController() {
		// TODO Auto-generated constructor stub
	}


	public void displayPlayers() {
    	
    	int loggedUserId = SessionManager.getUserId();
    	List<Player> players = PlayerPerformanceDAO.getAllPlayers(loggedUserId);
    	myPlayerPerformancePanel.populateCmb(players);
    	myPlayerPerformancePanel.setUploadPlayers(true);

    }
	
	public void displaySkills() {
		List<Skill> skills = PlayerPerformanceDAO.getAllSkills();
		myPlayerPerformancePanel.populateSkillCmb(skills);
	}
	
	
	
	public DefaultTableModel getTableModelForSkills(List<Skill> skillList, Map<String, Double> avgScores) {

	    // get dates
	    List<String> dateColumns = new ArrayList<>();
	    for (Skill skill : skillList) {
	        String date = skill.getTrainingDateChanged().toString();
	        if (!dateColumns.contains(date)) {
	            dateColumns.add(date);
	        }
	    }
	    
	    // get skill names
	    List<String> skillNames = new ArrayList<>();
	    for (Skill skill : skillList) {
	        if (!skillNames.contains(skill.getSkillName())) {
	            skillNames.add(skill.getSkillName());
	        }
	    }
	    
	    // get column names: first column "skill name", then dates
	    String[] columnNames = new String[dateColumns.size() + 2];
	    columnNames[0] = "Skill Name"; // first column is "skill name"
	    // add average score here????
	    for (int i = 0; i < dateColumns.size(); i++) {
	        columnNames[i + 1] = dateColumns.get(i);
	    }
	    columnNames[columnNames.length - 1] = "Average";
	    
	    // create new model
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	    
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
	        
	        double averageScore = avgScores.getOrDefault(skillName, 0.0);
	        row[columnNames.length - 1] = String.format("%.2f", averageScore);
	        model.addRow(row);
	    }
	    
	    return model;
	}
	
	
	public void loadTable() {
	    int selectedPlayerId = myPlayerPerformancePanel.getSelectedPlayerId(); // get player from cmb box
	    
	    if (selectedPlayerId != -1) {
	        List<Skill> skills = PlayerPerformanceDAO.getAllSkillsForPlayer(selectedPlayerId);
	        Map<String, Double> averageScore = PlayerPerformanceDAO.getAverageScorePerSkill(selectedPlayerId);
	        DefaultTableModel model = getTableModelForSkills(skills, averageScore);
	        myPlayerPerformancePanel.updateTableModel(model);
	    } else {
	        //System.out.println("No player selected.");
	    	myPlayerPerformancePanel.clearTable();
	    	myPlayerPerformancePanel.clearComment();
	    	
	    }
	}
	
	public void displayComments() {
	    int selectedPlayerId = myPlayerPerformancePanel.getSelectedPlayerId(); // get player from cmb box

	    if (selectedPlayerId != -1) {
	        List<Skill> skills = PlayerPerformanceDAO.getAllCommentsForPlayer(selectedPlayerId);

	        StringBuilder commentsBuilder = new StringBuilder();

	        for (Skill skill : skills) {
	            if (skill.getComment() != null && !skill.getComment().trim().isEmpty()) {
	                commentsBuilder.append("Date: ").append(skill.getTrainingDateChanged())
	                               .append(" | Skill: ").append(skill.getSkillName())
	                               .append(" | Comment: ").append(skill.getComment())
	                               .append("\n");
	            }
	        }

	        myPlayerPerformancePanel.getTxtComments().setText(commentsBuilder.toString());
	    } else {
	        //System.out.println("No player selected.");
	    	myPlayerPerformancePanel.clearTable();
	    	myPlayerPerformancePanel.clearComment();
	    }
	}

}
