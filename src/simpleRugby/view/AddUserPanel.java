package simpleRugby.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import simpleRugby.controler.RegisterController;
import simpleRugby.model.StaffRole;

/**
 * AddUserPanel is a registration form used by Member Secretary to add new users.
 * 
 * It lets user input a first name, surname, and select a staff role. A button
 * generates a login and temporary password. 
 * 
 */

public class AddUserPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private RegisterController myRegisterController;
	private JTextField txtSurname;
	private JTextField txtFirstName;
	private JComboBox cmbStaffRole;

	
	public AddUserPanel(RegisterController registerController) {
		
		this.myRegisterController = registerController;
		
		
		setLayout(new BorderLayout()); 
        add(new JLabel("This is the Second Panel", JLabel.CENTER), BorderLayout.CENTER); 
        setBackground(Color.LIGHT_GRAY); 
        
        // general panel settings
        setBackground(SystemColor.activeCaption);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(40, 100, 20, 20));

      
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(SystemColor.activeCaption);
        
        // form components
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
        
        JButton btnRegister = new JButton("Register");
        btnRegister.setEnabled(false);
        btnRegister.setBackground(new Color(0, 0, 0));
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegister.setFont(new Font("SansSerif", Font.BOLD, 16));
       
        
        // GroupLayout properties
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
        
        add(mainPanel, BorderLayout.CENTER);
    }

    public void displayMessage(String message) {
		JOptionPane.showMessageDialog(this, message);

	}

}
