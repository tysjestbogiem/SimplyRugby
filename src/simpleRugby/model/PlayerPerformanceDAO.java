package simpleRugby.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import config.CommonConstraints;

public class PlayerPerformanceDAO {
	
		// gets a list of all players from the database
		public static List<Player> getAllPlayers(int coachId) {
		    List<Player> players = new ArrayList<>();

		    try (Connection connection = DriverManager.getConnection(
		            CommonConstraints.DB_URL,
		            CommonConstraints.DB_USER,
		            CommonConstraints.DB_PASSWORD)) {

		    	// prepare statement to avoid SQL injection
		    	PreparedStatement statement = connection.prepareStatement(
		                "SELECT m.id, m.first_name, m.surname, t.team_name, p.position " +
		                "FROM member m " +
		                "INNER JOIN player p ON m.id = p.id " +
		                "INNER JOIN team t ON p.team_id = t.id " +
		                "WHERE t.coach_id = ?"
		            );

		        statement.setInt(1, coachId); // set the coach id dynamically

		        // run query and get the result set
		        ResultSet resultSet = statement.executeQuery();

		        // loop through result set and collect data
		        while (resultSet.next()) {
	                Player player = new Player();
	                player.setMemberId(resultSet.getInt("id")); 
	                player.setFirstName(resultSet.getString("first_name"));
	                player.setSurname(resultSet.getString("surname"));
	                

	                players.add(player); 
		        }

		    } catch (SQLException e) {
		    	// if anything goes wrong with db, print error so we can see what happened
		        e.printStackTrace(); 
		    }
		    // return the list of players (could be empty if no results or something failed)
		    return players;
		}

		
		
		
		
		public static List<Skill> getAllSkillsForPlayer(int playerId) {
		    List<Skill> skills = new ArrayList<>();

		    try (Connection connection = DriverManager.getConnection(
		            CommonConstraints.DB_URL,
		            CommonConstraints.DB_USER,
		            CommonConstraints.DB_PASSWORD)) {

		    	PreparedStatement statement = connection.prepareStatement(
		    		    "SELECT training_date, skill_name, skill_level " +
		    		    "FROM skill " +
		    		    "WHERE player_id = ? " +    
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
		
		public static List<Skill> getAllCommentsForPlayer(int playerId) {
		    List<Skill> skills = new ArrayList<>();

		    try (Connection connection = DriverManager.getConnection(
		            CommonConstraints.DB_URL,
		            CommonConstraints.DB_USER,
		            CommonConstraints.DB_PASSWORD)) {

		    	PreparedStatement statement = connection.prepareStatement(
		    		    "SELECT training_date, skill_name, comment " +
		    		    "FROM skill " +
		    		    "WHERE player_id = ? " +    
		    		    "ORDER BY training_date DESC"
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
		            skill.setComment(resultSet.getString("comment"));
		            skills.add(skill);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return skills;
		}





		public static List<Skill> getAllSkills() {
			List<Skill> skills = new ArrayList<>();

		    try (Connection connection = DriverManager.getConnection(
		            CommonConstraints.DB_URL,
		            CommonConstraints.DB_USER,
		            CommonConstraints.DB_PASSWORD)) {

		    	PreparedStatement statement = connection.prepareStatement(
		    			"SELECT DISTINCT skill_name FROM skill"

		    		);


		        ResultSet resultSet = statement.executeQuery();

		        while (resultSet.next()) {
		            Skill skill = new Skill();

		            skill.setSkillName(resultSet.getString("skill_name"));
		            skills.add(skill);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return skills;
		}
	}
		
	
