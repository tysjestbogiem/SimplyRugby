package simpleRugby.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReportsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ReportsPanel() {
		setLayout(new BorderLayout()); 
        add(new JLabel("Panel under construction - reports", JLabel.CENTER), BorderLayout.CENTER); 
        setBackground(Color.LIGHT_GRAY); 

	}

}
