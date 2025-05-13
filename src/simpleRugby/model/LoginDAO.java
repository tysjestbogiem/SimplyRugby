/**
 * The LoginDAO (Data Access Object) handles checking login credentials against database.
 * It connects to database, runs a query to match username and password,
 * and returns true if a valid match is found.
 * 
 * This class uses JDBC (Java Database Connectivity) to talk to the database using details provided in CommonConstraints.
 */

package simpleRugby.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.CommonConstraints;

/**
 * LoginDAO handles the login logic and database access for user authentication.
 * 
 * It connects to the database to check if a username/password exists,
 * and fetches the user's role if login is successful.
 */
public class LoginDAO {

	
	/**
     * Checks the username and password against the database.
     * If valid, it sets the session and returns true.
     */
	public static boolean validateLogin(String username, String password) throws SQLException {
		
		// this will be returned at the end – set to false by default
		boolean retVal = false;
		
		try {
			// try to connect to the database using the login details
			Connection connection = DriverManager.getConnection(
				CommonConstraints.DB_URL, 
				CommonConstraints.DB_USER, 
				CommonConstraints.DB_PASSWORD
			);
			
			// prepare a SQL query that checks if the username and password match a row			
			PreparedStatement validateUser = connection.prepareStatement(
				"SELECT * FROM staff WHERE username = ? AND password = ?"
			);
			
			// plug in the values into the placeholders (to avoid SQL injection)
			validateUser.setString(1, username);
			validateUser.setString(2, password);
			
			// run the query and get the results
			ResultSet resultSet = validateUser.executeQuery();
			
			if (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String role = resultSet.getString("staff_role");

	            // save login session
	            SessionManager.setUserSession(id, username, role);

	            retVal = true; // login successful
	        }
			
		} catch (SQLException e) {
			// if there's any problem with database stuff, print it to the console
			System.err.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
		// if nothing matched or something went wrong, return false
		return retVal;
	}
	
	/**
     * Gets the user's role based on the username.
     */
	public static String getRoleByUsername(String username) {
	    String role = null;
	    
	    try {
	    	// Connect to the database
	        Connection connection = DriverManager.getConnection(
	            CommonConstraints.DB_URL,
	            CommonConstraints.DB_USER,
	            CommonConstraints.DB_PASSWORD
	        );
	        
	        // Prepare SQL to get role for the given username
	        PreparedStatement getRole = connection.prepareStatement(
	            "SELECT staff_role FROM staff WHERE username = ?"
	        );
	        
	        getRole.setString(1, username);
	        ResultSet resultSet = getRole.executeQuery();
	        
	        // If a result is found, return the role
	        if (resultSet.next()) {
	            role = resultSet.getString("staff_role");
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return role;
	}

}

