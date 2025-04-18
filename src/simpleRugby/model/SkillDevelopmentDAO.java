package simpleRugby.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import config.CommonConstraints;

/**
 * SkillDevelopmentDAO handles all DB queries related to skill development features.
 */
public class SkillDevelopmentDAO {

    // returns a list of players (first name + surname) from database
    public static List<Player> getPlayersCmb() {
        List<Player> playersCmb = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                CommonConstraints.DB_URL,
                CommonConstraints.DB_USER,
                CommonConstraints.DB_PASSWORD)) {

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, first_name, surname FROM member"
            		// where coach is.. 
            );

            ResultSet resultSet = statement.executeQuery();

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

    
    public static void saveSkills(int playerId, LocalDate trainingDate, List<Skill> skills) {

        try (Connection connection = DriverManager.getConnection(
                CommonConstraints.DB_URL,
                CommonConstraints.DB_USER,
                CommonConstraints.DB_PASSWORD)) {

            PreparedStatement addSkill = connection.prepareStatement(
                    "INSERT INTO player_skills (player_id, training_date, category, skill_name, skill_level, comment) " +
                    "VALUES (?, ?, ?, ?, ?, ?)"
            );

            for (Skill skill : skills) {
                addSkill.setInt(1, playerId);
                addSkill.setDate(2, Date.valueOf(trainingDate));
                addSkill.setString(3, skill.getCategory());
                addSkill.setString(4, skill.getName());
                addSkill.setInt(5, skill.getLevel());
                addSkill.setString(6, skill.getComment());
                addSkill.addBatch(); // add multiple skills at once
            }

            addSkill.executeBatch(); // execute all in one go
            addSkill.close();
            System.out.println("Skills saved successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


	public static int getPlayerIdByName(String selectedPlayerName) {
		// TODO Auto-generated method stub
		return 0;
	}
    
    
}
