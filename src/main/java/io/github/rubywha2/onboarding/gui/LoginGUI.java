package io.github.rubywha2.onboarding.gui;
import io.github.rubywha2.onboarding.service.LoginService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public record LoginGUI(LoginService loginService) {

    /**
     * Constructor - initializes loginService and creates the login window
     */
    public LoginGUI(LoginService loginService) {
        this.loginService = loginService;
        createWindow(); // build GUI components
    }

    /**
     * Creates and shows the login window with all UI components
     */
    private void createWindow() {
        JFrame frame = new JFrame("Login"); // main window title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);

        JPanel panel = new JPanel();
        panel.setLayout(null); // manual layout for precise positioning
        frame.add(panel);
        frame.setVisible(true);

        // Username label and text field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        /*
         * Action listener for login button click:
         * - get username and password input
         * - validate credentials using LoginService
         * - show success or failure message
         * - record login attempt with timestamp and result
         * - open HomepageGUI on success, close login window
         */
        loginButton.addActionListener(e -> {
            LocalDateTime now = LocalDateTime.now();
            String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            int attempts = 1;  // initial attempt count (consider tracking externally for multiple attempts)

            String username = userText.getText();
            String password = new String(passwordText.getPassword());

            // Note: You already have loginService from record, no need to instantiate again
            if (loginService.validateLogin(username, password)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                new HomepageGUI();  // open homepage on successful login
                frame.dispose();    // close login window

                loginService.recordLogin(username, formatted, attempts, "SuccessfulLogin");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                attempts++;  // increment attempts (note: this resets each click here)

                loginService.recordLogin(username, formatted, attempts, "FailedLogin");
            }
        });

    }
}
