package simpleRugby.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;



public class MembersPanel extends JPanel {
	private JTable tblMembers;


	public MembersPanel() {
        //add(new JLabel("This is the First Panel", JLabel.CENTER), BorderLayout.CENTER);
        setSize(1000, 800);
        setBackground(Color.LIGHT_GRAY);
        setLayout(null);
        
        JLabel lblMembers = new JLabel("Members");
        lblMembers.setBounds(0, 0, 1000, 21);
        lblMembers.setHorizontalAlignment(SwingConstants.CENTER);
        lblMembers.setVerticalAlignment(SwingConstants.BOTTOM);
        lblMembers.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(lblMembers);
        
        tblMembers = new JTable();
        tblMembers.setBounds(0, 21, 0, 779);
        add(tblMembers);
        
        JScrollPane pnMembers = new JScrollPane();
        pnMembers.setBounds(38, 177, 926, 569);
        add(pnMembers);
        
        JButton btnAddNewMember = new JButton("Add New Member");
        btnAddNewMember.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnAddNewMember.setBounds(38, 117, 200, 50);
        add(btnAddNewMember);
        
        
    }
}
