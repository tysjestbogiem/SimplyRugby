package simpleRugby.controler;

import java.sql.SQLException;

import simpleRugby.model.CoachController;

import simpleRugby.model.LoginDAO;
import simpleRugby.model.Model;
import simpleRugby.model.Staff;
import simpleRugby.view.CoachGUI;
import simpleRugby.view.LoginGUI;
import simpleRugby.view.MemberSecretaryGUI;

/**
 * The LoginController class handles the login logic for the application.
 * It acts as the middleman between the Login GUI (what the user sees) and the 
 * model (which handles data). It checks login credentials and opens the 
 * appropriate user interface depending on who's logged in.
 * 
 */

public class LoginController {
	
	
	private LoginGUI loginGUI;
	
	
	// constructor
	public LoginController() {
		loginGUI = new LoginGUI(this);
		loginGUI.setVisible(true);
	}
	
	// checks login credentials, if valid, opens correct view.
	public boolean handleLoginRequest(String username, String password) throws SQLException {
		
		 boolean loginSuccess = LoginDAO.validateLogin(username, password);

	        if (!loginSuccess) {
	            //loginGUI.displayMessage("Login failed");
	            return false;
	        }

	        // login succeeded, time to get role
	        String role = LoginDAO.getRoleByUsername(username);

	        if (role == null) {
	            loginGUI.displayMessage("Login failed: no role found");
	            return false;
	        }

	        loginGUI.displayMessage("Login accepted");
	        loginGUI.setVisible(false); // hide login screen

	        if (role.equalsIgnoreCase("Coach")) {
	        	CoachController coachController = new CoachController(); 
	        	CoachGUI coachGUI = new CoachGUI(coachController);      
	            coachGUI.setVisible(true);

	        } else if (role.equalsIgnoreCase("Membership Secretary")) {
	            MemberSecretaryGUI memberSecretaryGUI = new MemberSecretaryGUI();
	            new MemberSecretaryController(memberSecretaryGUI);
	            memberSecretaryGUI.setVisible(true);

	        } else {
	            loginGUI.displayMessage("Unknown role: " + role);
	            return false;
	        }

	        return true;
	    }
	
	
	// function to let user know what happens if login/ server issues
	public void loginAttempt(String username, String password) {
	    try {
	        boolean result = handleLoginRequest(username, password);

	        if (result) {
	            loginGUI.dispose(); 
	        } else {
	            loginGUI.displayMessage("Invalid login credentials!");
	            loginGUI.clearFields();
	        }

	    } catch (SQLException e) {
	        if (e.getMessage().contains("Communications link failure") || 
	            e.getMessage().toLowerCase().contains("connect")) {
	            loginGUI.displayMessage("Cannot connect to the server. Try again later.");
	        } else {
	            loginGUI.displayMessage("A database error occurred: " + e.getMessage());
	        }
	        e.printStackTrace();
	    }
	}


}
