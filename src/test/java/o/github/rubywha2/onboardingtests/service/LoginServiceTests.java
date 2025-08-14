package o.github.rubywha2.onboardingtests.service;

import io.github.rubywha2.onboarding.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginServiceTests {

    private static final String USER_CSV_PATH = "users.csv"; // Adjust if your DAO uses a different path

    @BeforeEach
    void setUp() throws IOException {
        // Create a test user directly in the CSV file before each test
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_CSV_PATH))) {
            String hashedPassword = BCrypt.hashpw("testpass", BCrypt.gensalt());
            // Assuming your CSV format is: username,passwordHash,role
            writer.write("testuser," + hashedPassword + ",Admin");
            writer.newLine();
        }
    }

    @Test
    void testValidateLogin_Success() {
        LoginService loginService = new LoginService();
        boolean result = loginService.validateLogin("testuser", "testpass");
        assertTrue(result, "Login should succeed for correct credentials");
    }

    @Test
    void testValidateLogin_Failure_WrongPassword() {
        LoginService loginService = new LoginService();
        boolean result = loginService.validateLogin("testuser", "wrongpass");
        assertFalse(result, "Login should fail for incorrect password");
    }

    @Test
    void testValidateLogin_Failure_NoUser() {
        LoginService loginService = new LoginService();
        boolean result = loginService.validateLogin("nonexistent", "testpass");
        assertFalse(result, "Login should fail for non-existent user");
    }
}
