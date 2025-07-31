package io.github.rubywha2.onboarding.gui;
import io.github.rubywha2.onboarding.dao.StaffDAO;
import io.github.rubywha2.onboarding.model.Staff;
import javax.swing.table.DefaultTableModel;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StaffManagementGUI {
    public StaffManagementGUI() {
        createWindow(); // call method to build GUI
    }

    private void createWindow() {
        JFrame frame = new JFrame("Staff Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.setLayout(null); // Manual layout for full control
        frame.setVisible(true);

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

        //Setting Up Column headers JTable
        String[] columnNames = { "Firstname", "Lastname", "Email", "Postcode", "StaffID", "DBS Number", "RoleID" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        StaffDAO dao = new StaffDAO();
        List<Staff> staffList = dao.getStaffData();

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

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 850, 300); // Proper size and location
        contentPanel.add(scrollPane);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(20, 340, 120, 40);
        contentPanel.add(saveButton);

        JButton addButton = new JButton("Add Staff");
        addButton.setBounds(140, 340, 120, 40);
        contentPanel.add(addButton);

        JButton deleteButton = new JButton("Delete Staff");
        deleteButton.setBounds(260, 340, 120, 40);
        contentPanel.add(deleteButton);


        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 120, 40);
        sidebar.add(backButton);

        saveButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow(); //gets staff member
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row to save changes.");
                return;
            }

            String firstname = model.getValueAt(selectedRow, 0).toString();
            String lastname = model.getValueAt(selectedRow, 1).toString();
            String email = model.getValueAt(selectedRow, 2).toString();
            String postcode = model.getValueAt(selectedRow, 3).toString();
            String staffID = model.getValueAt(selectedRow, 4).toString();
            int dbsNumber = Integer.parseInt(model.getValueAt(selectedRow, 5).toString());
            String roleID = model.getValueAt(selectedRow, 6).toString();

            Staff UpdatedStaff = new Staff(firstname, lastname, email, postcode, staffID, dbsNumber, roleID);

            boolean UpdatedStaffQuery = dao.SaveStaff(UpdatedStaff);

            if (UpdatedStaffQuery) {
                JOptionPane.showMessageDialog(frame, "Staff updated successfully.");
            }
            else {
                JOptionPane.showMessageDialog(frame, "Staff update failed.");
            }
        });


        addButton.addActionListener(e -> {
            showAddStaffDialog();
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow(); //gets staff member
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row to save changes.");
                return;
            }

            String staffID = model.getValueAt(selectedRow, 4).toString();
            boolean DeleteStaffQuery = dao.DeleteStaff(staffID);

            if (DeleteStaffQuery) {
                JOptionPane.showMessageDialog(frame, "Staff updated successfully.");
            }
            else {
                JOptionPane.showMessageDialog(frame, "Staff update failed.");
            }
        });
        backButton.addActionListener(e -> {
            new HomepageGUI();
            frame.dispose(); // close current frame
        });
    }

    private void showAddStaffDialog() {
        JTextField firstnameField = new JTextField();
        JTextField lastnameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField postcodeField = new JTextField();
        JTextField dbsField = new JTextField();
        JTextField roleIdField = new JTextField();
        JTextField staffIdField = new JTextField();

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

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Staff", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                Staff newStaff = new Staff(
                        firstnameField.getText(),
                        lastnameField.getText(),
                        emailField.getText(),
                        postcodeField.getText(),
                        staffIdField.getText(),
                        Integer.parseInt(dbsField.getText()),
                        roleIdField.getText()
                );

                StaffDAO dao = new StaffDAO();
                boolean added = dao.AddNewStaff(newStaff);
                if (added) {
                    JOptionPane.showMessageDialog(null, "New staff added successfully!");
                    // Optionally refresh the table here
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add staff.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid DBS number.");
            }
        }


    }
}

