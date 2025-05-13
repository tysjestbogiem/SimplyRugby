package simpleRugby.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import config.CommonConstraints;

/**
 * SkillDevelopmentDAO handles all DB queries related to skill development features.
 * It provides methods to fetch players and save skill records to the database.
 */
public class SkillDevelopmentDAO {

		/**
		 * Gets a list of players that belong to the coach's team.
		 * Only fetches ID, first name, and surname to populate combo boxes.
		 */
	    public static List<Player> getPlayersCmb(int coachId) {
	        List<Player> playersCmb = new ArrayList<>();

	        try (Connection connection = DriverManager.getConnection(
	                CommonConstraints.DB_URL,
	                CommonConstraints.DB_USER,
	                CommonConstraints.DB_PASSWORD)) {

	        	// SQL joins member, player, and team tables to find players for a coach
	            PreparedStatement statement = connection.prepareStatement(
	                "SELECT m.id, m.first_name, m.surname " +
	                "FROM member m " +
	                "INNER JOIN player p ON m.id = p.id " +
	                "INNER JOIN team t ON p.team_id = t.id " +
	                "WHERE t.coach_id = ?"
	            );

	            statement.setInt(1, coachId); // replace the ? with the actual coach I

	            ResultSet resultSet = statement.executeQuery();

	            // loop through the results and add each player to the list
	            while (resultSet.next()) {
	                Player player = new Player();
	                player.setMemberId(resultSet.getInt("id"));
	                player.setFirstName(resultSet.getString("first_name"));
	                player.setSurname(resultSet.getString("surname"));

	                playersCmb.add(player);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace(); // for debugging
	        }

	        return playersCmb;
	    }
	 
	
    /**
     * Saves a list of skill details into the database.
     * Each skill includes the player, date, category, level, and optional comment.
     */
    public static void saveSkills(List<Skill> skills) {

        try (Connection connection = DriverManager.getConnection(
                CommonConstraints.DB_URL,
                CommonConstraints.DB_USER,
                CommonConstraints.DB_PASSWORD)) {

        	// SQL command to insert skill information into the database
            PreparedStatement addSkill = connection.prepareStatement(
                    "INSERT INTO skill (player_id, training_date, category, skill_name, skill_level, comment) " +
                    "VALUES (?, ?, ?, ?, ?, ?)"
            );

            // Add each skill to a batch to be inserted all at once
            for (Skill skill : skills) {
                addSkill.setInt(1, skill.getPlayerId());
                addSkill.setDate(2, new java.sql.Date(skill.getTrainingDate().getTime()));
                addSkill.setString(3, skill.getSkillCategory());
                addSkill.setString(4, skill.getSkillName());
                addSkill.setInt(5, skill.getLevel());
                addSkill.setString(6, skill.getComment());
                addSkill.addBatch(); // add multiple skills at once
                
                // Print each skill info to the console
//                System.out.println("Saving skill:");
//                System.out.println("Player ID: " + skill.getPlayerId());
//                System.out.println("Training Date: " + new java.sql.Date(skill.getTrainingDate().getTime()));
//                System.out.println("Skill Category: " + skill.getSkillCategory());
//                System.out.println("Skill Name: " + skill.getSkillName());
//                System.out.println("Skill Level: " + skill.getLevel());
//                System.out.println("Comment: " + skill.getComment());
            }
            
            addSkill.executeBatch(); // execute all in one go
            addSkill.close();
            //System.out.println("Skills saved successfully.");

        } catch (SQLException e) {
            e.printStackTrace(); // show DB errors
        }
    }
}
