package simpleRugby.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import simpleRugby.controler.RegisterController;
import simpleRugby.model.StaffRole;


/**
 * RegisterPanel is form where a Member Secretary can register new users.
 * 
 * It collects the user's first name, surname, and role, and allows for generating
 * a username and default password. Once generated, the Register button becomes enabled.
 * The layout uses GroupLayout to arrange components vertically in a clean structure.
 */

public class RegisterPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private RegisterController myRegisterController;
    private JTextField txtFirstName;
    private JTextField txtSurname;
    private JComboBox<StaffRole> cmbStaffRole;
    private JButton btnRegister;

    // Constructor sets up the form and links it with the controller
    public RegisterPanel(RegisterController registerController) {
        this.myRegisterController = registerController;

        // panel settings
        setBackground(SystemColor.activeCaption);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(40, 40, 20, 20));

        // subpanel to hold the form components.
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(SystemColor.activeCaption);
        
        // Create form components.
        JLabel lblRegister = new JLabel("Register New User");
        lblRegister.setFont(new Font("SansSerif", Font.BOLD, 18));
        
        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        txtFirstName = new JTextField();
        txtFirstName.setToolTipText("Enter user's first name");
        txtFirstName.setColumns(10);
        
        JLabel lblSurname = new JLabel("Surname");
        lblSurname.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        txtSurname = new JTextField();
        txtSurname.setToolTipText("Enter user's surname");
        txtSurname.setColumns(10);
        
        JLabel lblStaffRole = new JLabel("Select Staff Role:");
        lblStaffRole.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        cmbStaffRole = new JComboBox<>(StaffRole.values());
        cmbStaffRole.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JButton btnGenerate = new JButton("Generate Login and Temporary Password");
        btnGenerate.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnGenerate.setBackground(UIManager.getColor("Button.light"));
        btnGenerate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                String name = txtFirstName.getText();
                String surname = txtSurname.getText();
                myRegisterController.generateLogin(name, surname);
                
            }
        });
        
        btnRegister = new JButton("Register");
        btnRegister.setForeground(SystemColor.inactiveCaption);
        btnRegister.setEnabled(false);
        btnRegister.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		registerController.createNewUser();
        		
        	}
        });
        btnRegister.setBackground(SystemColor.activeCaption);
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegister.setFont(new Font("SansSerif", Font.BOLD, 16));
        // Future registration logic can be added here.
        
        // Build layout using GroupLayout.
        GroupLayout gl = new GroupLayout(mainPanel);
        gl.setHorizontalGroup(
        	gl.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl.createSequentialGroup()
        			.addGroup(gl.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblRegister)
        				.addComponent(lblFirstName)
        				.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblSurname)
        				.addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblStaffRole)
        				.addComponent(cmbStaffRole, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(btnGenerate, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(38, Short.MAX_VALUE))
        );
        gl.setVerticalGroup(
        	gl.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl.createSequentialGroup()
        			.addComponent(lblRegister, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addComponent(lblSurname, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addComponent(lblStaffRole, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addComponent(cmbStaffRole, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addGap(66)
        			.addComponent(btnGenerate, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        			.addGap(38)
        			.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        			.addGap(188))
        );
        mainPanel.setLayout(gl);
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);
        
        // adds the subpanel (the form) to this panel.
        add(mainPanel, BorderLayout.CENTER);
    }
    
    // returns selected role as a string
    public String getSelectedRole() {
        return cmbStaffRole.getSelectedItem().toString();
    }


    // pops up a dialog with a message
    public int displayMessage(String message) {
        return JOptionPane.showConfirmDialog(
            this, message, "Generated Login", JOptionPane.OK_CANCEL_OPTION
        );
    }

    // enables the "Register" button once the login has been successfully generated
    public void enableRegisterButton() {
        btnRegister.setEnabled(true);
    }




}
