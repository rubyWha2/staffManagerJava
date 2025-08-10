package io.github.rubywha2.onboarding.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class responsible for establishing and providing
 * connections to the StaffOnboarding MySQL database.
 */
public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/StaffOnboarding";
    private static final String USER = "ruby";
    private static final String PASSWORD = "happy";

    /**
     * Creates and returns a new database connection.
     *
     * @return a Connection object connected to the database.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
