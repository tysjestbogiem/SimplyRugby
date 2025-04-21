package simpleRugby.controler;

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
	
	private Model myModel;
	private LoginGUI loginGUI;
	
	// constructor
	public LoginController() {
		myModel = new Model();
		loginGUI = new LoginGUI(this);
		loginGUI.setVisible(true);
	}
	
	// checks login credentials, if valid, opens correct view.
	public boolean handleLoginRequest(String username, String password) {
		
		 boolean loginSuccess = LoginDAO.validateLogin(username, password);

	        if (!loginSuccess) {
	            loginGUI.displayMessage("Login failed");
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
	            CoachGUI coachGUI = new CoachGUI();
	            new CoachController(coachGUI);
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

}
