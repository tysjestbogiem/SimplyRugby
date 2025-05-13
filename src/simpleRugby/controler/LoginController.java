package simpleRugby.controler;

import java.sql.SQLException;


import simpleRugby.model.LoginDAO;
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
	
	private LoginGUI myLoginGUI;
	private int failedAttempts = 0;
	private int MAX_ATTEMPTS = 3;
		
	/**
     * Constructor that connects this controller with the login screen.
     *      * @param loginGUI window user interacts with
     */
	public LoginController(LoginGUI myLoginGUI) {
        this.myLoginGUI = myLoginGUI;
    }
	
	
	/**
     * Checks the username and password entered by the user.
     * If correct, opens the correct interface (Coach or Membership Secretary).
     *
     */
	public boolean handleLoginRequest(String username, String password) throws SQLException {
		
		 boolean loginSuccess = LoginDAO.validateLogin(username, password);

	        if (!loginSuccess) {
	            //loginGUI.displayMessage("Login failed");
	            return false;
	        }

	        // login succeeded, time to get role
	        String role = LoginDAO.getRoleByUsername(username);

	        if (role == null) {
	        	myLoginGUI.displayMessage("Login failed: no role found");
	            return false;
	        }

	        myLoginGUI.displayMessage("Login accepted");
	        myLoginGUI.setVisible(false); // hide login screen

	        if (role.equalsIgnoreCase("Coach")) {
	        	// show coach interface
	        	CoachGUI coachGUI = new CoachGUI();
	        	CoachController coachController = new CoachController(coachGUI);
	        	coachGUI.setController(coachController);
	        	coachGUI.setVisible(true);

	        } else if (role.equalsIgnoreCase("Membership Secretary")) {
	        	// show member secretary interface
	            MemberSecretaryGUI memberSecretaryGUI = new MemberSecretaryGUI();
	            new MemberSecretaryController(memberSecretaryGUI);
	            memberSecretaryGUI.setVisible(true);

	        } else {
	        	myLoginGUI.displayMessage("Unknown role: " + role);
	            return false;
	        }

	        return true;
	    }
	
	
	/**
     * Manages full login attempt: checks credentials and handles errors.
     * Displays messages to the user in case something goes wrong.
     */
	public void loginAttempt(String username, String password) {
	    try {
	        boolean result = handleLoginRequest(username, password);

	        if (result) {
	            myLoginGUI.dispose(); // successful login
	            failedAttempts = 0;   // reset attempts
	        } else {
	            failedAttempts++;
	            
	            if (failedAttempts >= MAX_ATTEMPTS) {
	                myLoginGUI.displayMessage("Too many failed attempts.\nPlease contact the Member Secretary to reset your password.");
	                myLoginGUI.disableLogin(); // method to disable login button or input fields
	            } else {
	                myLoginGUI.displayMessage("Invalid login credentials! (" + failedAttempts + "/" + MAX_ATTEMPTS + ")");
	                myLoginGUI.clearFields();
	            }
	        }

	    } catch (SQLException e) {
	        if (e.getMessage().contains("Communications link failure") || 
	            e.getMessage().toLowerCase().contains("connect")) {
	            myLoginGUI.displayMessage("Cannot connect to the server. Try again later.");
	        } else {
	            myLoginGUI.displayMessage("A database error occurred: " + e.getMessage());
	        }
	        e.printStackTrace();
	    }
	}


}
