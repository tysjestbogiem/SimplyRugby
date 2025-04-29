package simpleRugby.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import config.CommonConstraints;

public class SkillPerformanceGraphDAO {
	
	private Map<String, Integer> skillData = new LinkedHashMap<>();

	public static List<Skill> getLineStatistics(int playerId) {
		List<Skill> skills = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection(
	            CommonConstraints.DB_URL,
	            CommonConstraints.DB_USER,
	            CommonConstraints.DB_PASSWORD)) {

	    	PreparedStatement statement = connection.prepareStatement(
	    		    "SELECT training_date, skill_level, skill_name FROM skill WHERE skill_level IS NOT NULL AND player_id = ? " +
	    		    "ORDER BY training_date ASC"
	    		);

	        statement.setInt(1, playerId);

	        ResultSet resultSet = statement.executeQuery();
	        
	        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); // formatter date


	        while (resultSet.next()) {
	            Skill skill = new Skill();

	            Date date = resultSet.getDate("training_date");

	            if (date != null) {
	                String formattedDate = formatter.format(date);
	                skill.setTrainingDateChanged(formattedDate); 
	            }

	            skill.setSkillName(resultSet.getString("skill_name"));
	            skill.setLevel(resultSet.getInt("skill_level"));
	            skills.add(skill);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return skills;
	}

	// Add skill performance data to local map
    public void addSkillPerformance(String date, int level) {
        skillData.put(date, level);
    }

    // Return all stored skill data
    public Map<String, Integer> getSkillData() {
        return skillData;
    }

}
