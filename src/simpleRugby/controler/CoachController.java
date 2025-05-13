package simpleRugby.controler;

import simpleRugby.view.CoachGUI;
import simpleRugby.view.PlayerPerformancePanel;

/**
 * Handles what the coach can do in the application.
 * This class helps the Coach GUI respond when buttons or screens are used.
 */
public class CoachController {

    private final CoachGUI coachGUI;

    /**
     * Connects this controller with the coach interface.
     */
    public CoachController(CoachGUI coachGUI) {
        this.coachGUI = coachGUI;
    }

    /**
     * Gets called when the coach switches to the Player Performance screen.
     * It clears out any old data from the table and comment area so the coach sees a empty screen.
     */
    public void clearPlayerPerformanceData(PlayerPerformancePanel performancePanel) {
        if (performancePanel != null) {
            performancePanel.clearTable(); // remove old table rows
            performancePanel.clearComment(); // clear the comment box
        }
    }
}
