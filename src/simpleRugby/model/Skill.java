package simpleRugby.model;

import java.time.LocalDate;

public class Skill {
	
    private int playerId;
    private LocalDate trainingDate;
    private String category;
    private String name;
    private int level;
    private String comment;
    
    
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public LocalDate getTrainingDate() {
		return trainingDate;
	}
	public void setTrainingDate(LocalDate trainingDate) {
		this.trainingDate = trainingDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

    
}


