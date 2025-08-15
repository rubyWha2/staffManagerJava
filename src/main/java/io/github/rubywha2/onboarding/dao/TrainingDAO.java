package io.github.rubywha2.onboarding.dao;
import io.github.rubywha2.onboarding.Database.DatabaseConnector;
import io.github.rubywha2.onboarding.model.StaffTraining;
import io.github.rubywha2.onboarding.model.Training;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class TrainingDAO {

    /**
     * Retrieves all training courses from the database.
     *
     * @return a List of Training objects representing all training courses.
     */
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

    /**
     * Retrieves all staff training records from the database.
     *
     * @return a List of StaffTraining objects representing staff training statuses.
     */
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

    /**
     * Retrieves a list of all training IDs from the training courses.
     *
     * @return a List of Integer representing all TrainingIDs.
     */
    public List<Integer> getAllTrainingIDs() {
        List<Integer> trainingIDs = new ArrayList<>();

        List<Training> trainingList = getAlltrainingCourses(); // Assuming this returns your List<Training>
        for (Training course : trainingList) {
            trainingIDs.add(course.getTrainingID());
        }

        return trainingIDs;
    }

    /**
     * Retrieves a list of all staff IDs from the database.
     *
     * @return a List of String representing all StaffIDs.
     */
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

    /**
     * Marks a training as completed for a given staff member.
     *
     * @param TrainingID the ID of the training course.
     * @param StaffID the ID of the staff member.
     * @return true if the status update was successful, false otherwise.
     */
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

    /**
     * Assigns a training course to a staff member.
     *
     * @param training a StaffTraining object representing the assignment details.
     * @return true if the insertion was successful, false otherwise.
     */
    public Boolean AssignTraining(StaffTraining training) {
        String query = "INSERT INTO Staff_Training (StaffID, TrainingID, Status) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set parameters for the insert query
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
