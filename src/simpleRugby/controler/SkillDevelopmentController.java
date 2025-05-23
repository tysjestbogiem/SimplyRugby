package simpleRugby.controler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import simpleRugby.model.Player;
import simpleRugby.model.SessionManager;
import simpleRugby.model.Skill;
import simpleRugby.model.SkillDevelopmentDAO;
import simpleRugby.model.SquadDAO;
import simpleRugby.view.SkillDevelopmentPanel;

/**
 * Controller class for SkillDevelopmentPanel.
 * It loads data from database and passes it to view.
 */

public class SkillDevelopmentController {

    private SkillDevelopmentPanel mySkillDevelopmentPanel;
    private SkillDevelopmentDAO mySkillDevelopmentDAO;
    private Map<String, String> summaryData = new LinkedHashMap<>();
    
    /**
     * Constructor - sets up panel, loads players, and sets summary keys
     */
    public SkillDevelopmentController(SkillDevelopmentDAO mySkillDevelopmentDAO, SkillDevelopmentPanel mySkillDevelopmentPanel) {
    	this.mySkillDevelopmentDAO = mySkillDevelopmentDAO;
    	this.mySkillDevelopmentPanel = mySkillDevelopmentPanel;
        
        displayPlayers(); // populate combo box 
        // setup empty elements of summary in correct order
        summaryData.put("Player", "");
        summaryData.put("Training Date", "");
        summaryData.put("Standard", "");
        summaryData.put("Spin", "");
        summaryData.put("Pop", "");
        summaryData.put("Front", "");
        summaryData.put("Rear", "");
        summaryData.put("Side", "");
        summaryData.put("Scrabble", "");
        summaryData.put("Drop", "");
        summaryData.put("Punt", "");
        summaryData.put("Grubber", "");
        summaryData.put("Goal", "");
    }

    /**
     * Loads players for the coach into the combo box 
     */
    public void displayPlayers() {
    	int loggedUserId = SessionManager.getUserId();
    	List<Player> players = SkillDevelopmentDAO.getPlayersCmb(loggedUserId);
    	mySkillDevelopmentPanel.populateCmb(players);
    }
    
    /**
     * Updates a single field in the summary map and refreshes the display
     * 
     * @param fieldName
     * @param value
     */
    public void updateSummary(String fieldName, String value) {
	        if (summaryData.containsKey(fieldName)) {
	            summaryData.put(fieldName, value);
	        }

	        refreshSummary();
	    }

	
    /**
     * Saves all skill ratings entered for the selected player and date
     */
    public void saveSkill() {
	    int playerId = mySkillDevelopmentPanel.getSelectedPlayerId();
	    // error message when player not chosen
	    if (playerId == -1) {
	        JOptionPane.showMessageDialog(null, "Please select a player!", "Missing Player", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    
	    Date trainingDate = mySkillDevelopmentPanel.getTrainingDate();
	    
	    if (trainingDate == null) {
	        JOptionPane.showMessageDialog(null, "Please select a training date!", "Missing Training Date", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    List<Skill> skills = new ArrayList<>();

	    
	    if (mySkillDevelopmentPanel.getLevelForStandard() > 0) {
	        skills.add(new Skill(playerId, trainingDate, "Passing", "Standard",
	                mySkillDevelopmentPanel.getLevelForStandard(),
	                mySkillDevelopmentPanel.getTxtCommentStandard()));
	    }

	    if (mySkillDevelopmentPanel.getLevelForSpin() > 0) {
	        skills.add(new Skill(playerId, trainingDate, "Passing", "Spin",
	                mySkillDevelopmentPanel.getLevelForSpin(),
	                mySkillDevelopmentPanel.getTxtCommentSpin()));
	    }

	    if (mySkillDevelopmentPanel.getLevelForPop() > 0) {
	        skills.add(new Skill(playerId, trainingDate, "Passing", "Pop",
	                mySkillDevelopmentPanel.getLevelForPop(),
	                mySkillDevelopmentPanel.getTxtCommentPop()));
	    }

	    if (mySkillDevelopmentPanel.getLevelForFront() > 0) {
	        skills.add(new Skill(playerId, trainingDate, "Tackling", "Front",
	                mySkillDevelopmentPanel.getLevelForFront(),
	                mySkillDevelopmentPanel.getTxtCommentFront()));
	    }

	    if (mySkillDevelopmentPanel.getLevelForRear() > 0) {
	        skills.add(new Skill(playerId, trainingDate, "Tackling", "Rear",
	                mySkillDevelopmentPanel.getLevelForRear(),
	                mySkillDevelopmentPanel.getTxtCommentRear()));
	    }

	    if (mySkillDevelopmentPanel.getLevelForSide() > 0) {
	        skills.add(new Skill(playerId, trainingDate, "Tackling", "Side",
	                mySkillDevelopmentPanel.getLevelForSide(),
	                mySkillDevelopmentPanel.getTxtCommentSide()));
	    }

	    if (mySkillDevelopmentPanel.getLevelForScrabble() > 0) {
	        skills.add(new Skill(playerId, trainingDate, "Tackling", "Scrabble",
	                mySkillDevelopmentPanel.getLevelForScrabble(),
	                mySkillDevelopmentPanel.getTxtCommentScrabble()));
	    }

	    if (mySkillDevelopmentPanel.getLevelForDrop() > 0) {
	        skills.add(new Skill(playerId, trainingDate, "Kicking", "Drop",
	                mySkillDevelopmentPanel.getLevelForDrop(),
	                mySkillDevelopmentPanel.getTxtCommentDrop()));
	    }

	    if (mySkillDevelopmentPanel.getLevelForPunt() > 0) {
	        skills.add(new Skill(playerId, trainingDate, "Kicking", "Punt",
	                mySkillDevelopmentPanel.getLevelForPunt(),
	                mySkillDevelopmentPanel.getTxtCommentPunt()));
	    }

	    if (mySkillDevelopmentPanel.getLevelForGrubber() > 0) {
	        skills.add(new Skill(playerId, trainingDate, "Kicking", "Grubber",
	                mySkillDevelopmentPanel.getLevelForGrubber(),
	                mySkillDevelopmentPanel.getTxtCommentGrubber()));
	    }

	    if (mySkillDevelopmentPanel.getLevelForGoal() > 0) {
	        skills.add(new Skill(playerId, trainingDate, "Kicking", "Goal",
	                mySkillDevelopmentPanel.getLevelForGoal(),
	                mySkillDevelopmentPanel.getTxtCommentGoal()));
	    }

	    if (!skills.isEmpty()) {
	        SkillDevelopmentDAO.saveSkills(skills);
	        mySkillDevelopmentPanel.clearForm();  
		    mySkillDevelopmentPanel.getTxtSummary().setText("");
		    summaryData.replaceAll((key, value) -> ""); // reset summary data
		    JOptionPane.showMessageDialog(null, "Skill Saved successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(null, "No skills selected to save.", "No Skills", JOptionPane.INFORMATION_MESSAGE);
	    }

    }
    
    /**
     * Refreshes the summary text area with selected values
     */
    public void refreshSummary() {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String> entry : summaryData.entrySet()) {
            if (!entry.getValue().isEmpty()) { // only show if user picked something
                builder.append(entry.getKey())
                       .append(": ")
                       .append(entry.getValue())
                       .append("\n");
            }
        }
        mySkillDevelopmentPanel.getTxtSummary().setText(builder.toString());  
     
    }
    
    /**
     * Refreshes the summary text area with selected values
     */
    public void reset() {
        summaryData.replaceAll((key, value) -> "");  // clear all summary data
        mySkillDevelopmentPanel.clearForm();         // clear visible form
    }


}



