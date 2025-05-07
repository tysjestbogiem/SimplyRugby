package simpleRugby.model;


import simpleRugby.view.CoachGUI;
import simpleRugby.view.PlayerPerformancePanel;

public class CoachController {
	
	private PlayerPerformancePanel myPlayerPerformancePanel = new PlayerPerformancePanel();
	private CoachGUI coachGUI;
	
	public CoachController(CoachGUI coachGUI) {
		this.coachGUI = coachGUI;
	}
	

	public CoachController() {
		// TODO Auto-generated constructor stub
	}


	public void openCoachView() {
		coachGUI.setVisible(true);
	}
	
	
	public void backToDefault() {
	    System.out.println("Back to default called");
	    myPlayerPerformancePanel.clearTable();
	    myPlayerPerformancePanel.clearComment();
	}

	
}

