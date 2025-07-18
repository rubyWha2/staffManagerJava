package io.github.rubywha2.onboarding.gui;
import io.github.rubywha2.onboarding.service.LoginService;
import javax.swing.*;

public class HomepageGUI
{
    public HomepageGUI() {
        createWindow(); // call method to build GUI
    }

    private void createWindow(){
        JFrame frame = new JFrame("Homepage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);

        JPanel panel = new JPanel();
        panel.setLayout(null); // Manual layout
        frame.add(panel);
        frame.setVisible(true);


        JButton staffButton = new JButton("Staff");
        staffButton.setBounds(10, 80, 100, 40);
        panel.add(staffButton);

        JButton trainningButton = new JButton("Trainning");
        trainningButton.setBounds(100, 80, 100, 40);
        panel.add(trainningButton);

        JButton jobPButton = new JButton("Job Profiles");
        jobPButton.setBounds(190, 80, 120, 40);
        panel.add(jobPButton);

        JButton jobRButton = new JButton("Job Roles");
        jobRButton.setBounds(300, 80, 100, 40);
        panel.add(jobRButton);

        staffButton.addActionListener(e -> {});
        jobPButton.addActionListener(e -> {});
        jobRButton.addActionListener(e -> {});
        trainningButton.addActionListener(e -> {});
        //need to add a log out button
        //need to add a welcome message and then a side bar with the buttons

    }

}
