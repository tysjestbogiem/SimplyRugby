/**
 * The RegisterDAO class handles all database operations related to user registration.
 * It includes two main methods:
 * 
 * - register(): Adds a new user to the database if the username doesn’t already exist
 * - checkUser(): Checks if a user with the given username already exists
 */

package simpleRugby.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.CommonConstraints;

public class RegisterDAO {
	
	// tries to register a new user into database
	public static boolean register(String username, String password, String role) {
		
		// first check if the username is already taken
		if (!checkUser(username)) {
			try {
				// connect to the database
				Connection connection = DriverManager.getConnection(
						CommonConstraints.DB_URL, 
						CommonConstraints.DB_USER, 
						CommonConstraints.DB_PASSWORD);

				// prepare SQL query to insert a new user
				PreparedStatement insertUser = connection.prepareStatement(
					"INSERT INTO user_info (username, password, role) VALUES (?, ?, ?)"
				);
				
				// plug the username, password, and role into the query
				insertUser.setString(1, username);
				insertUser.setString(2, password);
				insertUser.setString(3, role);

				// execute the query to insert the new user
				insertUser.executeUpdate();
				
				// if everything went well, return true
				return true;

			} catch (SQLException e) {
				// print any database errors to console for debugging
				e.printStackTrace();
			}
		}
		
		// return false if username exists or something went wrong
		return false;
	}
	
	// checks if a user already exists in database
	public static boolean checkUser(String username) {
		try {
			// connect to the database
			Connection connection = DriverManager.getConnection(
				CommonConstraints.DB_URL, 
				CommonConstraints.DB_USER, 
				CommonConstraints.DB_PASSWORD
			);

			// prepare a query to search for username
			PreparedStatement checkUserExists = connection.prepareStatement(
				"SELECT * FROM staff WHERE username = ?"
			);

			// plug in username to the query
			checkUserExists.setString(1, username);

			// run query
			ResultSet resultSet = checkUserExists.executeQuery();

			// if there's no data, user doesn't exist
			if (!resultSet.isBeforeFirst()) {
				return false;
			}

		} catch (SQLException e) {
			// print the error if query fails
			e.printStackTrace();
		}

		// if data was found, user exists
		return true;
	}
}

