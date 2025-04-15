package simpleRugby.controler;

import java.util.List;
import simpleRugby.model.ManagePlayersPanelDAO;
import simpleRugby.view.ManagePlayersPanel;

/**
 * The ManagePlayersPanelController handles communication between 
 * ManagePlayersPanel (view) and ManagePlayersPanelDAO (model layer).
 * 
 * Its main job is to load the list of players from the database and pass that data 
 * to the view so it can be shown in a table (JTable).
 */

public class ManagePlayersPanelController {

    private ManagePlayersPanel view; // the panel that shows the list of players

    // constructor takes view and loads players into it
    public ManagePlayersPanelController(ManagePlayersPanel view) {
        this.view = view;
        loadPlayers(); // call this straight away so the table is filled
    }

    // Loads all players from database and updates table in the view
    public void loadPlayers() {
        // get list of players from ManagePlayersPanelDAO
        List<Object[]> players = ManagePlayersPanelDAO.getAllPlayers();
        
        // send the data to view to show in JTable
        view.populateTable(players);

        // for debugging – print the number of players loaded
        // System.out.println("Loaded players: " + players.size());
    }
}
