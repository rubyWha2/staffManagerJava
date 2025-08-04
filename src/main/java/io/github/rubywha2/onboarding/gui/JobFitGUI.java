package io.github.rubywha2.onboarding.gui;

import io.github.rubywha2.onboarding.dao.JobFitDAO;
import io.github.rubywha2.onboarding.dao.TrainingDAO;
import io.github.rubywha2.onboarding.model.JobRoleTraining;
import io.github.rubywha2.onboarding.model.Training;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class JobFitGUI {
    public JobFitGUI() {
        createWindow(); // call method to build GUI
    }

    private void createWindow() {
        JFrame frame = new JFrame("Job Profile Page");
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
        JLabel welcomeLabel = new JLabel("Welcome to the Job Fit Page");
        welcomeLabel.setBounds(20, 20, 400, 25);
        contentPanel.add(welcomeLabel);

        JLabel instructionsLabel = new JLabel("Welcome to the Job Fit Page");
        instructionsLabel.setBounds(20, 20, 400, 25);
        contentPanel.add(instructionsLabel);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 20, 100, 30);
        sidebar.add(backButton);


        String[] columnNames = {"RoleID","TrainingID"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        JobFitDAO dao = new JobFitDAO();
        List<JobRoleTraining> AllJobRolesTrainingInstancesList = dao.getAllJobRolesTrainingInstances();

        for (JobRoleTraining JRTIrecord : AllJobRolesTrainingInstancesList) {
            Object[] row = {
                    JRTIrecord. getRoleID(),
                    JRTIrecord.getTrainingID()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 40, 850, 200); // Proper size and location
        contentPanel.add(scrollPane);

        backButton.addActionListener(e -> {
            new HomepageGUI();
            frame.dispose(); // close current frame

        });

        frame.setVisible(true);

    }
}