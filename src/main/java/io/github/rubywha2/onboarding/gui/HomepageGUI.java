package io.github.rubywha2.onboarding.gui;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Color;

public class HomepageGUI
{
    // Constructor - starts the GUI creation
    public HomepageGUI() {
        createWindow(); // call method to build GUI
    }

    // Method to build and display the main window
    private void createWindow(){
        JFrame frame = new JFrame("Homepage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.setLayout(null); // Manual layout for full control

        // Sidebar panel on the left
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(200, 200, 200));
        sidebar.setBounds(0, 0, 200, 800); // width: 200px, full height
        sidebar.setLayout(null);
        frame.add(sidebar);

        // Main panel for content and buttons
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(200, 0, 900, 800); // positioned to the right of sidebar
        frame.add(contentPanel);

        // Welcome message label
        JLabel welcomeLabel = new JLabel("Welcome to Staff Manager Homepage");
        welcomeLabel.setBounds(30, 20, 400, 25);
        contentPanel.add(welcomeLabel);

        // Instructions label 1
        JLabel DirectionsLabel = new JLabel("To edit staff personal details, press staff button. To edit and update training courses, press the training button.");
        DirectionsLabel.setBounds(30, 30, 900, 170);
        contentPanel.add(DirectionsLabel);

        // Instructions label 2
        JLabel DirectionsLabel2 = new JLabel("To edit or update job roles, press Job Roles button. To edit a member of Staff's job roles, press Staff Job Roles.");
        DirectionsLabel2.setBounds(30, 45, 900, 170);
        contentPanel.add(DirectionsLabel2);

        // Label to show current time
        JLabel timeLabel = new JLabel();
        timeLabel.setBounds(30, 50, 180, 25);
        contentPanel.add(timeLabel);
        updateTimeLabel(timeLabel); // set the time initially

        // Logout button on sidebar
        JButton logoutButton = new JButton("Log Out");
        logoutButton.setBounds(10, 20, 100, 30);
        sidebar.add(logoutButton);

        // Buttons on the main panel
        JButton staffButton = new JButton("Staff");
        staffButton.setBounds(30, 150, 120, 40);
        contentPanel.add(staffButton);

        JButton trainingButton = new JButton("Training");
        trainingButton.setBounds(170, 150, 120, 40);
        contentPanel.add(trainingButton);

        JButton jobPButton = new JButton("Job Fit");
        jobPButton.setBounds(310, 150, 140, 40);
        contentPanel.add(jobPButton);

        JButton jobRButton = new JButton("Job Roles");
        jobRButton.setBounds(460, 150, 120, 40);
        contentPanel.add(jobRButton);

        // Action listeners for buttons to open respective GUIs and close this one
        staffButton.addActionListener(e -> {
            new StaffManagementGUI();
            frame.dispose(); // close current frame
        });

        jobPButton.addActionListener(e -> {
            new JobFitGUI();
            frame.dispose();
        });

        jobRButton.addActionListener(e -> {
            new JobRoleGUI();
            frame.dispose();
        });

        trainingButton.addActionListener(e -> {
            new TrainingGUI();
            frame.dispose();
        });

        logoutButton.addActionListener(e -> {
            frame.dispose(); // close current frame
            System.out.println("Logged out.");
        });

        // Show the window
        frame.setVisible(true);

    }

    // Helper method to update the time label with current date/time
    private void updateTimeLabel(JLabel label) {
        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        label.setText("Time: " + formatted);
    }
}
