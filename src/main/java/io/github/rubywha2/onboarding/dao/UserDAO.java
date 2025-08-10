package io.github.rubywha2.onboarding.dao;
import io.github.rubywha2.onboarding.Database.DatabaseConnector;
import io.github.rubywha2.onboarding.model.Log;
import io.github.rubywha2.onboarding.model.Users;
import java.sql.*;

public class UserDAO {

    /**
     * Retrieves a user from the database based on the provided username.
     *
     * @param username the username to search for.
     * @return a Users object if a matching user is found; null otherwise.
     */
    public Users getUserByUsername(String username) {
        String query = "SELECT Role, Username, Password FROM LogInCredentials WHERE username = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Users(
                        rs.getString("role"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging later
        }

        return null; // Not found
    }

    /**
     * Records a new login attempt in the LoginLogs table.
     *
     * @param newLog a Log object containing the user, login time, and number of attempts.
     * @return true if the insert was successful; false otherwise.
     */
    public Boolean recordNewLogin(Log newLog) {
        String query = "INSERT INTO LoginLogs (User, Time, Attempts) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set parameters for the insert statement
            stmt.setString(1, newLog.getUser());
            stmt.setString(2, String.valueOf(newLog.getLogInTime()));
            stmt.setInt(3, newLog.getAttempts());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

