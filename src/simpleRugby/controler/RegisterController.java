/**
 * The RegisterController handles all the logic for registering new users into the system.
 * It connects the RegisterPanel (view) to the RegisterDAO (model), and manages tasks like:
 * - generating usernames based on name/surname
 * - creating new user accounts
 * - showing messages to the user
 * 
 * It also links to the MemberSecretaryController to open the main panel after registration.
 */

package simpleRugby.controler;

import javax.swing.JOptionPane;

import simpleRugby.model.RegisterDAO;
import simpleRugby.view.MemberSecretaryGUI;
import simpleRugby.view.RegisterPanel;

/**
 * Handles the registration logic for new members.
 * Interacts with the view (e.g., RegisterGUI) and the DAO layer to store new user details.
 */
public class RegisterController {
    
    private RegisterPanel registerPanel; // the UI for registering users
    private String newUsername; // stores the generated username

    // constructor sets up the RegisterPanel and links it to this controller
    public RegisterController() {
        registerPanel = new RegisterPanel(this);
    }

    // opens the Member Secretary GUI 
    public void openMembersPanel() {
        MemberSecretaryController myMemberSecretaryController = new MemberSecretaryController();

        // Create and display the Member Secretary screen, linking both controllers
        MemberSecretaryGUI memberSecretaryGUI = new MemberSecretaryGUI(myMemberSecretaryController, this);
        memberSecretaryGUI.setVisible(true);
    }
    
    // register a new user with the generated username
    public String createNewUser() {
        // make sure a username has been generated first
        if (newUsername == null || newUsername.isEmpty()) {
            registerPanel.displayMessage("Please generate a username first.");
            return null;
        }

        // default password for new users
        String password = "simplyRugby";

        // get the selected role from the dropdown in the RegisterPanel
        String role = registerPanel.getSelectedRole();

        // call the RegisterDAO to register the user
        boolean success = RegisterDAO.register(newUsername, password, role);

        if (success) {
            registerPanel.displayMessage("User registered successfully!\nUsername: " + newUsername);
            return newUsername;
        } else {
            registerPanel.displayMessage("Registration failed. Username may already exist.");
            return null;
        }
    }

    //  generates a username based on the user's first name and surname
    public void generateLogin(String name, String surname) {
        // Check input is valid
        if (name == null || surname == null || name.trim().isEmpty() || surname.trim().isEmpty()) {
            registerPanel.displayMessage("You need to add First Name and Surname\n to generate Username");
            return;
        }

        // Convert first name to lowercase and take first 3 letters or pad if too short
        String newFName = name.toLowerCase();
        if (newFName.length() < 3) {
            newFName = String.format("%-3s", newFName).replace(' ', 's'); // pad with 's'
        } else {
            newFName = newFName.substring(0, 3);
        }

        // Same process for surname, padded with 'r'
        String newSurname = surname.toLowerCase();
        if (newSurname.length() < 3) {
            newSurname = String.format("%-3s", newSurname).replace(' ', 'r'); // pad with 'r'
        } else {
            newSurname = newSurname.substring(0, 3);
        }

        // combine parts to make username
        String username = newFName + newSurname;
        this.newUsername = username; // store it for use in registration

        // show the generated details in a popup and get user response
        int response = registerPanel.displayMessage("First Name: " + name + "\n"
                + "Surname: " + surname + "\n"
                + "Username: " + username + "\n"
                + "Password: simplyRugby");


        if (response == JOptionPane.OK_OPTION) {
            System.out.println("OK clicked - enabling register button."); // confirmation
            registerPanel.enableRegisterButton(); // allow user to register now
        }
    }

    // getter method to allow access to the register panel
    public RegisterPanel getRegisterPanel() {
        return registerPanel;
    }
}

