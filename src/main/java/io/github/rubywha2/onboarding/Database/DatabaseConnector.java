package io.github.rubywha2.onboarding.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/StaffOnboarding";
    private static final String USER = "ruby";
    private static final String PASSWORD = "happy";

    public static Connection getConnection()  throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
