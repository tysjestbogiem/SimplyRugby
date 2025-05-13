package simpleRugby.controler;

import java.util.List;

import simpleRugby.model.ManagePlayersDAO;
import simpleRugby.model.Player;
import simpleRugby.model.SessionManager;
import simpleRugby.view.ManagePlayersPanel;

/**
 * The ManagePlayersPanelController handles communication between 
 * ManagePlayersPanel (view - GUI) and ManagePlayersPanelDAO (model layer - DAO).
 * 
 * Its main job is to load the list of players from the database and pass that data 
 * to the view so it can be shown in a table (JTable).
 */

public class ManagePlayersController {

	// Reference to the GUI panel that displays the list of players
    private ManagePlayersPanel myManagePlayersPanel; 

    /**
     * Constructor: sets up the connection between controller and view.
     * As soon as the controller is created, it loads players into the table.
     */
    public ManagePlayersController(ManagePlayersPanel myManagePlayersPanel) {
    	
        this.myManagePlayersPanel = myManagePlayersPanel;
        loadPlayers(); // call this straight away so the table is filled
    }
    
    /**
     * Loads players from the database and displays them in the table.
     * It fetches only the players linked to the currently logged-in coach.
     */
    public void loadPlayers() {
        // get list of players from ManagePlayersPanelDAO
    	int loggedUserId = SessionManager.getUserId();
        List<Player> players = ManagePlayersDAO.getAllPlayers(loggedUserId);
        
        // send the data to view to show in JTable
        myManagePlayersPanel.populateTable(players);

        // for debugging – print the number of players loaded
        // System.out.println("Loaded players: " + players.size());
    }
    
    /**
     * Updates a player's position in the database.
     */
    public boolean positionChange(int playerId, String newPosition) {
        return ManagePlayersDAO.updatePlayerPosition(playerId, newPosition);
    }
}
