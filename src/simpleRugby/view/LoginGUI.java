package simpleRugby.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import simpleRugby.controler.LoginController;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

/**
 * LoginGUI is main login window that asks the user for a username and password.
 * It passes the input to LoginController to check if the login is valid.
 * 
 * If login is successful, this screen closes and next GUI loads.
 * If login fails, a message appears and fields are cleared.
 */

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private LoginController myLoginController;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	
	// constructor – sets up the login screen and handles button actions
    public LoginGUI (LoginController loginController) {
    	super("Login"); // page title
    	
    	// save reference to the controller so we can call login logic
    	this.myLoginController = loginController;
    	
    	
        setAutoRequestFocus(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // full-screen mode
        getContentPane().setLayout(new BorderLayout());
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(SystemColor.controlHighlight);
        loginPanel.setPreferredSize(new Dimension(800, 1000));
        
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(SystemColor.window); 
        centerWrapper.add(loginPanel);
        contentPane.add(centerWrapper, BorderLayout.CENTER);

        
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtUsername.setColumns(1);
        
        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JLabel lblForgotPass = new JLabel("Forgot your password??");
        lblForgotPass.setHorizontalAlignment(SwingConstants.RIGHT);
        lblForgotPass.setForeground(new Color(255, 99, 71));
        lblForgotPass.setFont(new Font("SansSerif", Font.PLAIN, 12));
        
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setBackground(SystemColor.activeCaption);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String username = txtUsername.getText();
                // character because JPaswordField used 
                char[] passwordChars = txtPassword.getPassword(); 
                String password = new String(passwordChars); // convert to String

                boolean inputValid = validateInput();

                if (!inputValid) {
                    displayMessage("Enter login and password");
                    return;
                }
                
                // try to login using the controller
                boolean result = myLoginController.handleLoginRequest(username, password);

                if (result) {
                    dispose(); // close the login window
                } else {
                    displayMessage("Invalid login credentials!");
                    txtUsername.setText("");
                    txtPassword.setText("");
                }
            }
        });

        
        JLabel lblWelcome = new JLabel("Welcome Back!");
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setFont(new Font("SansSerif", Font.BOLD, 18));
        
        // layout manager for login panel
        GroupLayout gl_loginPanel = new GroupLayout(loginPanel);
        gl_loginPanel.setHorizontalGroup(
        	gl_loginPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_loginPanel.createSequentialGroup()
        			.addGap(10)
        			.addComponent(lblWelcome, GroupLayout.PREFERRED_SIZE, 430, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_loginPanel.createSequentialGroup()
        			.addGap(50)
        			.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_loginPanel.createSequentialGroup()
        			.addGap(50)
        			.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_loginPanel.createSequentialGroup()
        			.addGap(50)
        			.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_loginPanel.createSequentialGroup()
        			.addGap(50)
        			.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_loginPanel.createSequentialGroup()
        			.addGap(256)
        			.addComponent(lblForgotPass, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_loginPanel.createSequentialGroup()
        			.addGap(50)
        			.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
        );
        gl_loginPanel.setVerticalGroup(
        	gl_loginPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_loginPanel.createSequentialGroup()
        			.addGap(67)
        			.addComponent(lblWelcome, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addGap(53)
        			.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(lblForgotPass, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
        			.addGap(23)
        			.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
        );
        loginPanel.setLayout(gl_loginPanel);

        

        setVisible(true); // // show the login window
    }
    
    
    // checks that the username field isn't empty
 	private boolean validateInput() {
 		Boolean retval = true;
 		
 		if(txtUsername.getText().equals("")) {
 			retval = false;
 		}
 		
 		return retval;
 	}


 	// show a simple popup message to user
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

}



	