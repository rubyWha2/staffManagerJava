package o.github.rubywha2.onboardingtests.dao;

import io.github.rubywha2.onboarding.Database.DatabaseConnector;
import io.github.rubywha2.onboarding.dao.UserDAO;
import io.github.rubywha2.onboarding.model.Log;
import io.github.rubywha2.onboarding.model.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTests {
    private UserDAO dao;
    private final String testUsername = "test_user";
    private final String testPassword = "password123";
    private final String testRole = "Tester";

    @BeforeEach
    void setUp() throws SQLException {
        dao = new UserDAO();

        // Insert test user into LogInCredentials
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO LogInCredentials (Role, Username, Password) VALUES (?, ?, ?)")) {

            stmt.setString(1, testRole);
            stmt.setString(2, testUsername);
            stmt.setString(3, testPassword);
            stmt.executeUpdate();
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Remove test login logs
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM LoginLogs WHERE User = ?")) {
            stmt.setString(1, testUsername);
            stmt.executeUpdate();
        }

        // Remove test user
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM LogInCredentials WHERE Username = ?")) {
            stmt.setString(1, testUsername);
            stmt.executeUpdate();
        }
    }

    @Test
    void testGetUserByUsername_Found() {
        Users user = dao.getUserByUsername(testUsername);
        assertNotNull(user, "User should be found");
        assertEquals(testRole, user.getRole());
        assertEquals(testUsername, user.getUsername());
        assertEquals(testPassword, user.getPasswordHash());

    }
    @Test
    void testGetUserByUsername_NotFound() {
        Users user = dao.getUserByUsername("nonexistent_user");
        assertNull(user, "User should not be found for a non-existent username");
    }

    @Test
    void testRecordNewLogin() {
        Log log = new Log(testUsername, LocalDateTime.now().toString(), 1);
        boolean success = dao.recordNewLogin(log);
        assertTrue(success, "Log should be recorded successfully");
    }

}
