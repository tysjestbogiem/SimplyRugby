package simpleRugby.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewMatchHistoryPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	 // view past matches
	public ViewMatchHistoryPanel() {
		
		setLayout(new BorderLayout()); 
        add(new JLabel("Panel under construction - View Match History Panel", JLabel.CENTER), BorderLayout.CENTER); 
        setBackground(Color.LIGHT_GRAY); 

	}

}
