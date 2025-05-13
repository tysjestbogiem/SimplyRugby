package simpleRugby.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel to show training record details.
 * Currently just displays a placeholder message.
 */
public class TrainingRecordPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	//record training sessions (attendance, skills trained)
	public TrainingRecordPanel() {
		
		
		setLayout(new BorderLayout()); 
        add(new JLabel("Panel under construction - Training Record Panel", JLabel.CENTER), BorderLayout.CENTER); 
        setBackground(Color.LIGHT_GRAY); 

	}
}
