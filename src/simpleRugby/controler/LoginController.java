package simpleRugby.controler;

import simpleRugby.model.CoachController;

import simpleRugby.model.LoginDAO;
import simpleRugby.model.Model;
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
	
	private Model myModel; // manages data
	private LoginGUI loginGUI; // login screen
	private MemberSecretaryGUI memberSecretaryGUI;
	private CoachGUI coachGUI;
	private CoachController coachController;

	
	// constructor
	public LoginController() {
		
		// load and add starter data
		myModel = new Model();
		loginGUI = new LoginGUI(this);
		loginGUI.setVisible(true);
	}
	
		
	// checks login credentials, if valid, opens coach's view for now.
	public boolean handleLoginRequest(String username, String password) {
	    
		// ask LoginDAO - model - to validate the credentials
		boolean result = LoginDAO.validateLogin(username, password);

	    if (result) {
	    	// if it's all good, show a confirmation
	        loginGUI.displayMessage("Login accepted");

	        // if it's all good, show a confirmation
	        coachController = new CoachController(); 
	        coachController.openCoachView();

	    } else {
	    	// if login fails, tell the user
	        loginGUI.displayMessage("Login failed");
	    }

	    return result;
	}


}
