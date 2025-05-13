package simpleRugby.model;

import java.time.LocalDate;
import java.util.Date;

/**
 * This class represents a skill a player has worked on.
 * It stores the skill name, category, level, date, and any coach comments.
 */

public class Skill {
	
	private int playerId;
	private Date trainingDate;
    private String skillCategory;
    private String skillName;
    private int level;
    private String comment;
    private String trainingDateChanged;
    
    
    public Skill(int playerId, Date trainingDate, String skillCategory, String skillName, int level, String comment) {
        this.playerId = playerId;         
        this.trainingDate = trainingDate; 
        this.skillCategory = skillCategory;
        this.skillName = skillName;
        this.level = level;
        this.comment = comment;
    }

	public Skill() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}
	
	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return the trainingDate
	 */
	public Date getTrainingDate() {
		return trainingDate;
	}

	/**
	 * @param trainingDate the trainingDate to set
	 */
	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}

	/**
	 * @return the skillCategory
	 */
	public String getSkillCategory() {
		return skillCategory;
	}

	/**
	 * @param skillCategory the skillCategory to set
	 */
	public void setSkillCategory(String skillCategory) {
		this.skillCategory = skillCategory;
	}

	/**
	 * @return the skillName
	 */
	public String getSkillName() {
		return skillName;
	}

	/**
	 * @param skillName the skillName to set
	 */
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	
	@Override
	public String toString() {
	    return skillName; 
	}

	/**
	 * @return the trainingDateChanged
	 */
	public String getTrainingDateChanged() {
		return trainingDateChanged;
	}

	/**
	 * @param trainingDateChanged the trainingDateChanged to set
	 */
	public void setTrainingDateChanged(String trainingDateChanged) {
		this.trainingDateChanged = trainingDateChanged;
	}


	
    
}


