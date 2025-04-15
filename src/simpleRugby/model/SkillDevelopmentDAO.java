package simpleRugby.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import config.CommonConstraints;

/**
 * SkillDevelopmentDAO handles all DB queries related to skill development features.
 */
public class SkillDevelopmentDAO {

    // returns a list of players (first name + surname) from database
    public static List<Object[]> getPlayersCmb() {
        List<Object[]> playersCmb = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                CommonConstraints.DB_URL,
                CommonConstraints.DB_USER,
                CommonConstraints.DB_PASSWORD)) {

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT first_name, surname FROM members_info"
            );

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Object[] row = {
                    resultSet.getString("first_name"),
                    resultSet.getString("surname")
                };
                playersCmb.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // for debugging
        }

        return playersCmb;
    }
}
