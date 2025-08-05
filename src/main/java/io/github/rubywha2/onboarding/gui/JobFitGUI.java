package io.github.rubywha2.onboarding.gui;

import io.github.rubywha2.onboarding.dao.JobFitDAO;
import io.github.rubywha2.onboarding.dao.TrainingDAO;
import io.github.rubywha2.onboarding.model.JobRoleTraining;
import io.github.rubywha2.onboarding.model.StaffTraining;
import io.github.rubywha2.onboarding.model.Training;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
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

        JButton assignButton = new JButton("Check Staff Members Eligibility For New Job role");
        assignButton.setBounds(20, 280, 700, 40);
        contentPanel.add(assignButton);

        assignButton.addActionListener(e -> {
            showAssignJobRoleDialog();
        });
    }

    private void showAssignJobRoleDialog() {
        JobFitDAO dao = new JobFitDAO();

        JComboBox<String> staffIDBox = new JComboBox<>();
        JComboBox<String> roleIDBox = new JComboBox<>();

        // Load staff IDs
        List<String> staffIDs = dao.getAllStaffIDs();
        for (String id : staffIDs) {
            staffIDBox.addItem(id);
        }

        // Load job roles
        List<String> roleIDs = dao.getAllJobRoles();
        for (String id : roleIDs) {
            roleIDBox.addItem(id);
        }

        // Layout panel
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Select Staff ID:"));
        panel.add(staffIDBox);
        panel.add(new JLabel("Select Role ID:"));
        panel.add(roleIDBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Check Training Match",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String selectedStaffID = (String) staffIDBox.getSelectedItem();
            String selectedRoleID = (String) roleIDBox.getSelectedItem();

            if (selectedStaffID == null || selectedRoleID == null) {
                JOptionPane.showMessageDialog(null, "Please select both Staff ID and Role ID.");
                return;
            }

            // Load completed training
            List<StaffTraining> staffTrainings = dao.getAllStaffsTrainings(selectedStaffID);
            List<String> completedTrainings = new ArrayList<>();
            for (StaffTraining t : staffTrainings) {
                if ("Completed".equalsIgnoreCase(t.getStatus())) {
                    completedTrainings.add(String.valueOf(t.getTrainingID()));
                }
            }

            // Load required training
            List<String> requiredTrainings = dao.getRequiredTrainingForRole(selectedRoleID);

            // Check if staff has completed all required trainings
            List<String> missingTrainings = new ArrayList<>();
            for (String required : requiredTrainings) {
                if (!completedTrainings.contains(required)) {
                    missingTrainings.add(required);
                }
            }

            if (missingTrainings.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Staff member IS fully trained for this role!",
                        "Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Staff member is MISSING training(s): " + String.join(", ", missingTrainings),
                        "Result", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}