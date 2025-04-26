package simpleRugby.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.CommonConstraints;

public class SquadDAO {
	
	public static String getSquadNameByCoach(int coachId) {
		
		String squad = null;
		
		try (Connection connection = DriverManager.getConnection(
	            CommonConstraints.DB_URL,
	            CommonConstraints.DB_USER,
	            CommonConstraints.DB_PASSWORD)) {

	    	// prepare the statement to avoid SQL injection
	    	PreparedStatement statement = connection.prepareStatement(
	    			"SELECT team_name FROM team WHERE coach_id = ?"
	    			 );
	    	
	    	statement.setInt(1, coachId); // set the coach id dynamically

	        // run query and get the result set
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            squad = resultSet.getString("team_name");
	        }
	        
		} catch (SQLException e) {
	    	// if anything goes wrong with  db, print error so we can see what happened
	        e.printStackTrace(); 
	    }
	    // return the list of players 
	    return squad;
	}

}
