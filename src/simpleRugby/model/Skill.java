package simpleRugby.model;

import java.time.LocalDate;
import java.util.Date;

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


	public String getSkillCategory() {
		return skillCategory;
	}

	public void setSkillCategory(String skillCategory) {
		this.skillCategory = skillCategory;
	}

	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getTrainingDate() {
	    return trainingDate; 
	}

	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getTrainingDateChanged() {
		return trainingDateChanged;
	}

	public void setTrainingDateChanged(String trainingDateChanged) {
		this.trainingDateChanged = trainingDateChanged;
	}

	@Override
	public String toString() {
	    return skillName; 
	}


	
    
}


