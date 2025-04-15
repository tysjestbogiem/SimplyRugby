package simpleRugby.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EditUserPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	
	public EditUserPanel() {
		setLayout(new BorderLayout()); 
        add(new JLabel("Panel under construction - edit users", JLabel.CENTER), BorderLayout.CENTER); 
        setBackground(Color.LIGHT_GRAY);  

	}

}
