package simpleRugby.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.CommonConstraints;

/**
 * This method gets the name of the team managed by a specific coach.
 * It uses the coach's ID to find their team in the database.
 */

public class SquadDAO {

    public static String getSquadNameByCoach(int coachId) {

        String squad = null;

        try (Connection connection = DriverManager.getConnection(
                CommonConstraints.DB_URL,
                CommonConstraints.DB_USER,
                CommonConstraints.DB_PASSWORD)) {

            // Prepared statement to safely insert the coach ID into the query
            PreparedStatement statement = connection.prepareStatement(
                "SELECT team_name FROM team WHERE coach_id = ?"
            );

            // Set the coach ID in the query
            statement.setInt(1, coachId);

            // Run the query and get the result
            ResultSet resultSet = statement.executeQuery();

            // If we got a result, save the team name
            if (resultSet.next()) {
                squad = resultSet.getString("team_name");
            }

        } catch (SQLException e) {
            // Print the error if there's a problem connecting or querying the database
            e.printStackTrace();
        }

        // Return the team name or null if not found
        return squad;
    }

}
