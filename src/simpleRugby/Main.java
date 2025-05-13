package simpleRugby;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;


import simpleRugby.controler.LoginController;
import simpleRugby.controler.MemberSecretaryController;
import simpleRugby.controler.RegisterController;
import simpleRugby.model.LoginDAO;
import simpleRugby.model.RegisterDAO;
import simpleRugby.view.LoginGUI;
import simpleRugby.view.MemberSecretary;
import simpleRugby.view.RegisterPanel;


/**
 * Main class – this is the starting point of the application.
 * It sets the look and feel for the GUI and launches the login screen.
 */
public class Main {
	
	//private RegisterPanel registerPanel;

	public static void main(String[] args) {
		
        try {
            
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
 
        } catch (Exception e) {
            e.printStackTrace(); // fallback to default look if Nimbus fails
        }

        // start the GUI
        EventQueue.invokeLater(() -> {
        	LoginGUI loginGUI = new LoginGUI(); // create login window
            loginGUI.setVisible(true); // show it
        });
	}
}


