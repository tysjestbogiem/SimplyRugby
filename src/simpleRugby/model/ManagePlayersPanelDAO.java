package simpleRugby.model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.CommonConstraints;

/**
 * The ManagePlayersPanelDAO class is responsible for fetching player data from the database.
 * It uses JDBC to connect to the database and retrieve a list of all players,
 * returning them as a list of Object arrays, where each array holds a player's
 * first name, surname, and squad.
 * 
 * This class supports the ManagePlayersPanel in the view layer, supplying it with data to display.
 */

public class ManagePlayersPanelDAO {
	
	// gets a list of all players from the database
	public static List<Object[]> getAllPlayers() {
	    List<Object[]> players = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection(
	            CommonConstraints.DB_URL,
	            CommonConstraints.DB_USER,
	            CommonConstraints.DB_PASSWORD)) {

	    	// prepare the statement to avoid SQL injection
	        PreparedStatement statement  = connection.prepareStatement(
	        		// SQL to fetch first name, surname, and squad from the members_info table
	        		"SELECT first_name, surname, squad FROM members_info");
	        
	        // run query and get the result set
	        ResultSet resultSet = statement.executeQuery();

	        // loop through result set and collect data
	        while (resultSet.next()) {
	        	players.add(new Object[] {
	        			resultSet.getString("first_name"),
	        			resultSet.getString("surname"),
	        			resultSet.getString("squad")
	        		});
	        }

	    } catch (SQLException e) {
	    	// if anything goes wrong with the DB, print error so we can see what happened
	        e.printStackTrace(); 
	    }
	    // return the list of players (could be empty if no results or something failed)
	    return players;
	}

}
