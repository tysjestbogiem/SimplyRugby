package simpleRugby.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel to show match reassignment details.
 * Currently just displays a placeholder message.
 */
public class ReassignmentPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// moving a player to a different squad
	public ReassignmentPanel() {
		
		setLayout(new BorderLayout()); 
        add(new JLabel("Panel under construction - Reassignment Panel", JLabel.CENTER), BorderLayout.CENTER); 
        setBackground(Color.LIGHT_GRAY); 

	}

}

