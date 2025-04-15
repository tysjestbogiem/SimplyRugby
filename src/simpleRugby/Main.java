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
import simpleRugby.model.CoachController;
import simpleRugby.model.LoginDAO;
import simpleRugby.model.RegisterDAO;
import simpleRugby.view.MemberSecretary;
import simpleRugby.view.RegisterPanel;



public class Main {
	
	private RegisterPanel registerPanel;

	public static void main(String[] args) {
		
		
		
        try {
            
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // start controller 
        EventQueue.invokeLater(() -> {
            //LoginController myLoginController = new LoginController();
        	//MemberSecretaryController myMemberSecretaryController = new MemberSecretaryController(); 
        	//new MemberSecretary(); // Launch the application
            
            CoachController myCoachController = new CoachController();
        	
        	//System.out.println(MyJDBC.checkUser("mardul"));
        	
        	//System.out.println(RegisterDAO.register("mardul", "test", "coach"));
            
            //System.out.println(LoginDAO.validateLogin("user1", "user123"));
        	//System.out.println("Displayed RegisterPanel: " + registerPanel.hashCode());

        });
		
		
	}

}


