package simpleRugby.controler;

import java.util.List;

import simpleRugby.model.SkillDevelopmentDAO;
import simpleRugby.view.SkillDevelopmentPanel;

/**
 * Controller class for SkillDevelopmentPanel.
 * It loads data from database and passes it to view.
 */
public class SkillDevelopmentController {

    private SkillDevelopmentPanel mySkillDevelopmentPanel;

    public SkillDevelopmentController(SkillDevelopmentPanel mySkillDevelopmentPanel) {
        this.mySkillDevelopmentPanel = mySkillDevelopmentPanel;
        displayPlayers(); // populate combo box 
    }

    
    public void displayPlayers() {
        List<Object[]> players = SkillDevelopmentDAO.getPlayersCmb();

        mySkillDevelopmentPanel.populateCmb(players);
    }

}
