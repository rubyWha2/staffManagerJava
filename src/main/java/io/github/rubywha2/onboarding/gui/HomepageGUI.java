package io.github.rubywha2.onboarding.gui;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Color;



public class HomepageGUI
{
    public HomepageGUI() {
        createWindow(); // call method to build GUI
    }

    private void createWindow(){
        JFrame frame = new JFrame("Homepage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.setLayout(null); // Manual layout for full control

        // Sidebar panel on the left
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(200, 200, 200));
        sidebar.setBounds(0, 0, 200, 800); // width: 200px
        sidebar.setLayout(null);
        frame.add(sidebar);

        // Main panel (for content, buttons etc)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(200, 0, 900, 800); // right of sidebar
        frame.add(contentPanel);

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to Staff Manager Homepage");
        welcomeLabel.setBounds(30, 20, 400, 25);
        contentPanel.add(welcomeLabel);

        JLabel DirectionsLabel = new JLabel("To edit staff personal details, press staff button. To edit and update training courses, press the training button. .");
        DirectionsLabel.setBounds(30, 30, 900, 170);
        contentPanel.add(DirectionsLabel);

        JLabel DirectionsLabel2 = new JLabel("To edit or update job roles, press Job Roles button. To edit a member of Staff's job roles, press Staff Job Roles.");
        DirectionsLabel2.setBounds(30, 45, 900, 170);
        contentPanel.add(DirectionsLabel2);

        // Clock label
        JLabel timeLabel = new JLabel();
        timeLabel.setBounds(30, 50, 180, 25);
        contentPanel.add(timeLabel);
        updateTimeLabel(timeLabel);

        // Logout button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.setBounds(10, 20, 100, 30);
        sidebar.add(logoutButton);

        // Buttons (outside sidebar)
        JButton staffButton = new JButton("Staff");
        staffButton.setBounds(30, 150, 120, 40);
        contentPanel.add(staffButton);

        JButton trainingButton = new JButton("Training");
        trainingButton.setBounds(170, 150, 120, 40);
        contentPanel.add(trainingButton);

        JButton jobPButton = new JButton("Job Profiles");
        jobPButton.setBounds(310, 150, 140, 40);
        contentPanel.add(jobPButton);

        JButton jobRButton = new JButton("Job Roles");
        jobRButton.setBounds(460, 150, 120, 40);
        contentPanel.add(jobRButton);


        staffButton.addActionListener(e -> {
            new StaffManagementGUI();
            frame.dispose(); // close current frame
        });

        jobPButton.addActionListener(e -> {
            new JobFitGUI();
            frame.dispose(); // close current frame
        });
        jobRButton.addActionListener(e -> {
            new JobRoleGUI();
            frame.dispose(); // close current frame
        });

        trainingButton.addActionListener(e -> {
            new TrainingGUI();
            frame.dispose(); // close current frame
        });

        logoutButton.addActionListener(e -> {
            frame.dispose(); // close current frame
            System.out.println("Logged out.");
        });

        frame.setVisible(true);

    }
    private void updateTimeLabel(JLabel label) {
        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        label.setText("Time: " + formatted);
    }
}
