package simpleRugby.model;


import simpleRugby.view.CoachGUI;

public class CoachController {
	
	private CoachGUI coachGUI;
	
	public CoachController(CoachGUI coachGUI) {
		this.coachGUI = coachGUI;
	}


	public void openCoachView() {
		coachGUI.setVisible(true);
	}
}

