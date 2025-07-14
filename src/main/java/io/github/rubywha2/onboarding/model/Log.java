package io.github.rubywha2.onboarding.model;
import java.time.LocalDateTime; // Import the LocalDateTime class


public class Log {
    private String user;
    private LocalDateTime logInTime;
    private int attempts;

    public Log(String user, LocalDateTime logInTime, int attempts) {
        this.user = user;
        this.logInTime = logInTime;
        this.attempts = attempts;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public LocalDateTime getLogInTime() {
        return logInTime;
    }
    public void setLogInTime(LocalDateTime logInTime) {
        this.logInTime = logInTime;
    }
    public int getAttempts() {
        return attempts;
    }
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

}

