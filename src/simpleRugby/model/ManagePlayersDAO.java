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

public class ManagePlayersDAO {
	
	// gets a list of all players from the database
	public static List<Player> getAllPlayers(int coachId) {
	    List<Player> players = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection(
	            CommonConstraints.DB_URL,
	            CommonConstraints.DB_USER,
	            CommonConstraints.DB_PASSWORD)) {

	    	// prepare the statement to avoid SQL injection
	    	PreparedStatement statement = connection.prepareStatement(
	                "SELECT m.id, m.first_name, m.surname, t.team_name, p.position " +
	                "FROM member m " +
	                "INNER JOIN player p ON m.id = p.id " +
	                "INNER JOIN team t ON p.team_id = t.id " +
	                "WHERE t.coach_id = ?"
	            );

	        statement.setInt(1, coachId); // set the coach id dynamically

	        // run query and get the result set
	        ResultSet resultSet = statement.executeQuery();

	        // loop through result set and collect data
	        while (resultSet.next()) {
                Player player = new Player();
                player.setMemberId(resultSet.getInt("id"));
                player.setFirstName(resultSet.getString("first_name"));
                player.setSurname(resultSet.getString("surname"));
                player.setTeamName(resultSet.getString("team_name"));
                player.setPosition(resultSet.getString("position"));
                

                players.add(player); 
	        }

	    } catch (SQLException e) {
	    	// if anything goes wrong with the DB, print error so we can see what happened
	        e.printStackTrace(); 
	    }
	    // return the list of players (could be empty if no results or something failed)
	    return players;
	}
	
	
	public static boolean updatePlayerPosition(int playerId, String newPosition) {
	    boolean success = false;

	    try (Connection connection = DriverManager.getConnection(
	            CommonConstraints.DB_URL,
	            CommonConstraints.DB_USER,
	            CommonConstraints.DB_PASSWORD)) {

	        PreparedStatement statement = connection.prepareStatement(
	        		"UPDATE player " +
	                "JOIN member ON player.id = member.id " +
	                "SET player.position = ? " +
	                 "WHERE member.id = ?"
	        );

	        statement.setString(1, newPosition);
	        statement.setInt(2, playerId);

	        int rowsUpdated = statement.executeUpdate();
	        success = (rowsUpdated > 0);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return success;
	}

	
	


}
