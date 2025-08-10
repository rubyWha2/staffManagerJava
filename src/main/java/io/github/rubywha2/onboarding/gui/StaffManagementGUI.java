package io.github.rubywha2.onboarding.gui;
import io.github.rubywha2.onboarding.dao.StaffDAO;
import io.github.rubywha2.onboarding.model.Staff;
import javax.swing.table.DefaultTableModel;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StaffManagementGUI {

    /**
     * Constructor to initialize and display the Staff Management GUI.
     */
    public StaffManagementGUI() {
        createWindow(); // call method to build GUI components
    }

    /**
     * Creates and configures the main window and GUI components.
     */
    private void createWindow() {
        JFrame frame = new JFrame("Staff Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.setLayout(null); // Using manual layout for full control over components
        frame.setVisible(true);

        // Sidebar panel on the left side for navigation or options
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(200, 200, 200)); // light grey background
        sidebar.setBounds(0, 0, 200, 800); // fixed width 200px, full height
        sidebar.setLayout(null); // manual layout
        frame.add(sidebar);

        // Main content panel to the right of sidebar for displaying staff data and controls
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(200, 0, 900, 800); // positioned next to sidebar
        frame.add(contentPanel);

        /*
         * Define table columns for displaying staff data.
         * Columns: Firstname, Lastname, Email, Postcode, StaffID, DBS Number, RoleID
         */
        String[] columnNames = { "Firstname", "Lastname", "Email", "Postcode", "StaffID", "DBS Number", "RoleID" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Retrieve staff data from database via DAO
        StaffDAO dao = new StaffDAO();
        List<Staff> staffList = dao.getStaffData();

        // Populate table model with staff data
        for (Staff staff : staffList) {
            Object[] row = {
                    staff.getFirstname(),
                    staff.getLastname(),
                    staff.getEmail(),
                    staff.getPostcode(),
                    staff.getStaffID(),
                    staff.getDBSnumber(),
                    staff.getRoleID()
            };
            model.addRow(row);
        }

        // Create JTable with the populated model
        JTable table = new JTable(model);

        // Add scrolling capability to the table and set its size & position
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 850, 300);
        contentPanel.add(scrollPane);

        // Create buttons for Save, Add, Delete actions
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(20, 340, 120, 40);
        contentPanel.add(saveButton);

        JButton addButton = new JButton("Add Staff");
        addButton.setBounds(140, 340, 120, 40);
        contentPanel.add(addButton);

        JButton deleteButton = new JButton("Delete Staff");
        deleteButton.setBounds(260, 340, 120, 40);
        contentPanel.add(deleteButton);

        // Back button added to sidebar for navigation back to homepage
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 120, 40);
        sidebar.add(backButton);

        /*
         * Save button logic:
         * - Check if a row is selected
         * - Retrieve updated staff info from selected row
         * - Create Staff object and call DAO to save changes
         * - Notify user of success/failure
         */
        saveButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow(); // get selected row index
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row to save changes.");
                return; // no selection, exit handler
            }

            // Extract updated data from table model
            String firstname = model.getValueAt(selectedRow, 0).toString();
            String lastname = model.getValueAt(selectedRow, 1).toString();
            String email = model.getValueAt(selectedRow, 2).toString();
            String postcode = model.getValueAt(selectedRow, 3).toString();
            String staffID = model.getValueAt(selectedRow, 4).toString();
            int dbsNumber = Integer.parseInt(model.getValueAt(selectedRow, 5).toString());
            String roleID = model.getValueAt(selectedRow, 6).toString();

            // Create Staff object with updated data
            Staff UpdatedStaff = new Staff(firstname, lastname, email, postcode, staffID, dbsNumber, roleID);

            // Call DAO method to save updated staff info
            boolean UpdatedStaffQuery = dao.SaveStaff(UpdatedStaff);

            // Show message based on operation result
            if (UpdatedStaffQuery) {
                JOptionPane.showMessageDialog(frame, "Staff updated successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Staff update failed.");
            }
        });

        // Add button opens a dialog to add new staff
        addButton.addActionListener(e -> {
            showAddStaffDialog();
        });

        /*
         * Delete button logic:
         * - Check if a row is selected
         * - Retrieve StaffID from selected row
         * - Call DAO to delete staff by StaffID
         * - Notify user of success/failure
         */
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
                return;
            }

            String staffID = model.getValueAt(selectedRow, 4).toString();
            boolean DeleteStaffQuery = dao.DeleteStaff(staffID);

            if (DeleteStaffQuery) {
                JOptionPane.showMessageDialog(frame, "Staff deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Staff deletion failed.");
            }
        });

        // Back button logic to return to homepage and close current window
        backButton.addActionListener(e -> {
            new HomepageGUI();
            frame.dispose(); // close current frame
        });
    }

    /**
     * Displays a dialog to input new staff information and adds it to database.
     */
    private void showAddStaffDialog() {
        // Create text fields for new staff input
        JTextField firstnameField = new JTextField();
        JTextField lastnameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField postcodeField = new JTextField();
        JTextField dbsField = new JTextField();
        JTextField roleIdField = new JTextField();
        JTextField staffIdField = new JTextField();

        // Use a panel with grid layout for neat arrangement of input fields and labels
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Firstname:"));
        panel.add(firstnameField);
        panel.add(new JLabel("Lastname:"));
        panel.add(lastnameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Postcode:"));
        panel.add(postcodeField);
        panel.add(new JLabel("DBS Number:"));
        panel.add(dbsField);
        panel.add(new JLabel("Role ID:"));
        panel.add(roleIdField);
        panel.add(new JLabel("Staff ID:"));
        panel.add(staffIdField);

        // Show confirm dialog with OK and Cancel options
        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Staff", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // If OK clicked, validate and add new staff
        if (result == JOptionPane.OK_OPTION) {
            try {
                // Create new Staff object from input fields
                Staff newStaff = new Staff(
                        firstnameField.getText(),
                        lastnameField.getText(),
                        emailField.getText(),
                        postcodeField.getText(),
                        staffIdField.getText(),
                        Integer.parseInt(dbsField.getText()), // parse DBS number as integer
                        roleIdField.getText()
                );

                // Use DAO to add new staff to database
                StaffDAO dao = new StaffDAO();
                boolean added = dao.AddNewStaff(newStaff);

                if (added) {
                    JOptionPane.showMessageDialog(null, "New staff added successfully!");
                    // Optionally, refresh the staff table here
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add staff.");
                }
            } catch (NumberFormatException ex) {
                // Show error if DBS number is not a valid integer
                JOptionPane.showMessageDialog(null, "Invalid DBS number.");
            }
        }
    }
}

