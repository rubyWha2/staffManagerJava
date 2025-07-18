package io.github.rubywha2.onboarding.dao;

import io.github.rubywha2.onboarding.Database.DatabaseConnector;
import io.github.rubywha2.onboarding.model.Users;

import java.sql.*;

public class UserDAO {

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
}
