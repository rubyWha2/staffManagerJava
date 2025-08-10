package io.github.rubywha2.onboarding.gui;

import io.github.rubywha2.onboarding.dao.JobRoleDAO;
import io.github.rubywha2.onboarding.model.JobRoles;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class JobRoleGUI {
    // Constructor - starts GUI creation
    public JobRoleGUI() {
        createWindow(); // call method to build GUI
    }

    /**
     * Create the main window and initialize all components
     */
    private void createWindow() {
        JFrame frame = new JFrame("Job Role Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.setLayout(null); // Manual layout for full control
        frame.setVisible(true); // Make window visible

        // Sidebar panel on the left
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(200, 200, 200));
        sidebar.setBounds(0, 0, 200, 800); // fixed width and height
        sidebar.setLayout(null);
        frame.add(sidebar);

        // Main content panel (right side)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(200, 0, 900, 800); // beside sidebar
        frame.add(contentPanel);

        // Back button in sidebar to return to homepage
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 120, 40);
        sidebar.add(backButton);

        // Back button action: open HomepageGUI and close this window
        backButton.addActionListener(e -> {
            new HomepageGUI();
            frame.dispose(); // close current frame
        });

        // Define column headers for the job roles table
        String[] columnNames = { "RoleID","RateOfPay","Description"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Load job roles data from DAO
        JobRoleDAO dao = new JobRoleDAO();
        List<JobRoles> rolesList = dao.getAllJobRoles();

        // Add each job role to the table model
        for (JobRoles role : rolesList) {
            Object[] row = {
                    role.getRoleID(),
                    role.getRateOfPay(),
                    role.getDescription()
            };
            model.addRow(row);
        }

        // Create table to display job roles
        JTable table = new JTable(model);

        // Add table to scroll pane for better UI
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 850, 175); // size and position
        contentPanel.add(scrollPane);

        // Buttons for save, add, and delete job roles
        JButton saveButton = new JButton("Save Job Role");
        saveButton.setBounds(20, 200, 140, 40);
        contentPanel.add(saveButton);

        JButton addButton = new JButton("Add New Job Role");
        addButton.setBounds(160, 200, 140, 40);
        contentPanel.add(addButton);

        JButton deleteButton = new JButton("Delete Job Role");
        deleteButton.setBounds(290, 200, 140, 40);
        contentPanel.add(deleteButton);

        // Save changes to selected job role
        saveButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow(); // get selected row index
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row to save changes.");
                return; // no row selected, exit method
            }
            String originalRoleID = table.getValueAt(selectedRow, 0).toString();

            // Get updated data from the table model
            String RoleID = model.getValueAt(selectedRow, 0).toString();
            Double RateOfPay = (Double) model.getValueAt(selectedRow, 1);
            String Description = model.getValueAt(selectedRow, 2).toString();

            // Create updated JobRoles object
            JobRoles UpdatedRole = new JobRoles(RoleID, RateOfPay, Description);

            // Save updated role in database via DAO
            boolean UpdatedRoleQuery = dao.SaveJobRole(UpdatedRole, originalRoleID);

            // Notify user of success or failure
            if (UpdatedRoleQuery) {
                JOptionPane.showMessageDialog(frame, "Role has been updated successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Role update failed.");
            }
        });

        // Delete selected job role from database
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow(); // get selected row index
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row to save changes.");
                return; // no row selected, exit method
            }

            // Get RoleID to delete
            String RoleID = model.getValueAt(selectedRow, 0).toString();

            // Delete role via DAO
            boolean DeleteRoleQuery = dao.DeleteRole(RoleID);

            // Notify user of result
            if (DeleteRoleQuery) {
                JOptionPane.showMessageDialog(frame, "Role updated successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Role update failed.");
            }
        });

        // Open dialog to add a new job role
        addButton.addActionListener(e -> {
            showAddRoleDialog();
        });
    }

    /**
     * Show dialog to add a new job role with fields for RoleID, RateOfPay, and Description
     */
    private void showAddRoleDialog() {
        JTextField RoleIDField = new JTextField();
        JTextField RateOfPayField = new JTextField();
        JTextField DescField = new JTextField();

        // Create panel with form fields
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Role ID:"));
        panel.add(RoleIDField);
        panel.add(new JLabel("Rate of Pay:"));
        panel.add(RateOfPayField);
        panel.add(new JLabel("Description:"));
        panel.add(DescField);

        // Show confirmation dialog
        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Job Role", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                // Create new JobRoles object from input
                JobRoles newRole = new JobRoles(
                        RoleIDField.getText(),
                        Double.parseDouble(RateOfPayField.getText()),
                        DescField.getText()
                );

                // Save new role in database
                JobRoleDAO dao = new JobRoleDAO();
                boolean added = dao.AddNewRole(newRole);

                // Notify user of success or failure
                if (added) {
                    JOptionPane.showMessageDialog(null, "New job role added successfully!");
                    // Optionally refresh the table here
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add new job role.");
                }
            } catch (NumberFormatException ex) {
                // Handle invalid RateOfPay input
                JOptionPane.showMessageDialog(null, "Invalid Rate Of Pay.");
            }
        }
    }
}


