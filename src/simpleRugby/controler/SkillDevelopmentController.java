package simpleRugby.controler;

import java.util.List;

import javax.swing.JOptionPane;

import simpleRugby.model.Player;
import simpleRugby.model.Skill;
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
        List<Player> players = SkillDevelopmentDAO.getPlayersCmb();

        mySkillDevelopmentPanel.populateCmb(players);
    }


    public void saveSkill() {
    	
    	// get user id
    	
    	// get date
    	
    	// get skills

    	

    }


}
