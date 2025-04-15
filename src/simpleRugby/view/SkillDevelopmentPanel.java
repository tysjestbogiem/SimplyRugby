package simpleRugby.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SkillDevelopmentPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	//update player skills & training notes
	public SkillDevelopmentPanel() {
		
		setLayout(new BorderLayout()); 
        add(new JLabel("Panel under construction - Skill Development Panel", JLabel.CENTER), BorderLayout.CENTER); 
        setBackground(Color.LIGHT_GRAY); 

	}

}
