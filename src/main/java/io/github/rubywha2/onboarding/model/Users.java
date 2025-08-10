package io.github.rubywha2.onboarding.model;

/**
 * Represents a system user with login credentials and a role.
 */
public class Users {

    // Username of the user
    private String username;

    // Hashed password of the user
    private String password;

    // Role assigned to the user (e.g., Admin, Staff, Manager)
    private String role;

    /**
     * Constructs a new Users object with the given role, username, and password hash.
     *
     * @param role         The role of the user
     * @param username     The username of the user
     * @param passwordHash The hashed password of the user
     */
    public Users(String role, String username, String passwordHash) {
        this.role = role;
        this.username = username;
        this.password = passwordHash;
    }

    /**
     * Gets the user's role.
     *
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role The role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the user's username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username The username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the hashed password for the user.
     *
     * @return password hash
     */
    public String getPasswordHash() {
        return password;
    }

    /**
     * Sets the hashed password for the user.
     *
     * @param passwordHash The hashed password to set
     */
    public void setPasswordHash(String passwordHash) {
        this.password = passwordHash; // fixed bug: previously ignored parameter
    }
}


