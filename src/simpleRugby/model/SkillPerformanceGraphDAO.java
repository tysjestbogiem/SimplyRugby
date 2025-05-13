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

/**
 * This class is responsible for retrieving skill performance data from the database.
 * It's used for creating line graphs showing how a player's skills change over time.
 */
public class SkillPerformanceGraphDAO {

	/**
	 * Gets a list of all skill performance levels (all skills) for a player.
	 */
	public static List<Skill> getLineStatistics(int playerId) {
		List<Skill> skills = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection(
	            CommonConstraints.DB_URL,
	            CommonConstraints.DB_USER,
	            CommonConstraints.DB_PASSWORD)) {

	    	// Select skill name, level, and date for a specific player
	    	PreparedStatement statement = connection.prepareStatement(
	    		    "SELECT training_date, skill_level, skill_name FROM skill WHERE skill_level IS NOT NULL AND player_id = ? " +
	    		    "ORDER BY training_date ASC"
	    		);

	        statement.setInt(1, playerId); // Set player ID for query

	        ResultSet resultSet = statement.executeQuery();
	        
	        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); // formatter date

	        // Loop through results and store them in Skill objects
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

	    return skills; // Return the list
	}
	
	
	/**
	 * Gets skill performance data for a specific skill name (e.g., "Spin").
	 * This is used when user selects a skill from the drop-down to see its graph.
	 */
	public static List<Skill> getLineStatisticsForSkill(int playerId, String skillName) {
		List<Skill> skills = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection(
	            CommonConstraints.DB_URL,
	            CommonConstraints.DB_USER,
	            CommonConstraints.DB_PASSWORD)) {

	    	// Fetch records only for selected skill
	    	PreparedStatement statement = connection.prepareStatement(
	                "SELECT * FROM skill WHERE player_id = ? AND skill_name = ? "
	                + "ORDER BY training_date"

	    		);

	        statement.setInt(1, playerId);
	        statement.setString(2, skillName);

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

	    return skills; // Return the list
	}

}
