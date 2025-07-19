package io.github.rubywha2.onboarding.gui;
import io.github.rubywha2.onboarding.service.LoginService;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Dimension;
import java.awt.Color;
import io.github.rubywha2.onboarding.service.LoginService;



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
//        JFrame frame = new JFrame("Homepage");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1100, 800);
//
//        JPanel panel = new JPanel();
//        panel.setLayout(null); // Manual layout
//        frame.add(panel);
//        frame.setVisible(true);
//
//        JPanel sidebar = new JPanel();
//        sidebar.setPreferredSize(new Dimension(200, 800));
//        sidebar.setBackground(new Color(230, 230, 230));
//        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
//
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(null); // Manual layout still works inside here
//
//
//
//        JButton staffButton = new JButton("Staff");
//        staffButton.setBounds(10, 80, 100, 40);
//        panel.add(staffButton);
//
//        JButton trainningButton = new JButton("Trainning");
//        trainningButton.setBounds(100, 80, 100, 40);
//        panel.add(trainningButton);
//
//        JButton jobPButton = new JButton("Job Profiles");
//        jobPButton.setBounds(190, 80, 120, 40);
//        panel.add(jobPButton);
//
//        JButton jobRButton = new JButton("Job Roles");
//        jobRButton.setBounds(300, 80, 100, 40);
//        panel.add(jobRButton);
//
//        staffButton.addActionListener(e -> {});
//        jobPButton.addActionListener(e -> {});
//        jobRButton.addActionListener(e -> {});
//        trainningButton.addActionListener(e -> {});
//        //need to add a log out button
//        //need to add a welcome message and then a side bar with the buttons
//
//
//
//        // Welcome label
//        JLabel welcomeLabel = new JLabel("Welcome to Staff Manager Homepage");
//        welcomeLabel.setBounds(30, 20, 300, 25);
//        panel.add(welcomeLabel);
//
//        JButton logoutButton = new JButton("Log Out");
//        logoutButton.setBounds(400, 20, 100, 20);
//        panel.add(logoutButton);
//
//        // Clock label
//        JLabel timeLabel = new JLabel();
//        timeLabel.setBounds(30, 50, 180, 25);
//        panel.add(timeLabel);
//        updateTimeLabel(timeLabel);
//
//        // Example action listeners
//        staffButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Staff clicked"));
//        logoutButton.addActionListener(e -> {
//            frame.dispose(); // close current frame
//            System.out.println("Logged out.");
//        });

    }
    private void updateTimeLabel(JLabel label) {
        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        label.setText("Time: " + formatted);
    }
}
