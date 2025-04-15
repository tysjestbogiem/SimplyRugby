package simpleRugby.model;

import simpleRugby.view.CoachGUI;

public class CoachController {
	
	private CoachGUI coachGUI;
	
	
	public CoachController() {
		
		coachGUI = new CoachGUI(this);
	}
	
	public void openCoachView() {
		
		coachGUI.setVisible(true);
		
	}
	

}
