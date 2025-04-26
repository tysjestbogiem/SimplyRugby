package simpleRugby.controler;

import java.util.List;

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
	
	public PlayerPerformanceController(PlayerPerformanceDAO playerPerformanceModel,
			PlayerPerformanceDAO playerPerformanceModel2) {
		// TODO Auto-generated constructor stub
	}

	public void displayPlayers() {
    	
    	int loggedUserId = SessionManager.getUserId();
    	List<Player> players = PlayerPerformanceDAO.getAllPlayers(loggedUserId);
    	myPlayerPerformancePanel.populateCmb(players);
    }
	
	
	public void loadTable() {
	    int selectedPlayerId = myPlayerPerformancePanel.getSelectedPlayerId(); // get player from cmb box
	    
	    if (selectedPlayerId != -1) {
	        List<Skill> skills = PlayerPerformanceDAO.getAllSkillsForPlayer(selectedPlayerId);
	        myPlayerPerformancePanel.populateTable(skills);
	    } else {
	        System.out.println("No player selected.");
	    }
	}

}
