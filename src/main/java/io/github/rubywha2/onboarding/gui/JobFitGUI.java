package io.github.rubywha2.onboarding.gui;

import io.github.rubywha2.onboarding.dao.JobFitDAO;
import io.github.rubywha2.onboarding.model.JobRoleTraining;
import io.github.rubywha2.onboarding.model.StaffTraining;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JobFitGUI {
    private JFrame frame;
    private JTable table;

    // Constructor - starts GUI creation
    public JobFitGUI() {
        frame = new JFrame("Job Profile Page"); // initialize frame
        createWindow(); // build GUI window
    }

    // Build and display main window
    private void createWindow() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.setLayout(null); // manual layout for control

        // Sidebar panel on left
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(200, 200, 200));
        sidebar.setBounds(0, 0, 200, 800); // fixed width
        sidebar.setLayout(null);
        frame.add(sidebar);

        // Main content panel on right
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(200, 0, 900, 800);
        frame.add(contentPanel);

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Job Fit Page");
        welcomeLabel.setBounds(20, 20, 400, 25);
        contentPanel.add(welcomeLabel);


        // Back button in sidebar
        JButton backButton = new JButton("Back");
        backButton.setName("Back");
        backButton.setBounds(10, 20, 100, 30);
        sidebar.add(backButton);

        // Table columns for job roles and trainings
        String[] columnNames = {"RoleID", "TrainingID"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Load job roles and trainings from DAO
        JobFitDAO dao = new JobFitDAO();
        List<JobRoleTraining> AllJobRolesTrainingInstancesList = dao.getAllJobRolesTrainingInstances();

        // Add each job role training record to table model
        for (JobRoleTraining JRTIrecord : AllJobRolesTrainingInstancesList) {
            Object[] row = {
                    JRTIrecord.getRoleID(),
                    JRTIrecord.getTrainingID()
            };
            model.addRow(row);
        }

        // Initialises table and creates add to scroll pane
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 40, 850, 200); // position and size
        contentPanel.add(scrollPane);

        // Back button closes current window and opens homepage
        backButton.addActionListener(e -> {
            new HomepageGUI();
            frame.dispose(); // close this window
        });

        frame.setVisible(true);

        // Button to check staff eligibility for new job role
        JButton assignButton = new JButton("Check Staff Members Eligibility For New Job role");
        assignButton.setName("Assign");
        assignButton.setBounds(20, 280, 700, 40);
        contentPanel.add(assignButton);

        // Show dialog on button click
        assignButton.addActionListener(e -> {
            showAssignJobRoleDialog();
        });
    }

    // Dialog to select staff and role, then check training eligibility
    private void showAssignJobRoleDialog() {
        JobFitDAO dao = new JobFitDAO();

        // Dropdowns for staff IDs and role IDs
        JComboBox<String> staffIDBox = new JComboBox<>();
        JComboBox<String> roleIDBox = new JComboBox<>();

        // Load staff IDs into dropdown
        List<String> staffIDs = dao.getAllStaffIDs();
        for (String id : staffIDs) {
            staffIDBox.addItem(id);
        }

        // Load job role IDs into dropdown
        List<String> roleIDs = dao.getAllJobRoles();
        for (String id : roleIDs) {
            roleIDBox.addItem(id);
        }

        // Layout panel for dialog
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Select Staff ID:"));
        panel.add(staffIDBox);
        panel.add(new JLabel("Select Role ID:"));
        panel.add(roleIDBox);

        // Show dialog and wait for user input
        int result = JOptionPane.showConfirmDialog(null, panel, "Check Training Match",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String selectedStaffID = (String) staffIDBox.getSelectedItem();
            String selectedRoleID = (String) roleIDBox.getSelectedItem();

            // Validate selection
            if (selectedStaffID == null || selectedRoleID == null) {
                JOptionPane.showMessageDialog(null, "Please select both Staff ID and Role ID.");
                return;
            }

            // Get staff's completed trainings
            List<StaffTraining> staffTrainings = dao.getAllStaffsTrainings(selectedStaffID);
            List<String> completedTrainings = new ArrayList<>();
            for (StaffTraining t : staffTrainings) {
                if ("Completed".equalsIgnoreCase(t.getStatus())) {
                    completedTrainings.add(String.valueOf(t.getTrainingID()));
                }
            }

            // Get required trainings for selected role
            List<String> requiredTrainings = dao.getRequiredTrainingForRole(selectedRoleID);

            // Check for missing trainings
            List<String> missingTrainings = new ArrayList<>();
            for (String required : requiredTrainings) {
                if (!completedTrainings.contains(required)) {
                    missingTrainings.add(required);
                }
            }

            // Show result to user
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

    // ===== Getters for testing =====
    public JFrame getFrame() {
        return frame;
    }

    public JTable getTable() {
        return table;
    }

}
