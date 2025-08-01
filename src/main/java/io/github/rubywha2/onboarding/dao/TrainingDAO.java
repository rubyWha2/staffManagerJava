package io.github.rubywha2.onboarding.dao;

import io.github.rubywha2.onboarding.Database.DatabaseConnector;
import io.github.rubywha2.onboarding.model.JobRoles;
import io.github.rubywha2.onboarding.model.StaffTraining;
import io.github.rubywha2.onboarding.model.Training;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainingDAO {

    public List<Training> getAlltrainingCourses() {
        List<Training> TrainningList = new ArrayList<>();

        String query = "SELECT Training_Course_Name, Training_Description,Training_Provider,TrainingID  FROM Training";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Training trainingCourse = new Training(
                        rs.getString("Training_Course_Name"),
                        rs.getString("Training_Description"),
                        rs.getString("Training_Provider"),
                        rs.getInt("TrainingID")
                );
                TrainningList.add(trainingCourse);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging later
        }

        return TrainningList;
    }

    public List<StaffTraining> getAllStafftraining() {
        List<StaffTraining> staffTrainningList = new ArrayList<>();

        String query = "SELECT StaffID,TrainingID,Status  FROM Staff_Training";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                StaffTraining staffTrainingInstance = new StaffTraining(
                        rs.getString("StaffID"),
                        rs.getInt("TrainingID"),
                        rs.getString("Status")
                );
                staffTrainningList.add(staffTrainingInstance);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging later
        }

        return staffTrainningList;
    }

    public List<Integer> getAllTrainingIDs() {
        List<Integer> trainingIDs = new ArrayList<>();

        List<Training> trainingList = getAlltrainingCourses(); // Assuming this returns your List<Training>
        for (Training course : trainingList) {
            trainingIDs.add(course.getTrainingID());
        }

        return trainingIDs;
    }

    public List<String> getAllStaffIDs() {
        List<String> staffIDs = new ArrayList<>();

        String query = "SELECT StaffID FROM Staff";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                staffIDs.add(rs.getString("StaffID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffIDs;
    }

    public Boolean CompleteTraining(String TrainingID, String StaffID) {
        String query = "UPDATE Staff_Training SET Status = ? WHERE TrainingID = ? AND StaffID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "Completed");
            stmt.setString(2, TrainingID);
            stmt.setString(3, StaffID);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean AssignTraining(StaffTraining training) {
        String query = "INSERT INTO Staff_Training (StaffID, TrainingID, Status) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            //sets 1st question mark into Firstname
            stmt.setString(1, training.getStaffID());
            stmt.setInt(2, training.getTrainingID());
            stmt.setString(3, training.getStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
