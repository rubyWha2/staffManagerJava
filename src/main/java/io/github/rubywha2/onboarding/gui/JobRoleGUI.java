package io.github.rubywha2.onboarding.gui;

import io.github.rubywha2.onboarding.dao.JobRoleDAO;
import io.github.rubywha2.onboarding.model.JobRoles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class JobRoleGUI {
    public JobRoleGUI() {
        createWindow(); // call method to build GUI
    }

    private void createWindow() {
        JFrame frame = new JFrame("Job Role Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.setLayout(null); // Manual layout for full control
        frame.setVisible(true);

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

        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 120, 40);
        sidebar.add(backButton);

        backButton.addActionListener(e -> {
            new HomepageGUI();
            frame.dispose(); // close current frame
        });

        //Setting Up Column headers JTable
        String[] columnNames = { "RoleID","RateOfPay","Description"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        JobRoleDAO dao = new JobRoleDAO();
        List<JobRoles> rolesList = dao.getAllJobRoles();

        for (JobRoles role : rolesList) {
            Object[] row = {
                    role.getRoleID(),
                    role.getRateOfPay(),
                    role.getDescription()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 850, 175); // Proper size and location
        contentPanel.add(scrollPane);

        JButton saveButton = new JButton("Save Job Role");
        saveButton.setBounds(20, 200, 140, 40);
        contentPanel.add(saveButton);

        JButton addButton = new JButton("Add New Job Role");
        addButton.setBounds(160, 200, 140, 40);
        contentPanel.add(addButton);

        JButton deleteButton = new JButton("Delete Job Role");
        deleteButton.setBounds(290, 200, 140, 40);
        contentPanel.add(deleteButton);

        saveButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow(); //gets staff member
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row to save changes.");
                return;
            }
            String originalRoleID = table.getValueAt(selectedRow, 0).toString();

            String RoleID = model.getValueAt(selectedRow, 0).toString();
            Double RateOfPay = (Double) model.getValueAt(selectedRow, 1);
            String Description = model.getValueAt(selectedRow, 2).toString();


             JobRoles UpdatedRole = new JobRoles(RoleID, RateOfPay, Description);

            boolean UpdatedRoleQuery = dao.SaveJobRole(UpdatedRole,originalRoleID);

            if (UpdatedRoleQuery) {
                JOptionPane.showMessageDialog(frame, "Role has been updated successfully.");
            }
            else {
                JOptionPane.showMessageDialog(frame, "Role update failed.");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow(); //gets staff member
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row to save changes.");
                return;
            }

            String RoleID = model.getValueAt(selectedRow, 0).toString();
            boolean DeleteRoleQuery = dao.DeleteRole(RoleID);

            if (DeleteRoleQuery) {
                JOptionPane.showMessageDialog(frame, "Role updated successfully.");
            }
            else {
                JOptionPane.showMessageDialog(frame, "Role update failed.");
            }
        });

        addButton.addActionListener(e -> {
            showAddRoleDialog();
        });
    }

    private void showAddRoleDialog() {
        JTextField RoleIDField = new JTextField();
        JTextField RateOfPayField = new JTextField();
        JTextField DescField = new JTextField();


        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Role ID:"));
        panel.add(RoleIDField);
        panel.add(new JLabel("Rate of Pay:"));
        panel.add(RateOfPayField);
        panel.add(new JLabel("Description:"));
        panel.add(DescField);


        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Job Role", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                JobRoles newRole = new JobRoles(
                        RoleIDField.getText(),
                        Double.parseDouble(RateOfPayField.getText()),
                        DescField.getText()
                );

                JobRoleDAO dao = new JobRoleDAO();
                boolean added = dao.AddNewRole(newRole);
                if (added) {
                    JOptionPane.showMessageDialog(null, "New job role added successfully!");
                    // Optionally refresh the table here
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add new job role.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid Rate Of Pay.");
            }
        }
    }
}

