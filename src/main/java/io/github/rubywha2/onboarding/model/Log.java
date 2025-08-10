package io.github.rubywha2.onboarding.model;

/**
 * Represents a login attempt log entry.
 * Stores the username, the time of login attempt, and the number of attempts made.
 */
public class Log {
    // Username of the user attempting to log in
    private String user;

    // Timestamp of the login attempt
    private String logInTime;

    // Number of login attempts made
    private int attempts;

    /**
     * Constructor to initialize a Log entry.
     *
     * @param user      Username of the user
     * @param logInTime Time of the login attempt
     * @param attempts  Number of attempts made
     */
    public Log(String user, String logInTime, int attempts) {
        this.user = user;
        this.logInTime = logInTime;
        this.attempts = attempts;
    }

    /**
     * Gets the username associated with this log.
     *
     * @return Username
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the username.
     *
     * @param user Username to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets the login time.
     *
     * @return Login timestamp
     */
    public String getLogInTime() {
        return logInTime;
    }

    /**
     * Sets the login time.
     *
     * @param logInTime Login timestamp to set
     */
    public void setLogInTime(String logInTime) {
        this.logInTime = logInTime;
    }

    /**
     * Gets the number of login attempts.
     *
     * @return Number of attempts
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * Sets the number of login attempts.
     *
     * @param attempts Number of attempts to set
     */
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    /**
     * Returns a string representation of the Log object.
     *
     * @return String describing the log entry
     */
    @Override
    public String toString() {
        return "Log{" +
                "username='" + user + '\'' +
                ", date='" + logInTime + '\'' +
                ", attempts=" + attempts +
                '}';
    }
}

