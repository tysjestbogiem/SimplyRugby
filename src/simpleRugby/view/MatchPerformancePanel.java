package simpleRugby.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MatchPerformancePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// view match details (performance, scores, comments)
	public MatchPerformancePanel() {
		
		setLayout(new BorderLayout()); 
        add(new JLabel("Panel under construction - Match Performance Panel", JLabel.CENTER), BorderLayout.CENTER); 
        setBackground(Color.LIGHT_GRAY); 


	}

}
