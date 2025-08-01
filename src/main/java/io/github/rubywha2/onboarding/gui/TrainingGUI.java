package io.github.rubywha2.onboarding.gui;

import io.github.rubywha2.onboarding.dao.StaffDAO;
import io.github.rubywha2.onboarding.dao.TrainingDAO;
import io.github.rubywha2.onboarding.model.Staff;
import io.github.rubywha2.onboarding.model.StaffTraining;
import io.github.rubywha2.onboarding.model.Training;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TrainingGUI {
    public TrainingGUI() {
        createWindow(); // call method to build GUI
    }

    private void createWindow() {
        JFrame frame = new JFrame("Training Page");
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

        String[] columnNames = {"Training_Course_Name", "Training_Description","Training_Provider","TrainingID"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        TrainingDAO dao = new TrainingDAO();
        List<Training> trainingList = dao.getAlltrainingCourses();

        for (Training course : trainingList) {
            Object[] row = {
                    course. getTrainingCourseName(),
                    course.getTrainingDescription(),
                    course.getTrainingProvider(),
                    course.getTrainingID()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 850, 200); // Proper size and location
        contentPanel.add(scrollPane);

        //new table for staff training

        String[] staffcolumnNames = {"StaffID","TrainingID","Status"};
        DefaultTableModel staffModel = new DefaultTableModel(staffcolumnNames, 0);


        List<StaffTraining> staffTrainingList = dao.getAllStafftraining();

        for (StaffTraining trainingInstance : staffTrainingList) {
            Object[] row = {
                    trainingInstance.getStaffID(),
                    trainingInstance.getTrainingID(),
                    trainingInstance.getStatus()

            };
            staffModel.addRow(row);
        }

        JTable staffTable = new JTable(staffModel);

        JScrollPane scrollPane2 = new JScrollPane(staffTable);
        scrollPane2.setBounds(20, 250, 850, 200); // Proper size and location
        contentPanel.add(scrollPane2);

        JButton assignButton = new JButton("Assign Training");
        assignButton.setBounds(20, 500, 180, 40);
        contentPanel.add(assignButton);

        JButton completeButton = new JButton("Mark Training As Complete");
        completeButton.setBounds(200, 500, 200, 40);
        contentPanel.add(completeButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 120, 40);
        sidebar.add(backButton);

        backButton.addActionListener(e -> {
            new HomepageGUI();
            frame.dispose(); // close current frame
        });

        completeButton.addActionListener(e -> {
            int selectedRow = staffTable.getSelectedRow(); //gets staff member
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row on the bottom table to save changes.");
                return;
            }

            String staffID = staffModel.getValueAt(selectedRow, 0).toString();
            String trainingID = staffModel.getValueAt(selectedRow, 1).toString();

            boolean CompleteTraining = dao.CompleteTraining(trainingID,staffID);

            if (CompleteTraining) {
                JOptionPane.showMessageDialog(frame, "Training Marked as complete.");
            }
            else {
                JOptionPane.showMessageDialog(frame, "Training Marked as not complete, Update failed.");
            }
        });

        assignButton.addActionListener(e -> {
            showAssignTrainingDialog();
        });
    }

    private void showAssignTrainingDialog() {
        // Replace text fields with combo boxes
        JComboBox<String> staffIDBox = new JComboBox<>();
        JComboBox<Integer> trainingIDBox = new JComboBox<>();
        JComboBox<String> statusBox = new JComboBox<>(new String[]{"Started", "Completed", "Failed"});

        // Load staff IDs from DB
        TrainingDAO dao = new TrainingDAO(); // Or separate DAO for staff/training
        List<String> staffIDs = dao.getAllStaffIDs(); // You need to implement this
        for (String id : staffIDs) {
            staffIDBox.addItem(id);
        }

        // Load training IDs from DB
        List<Integer> trainingIDs = dao.getAllTrainingIDs(); // You need to implement this too
        for (Integer id : trainingIDs) {
            trainingIDBox.addItem(id);
        }

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Select Staff ID:"));
        panel.add(staffIDBox);
        panel.add(new JLabel("Select Training ID:"));
        panel.add(trainingIDBox);
        panel.add(new JLabel("Select Status:"));
        panel.add(statusBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Assign Training", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            StaffTraining newAssignment = new StaffTraining(
                    (String) staffIDBox.getSelectedItem(),
                    (Integer) trainingIDBox.getSelectedItem(),
                    (String) statusBox.getSelectedItem()
            );

            boolean assigned = dao.AssignTraining(newAssignment);
            if (assigned) {
                JOptionPane.showMessageDialog(null, "Training assigned successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to assign training.");
            }
        }
    }

}

