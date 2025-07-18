package io.github.rubywha2.onboarding.gui;
import io.github.rubywha2.onboarding.service.LoginService;

import javax.swing.*;

public class LoginGUI {

    private final LoginService loginService;

    public LoginGUI(LoginService loginService) {
        this.loginService = loginService;
        createWindow(); // call method to build GUI
    }

    private void createWindow(){
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);

        JPanel panel = new JPanel();
        panel.setLayout(null); // Manual layout
        frame.add(panel);
        frame.setVisible(true);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());
            LoginService loginService = new LoginService();

            if (loginService.validateLogin(username, password)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                new HomepageGUI();
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.");
            }
        });

    }
}
