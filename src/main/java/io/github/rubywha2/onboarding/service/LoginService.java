package io.github.rubywha2.onboarding.service;
import io.github.rubywha2.onboarding.dao.UserDAO;
import io.github.rubywha2.onboarding.model.Users;
import io.github.rubywha2.onboarding.model.Log;
import io.github.rubywha2.onboarding.util.CSVLogger;
import org.mindrot.jbcrypt.BCrypt;
public class LoginService {

    // Data Access Object for user-related database operations
    private final UserDAO userDAO = new UserDAO();

    /**
     * Validates user login by checking if the username exists and
     * if the provided password matches the stored hashed password.
     *
     * @param username The username entered by the user
     * @param password The plain-text password entered by the user
     * @return true if login is valid, false otherwise
     */
    public boolean validateLogin(String username, String password) {
        // Retrieve user from database by username
        Users user = userDAO.getUserByUsername(username);

        // If no user found, print message and return false
        if (user == null) {
            System.out.println("No user found for username: " + username);
            return false;
        }

        // Get stored hashed password for comparison
        String storedHash = user.getPasswordHash();

        // Use BCrypt to check if password matches stored hash
        boolean result = BCrypt.checkpw(password, storedHash);

        // Return whether passwords matched
        return result;
    }

    /**
     * Records a login attempt in the database and logs it to a CSV file.
     *
     * @param username The username used to attempt login
     * @param date The timestamp of the login attempt (formatted string)
     * @param attempts The number of login attempts made
     * @param status The status of the login attempt (e.g. "SuccessfulLogin", "FailedLogin")
     */
    public void recordLogin(String username, String date, int attempts, String status) {
        // Create a new log entry with username, date, and attempt count
        Log newLog = new Log(username, date, attempts);

        // Attempt to record the log entry in the database
        boolean recordLogIn = userDAO.recordNewLogin(newLog);

        // If successfully recorded in DB, append log entry to CSV file with status
        if (recordLogIn) {
            CSVLogger.append(newLog, status);
        }
    }

}
