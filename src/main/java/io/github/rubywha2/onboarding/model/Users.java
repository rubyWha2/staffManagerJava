package io.github.rubywha2.onboarding.model;

public class Users
{
    private String username;
    private String password;
    private String role;

    public Users() {}

    public Users(int id, String username, String passwordHash) {
        this.role = role;
        this.username = username;
        this.password = passwordHash;
    }
    public String getRole(){return role;}
    public void setRole(String role){this.role = role;}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return password ; }
    public void setPasswordHash(String passwordHash) { password = password; }
}

