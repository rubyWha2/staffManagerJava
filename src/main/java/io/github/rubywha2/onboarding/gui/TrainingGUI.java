package io.github.rubywha2.onboarding.gui;

import io.github.rubywha2.onboarding.dao.TrainingDAO;
import io.github.rubywha2.onboarding.model.StaffTraining;
import io.github.rubywha2.onboarding.model.Training;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class TrainingGUI {

    /**
     * Constructor to initialize and display the Training GUI.
     */
    public TrainingGUI() {
        createWindow(); // call method to build GUI components
    }

    /**
     * Creates and configures the main window and GUI components.
     */
    private void createWindow() {
        JFrame frame = new JFrame("Training Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.setLayout(null); // Using manual layout for precise control
        frame.setVisible(true);

        // Sidebar panel on the left for navigation or other options
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(200, 200, 200)); // light grey background
        sidebar.setBounds(0, 0, 200, 800); // fixed width 200px, full height
        sidebar.setLayout(null); // manual layout
        frame.add(sidebar);

        // Main content panel to the right of sidebar for displaying training data and controls
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(200, 0, 900, 800); // positioned next to sidebar
        frame.add(contentPanel);

        /*
         * Define table columns for training courses.
         * Columns: Training Course Name, Description, Provider, TrainingID
         */
        String[] columnNames = {"Training_Course_Name", "Training_Description", "Training_Provider", "TrainingID"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Create DAO to access training data
        TrainingDAO dao = new TrainingDAO();
        List<Training> trainingList = dao.getAlltrainingCourses();

        // Populate table model with training courses
        for (Training course : trainingList) {
            Object[] row = {
                    course.getTrainingCourseName(),
                    course.getTrainingDescription(),
                    course.getTrainingProvider(),
                    course.getTrainingID()
            };
            model.addRow(row);
        }

        // Create JTable for training courses with the model
        JTable table = new JTable(model);

        // Add scroll pane for the training courses table and set size & position
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 850, 200);
        contentPanel.add(scrollPane);

        /*
         * Create a second table to show staff training assignments
         * Columns: StaffID, TrainingID, Status
         */
        String[] staffcolumnNames = {"StaffID", "TrainingID", "Status"};
        DefaultTableModel staffModel = new DefaultTableModel(staffcolumnNames, 0);

        // Retrieve all staff training assignments
        List<StaffTraining> staffTrainingList = dao.getAllStafftraining();

        // Populate the staff training table model
        for (StaffTraining trainingInstance : staffTrainingList) {
            Object[] row = {
                    trainingInstance.getStaffID(),
                    trainingInstance.getTrainingID(),
                    trainingInstance.getStatus()
            };
            staffModel.addRow(row);
        }

        // Create JTable for staff training assignments
        JTable staffTable = new JTable(staffModel);

        // Add scroll pane for the staff training table and set size & position
        JScrollPane scrollPane2 = new JScrollPane(staffTable);
        scrollPane2.setBounds(20, 250, 850, 200);
        contentPanel.add(scrollPane2);

        // Button to assign new training to staff
        JButton assignButton = new JButton("Assign Training");
        assignButton.setBounds(20, 500, 180, 40);
        contentPanel.add(assignButton);

        // Button to mark training as complete
        JButton completeButton = new JButton("Mark Training As Complete");
        completeButton.setBounds(200, 500, 200, 40);
        contentPanel.add(completeButton);

        // Back button in sidebar to navigate back to homepage
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 120, 40);
        sidebar.add(backButton);

        // Back button action listener: opens homepage and closes this frame
        backButton.addActionListener(e -> {
            new HomepageGUI();
            frame.dispose(); // close current frame
        });

        /*
         * Complete button action listener:
         * - Gets selected row from staff training table
         * - Marks the selected training as complete using DAO
         * - Shows success or failure message
         */
        completeButton.addActionListener(e -> {
            int selectedRow = staffTable.getSelectedRow(); // get selected staff training row
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a row on the bottom table to save changes.");
                return; // exit if no row selected
            }

            // Retrieve StaffID and TrainingID from selected row
            String staffID = staffModel.getValueAt(selectedRow, 0).toString();
            String trainingID = staffModel.getValueAt(selectedRow, 1).toString();

            // Call DAO to mark training as complete
            boolean CompleteTraining = dao.CompleteTraining(trainingID, staffID);

            // Display result to user
            if (CompleteTraining) {
                JOptionPane.showMessageDialog(frame, "Training Marked as complete.");
            } else {
                JOptionPane.showMessageDialog(frame, "Training Marked as not complete, Update failed.");
            }
        });

        // Assign button action listener: shows dialog to assign training to staff
        assignButton.addActionListener(e -> {
            showAssignTrainingDialog();
        });
    }

    /**
     * Shows a dialog for assigning a training course to a staff member.
     * Uses combo boxes populated from the database to select StaffID, TrainingID, and Status.
     */
    private void showAssignTrainingDialog() {
        // Combo boxes for selecting Staff ID, Training ID, and status
        JComboBox<String> staffIDBox = new JComboBox<>();
        JComboBox<Integer> trainingIDBox = new JComboBox<>();
        JComboBox<String> statusBox = new JComboBox<>(new String[]{"Started", "Completed", "Failed"});

        // DAO instance to fetch data
        TrainingDAO dao = new TrainingDAO();

        // Load all Staff IDs from the database and add to combo box
        List<String> staffIDs = dao.getAllStaffIDs(); // Implement this method in DAO
        for (String id : staffIDs) {
            staffIDBox.addItem(id);
        }

        // Load all Training IDs from the database and add to combo box
        List<Integer> trainingIDs = dao.getAllTrainingIDs(); // Implement this method in DAO
        for (Integer id : trainingIDs) {
            trainingIDBox.addItem(id);
        }

        // Create a panel with GridLayout to arrange components vertically
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Select Staff ID:"));
        panel.add(staffIDBox);
        panel.add(new JLabel("Select Training ID:"));
        panel.add(trainingIDBox);
        panel.add(new JLabel("Select Status:"));
        panel.add(statusBox);

        // Show confirm dialog with OK and Cancel options
        int result = JOptionPane.showConfirmDialog(null, panel, "Assign Training", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Create new StaffTraining assignment from user selections
            StaffTraining newAssignment = new StaffTraining(
                    (String) staffIDBox.getSelectedItem(),
                    (Integer) trainingIDBox.getSelectedItem(),
                    (String) statusBox.getSelectedItem()
            );

            // Assign training using DAO method
            boolean assigned = dao.AssignTraining(newAssignment);

            // Show success or failure message
            if (assigned) {
                JOptionPane.showMessageDialog(null, "Training assigned successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to assign training.");
            }
        }
    }
}

