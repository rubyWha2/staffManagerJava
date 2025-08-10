package io.github.rubywha2.onboarding.dao;
import io.github.rubywha2.onboarding.Database.DatabaseConnector;
import io.github.rubywha2.onboarding.model.JobRoleTraining;
import io.github.rubywha2.onboarding.model.StaffTraining;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobFitDAO {

    /**
     * Retrieves all job role training instances from the database.
     *
     * @return a List of JobRoleTraining objects representing all job role training instances.
     */
    public List<JobRoleTraining> getAllJobRolesTrainingInstances() {
        List<JobRoleTraining> JobRoleTrainingList = new ArrayList<>();

        String query = "SELECT RoleID, TrainingID FROM JobRoleTraining";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                JobRoleTraining jobRoleTrainingInstance = new JobRoleTraining(
                        rs.getString("RoleID"),
                        rs.getString("TrainingID")
                );
                JobRoleTrainingList.add(jobRoleTrainingInstance);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging later
        }

        return JobRoleTrainingList;
    }

    /**
     * Retrieves the training records for a specific staff member.
     *
     * @param staffID the ID of the staff member whose training records are to be retrieved.
     * @return a List of String arrays, where each array contains the TrainingID and Status of a training record.
     */
    public List<String[]> getStaffTraining(String staffID) {
        List<String[]> trainings = new ArrayList<>();
        String query = "SELECT TrainingID, Status FROM Staff_Training WHERE StaffID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, staffID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    trainings.add(new String[] {
                            rs.getString("TrainingID"),
                            rs.getString("Status")
                    });
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trainings;
    }

    /**
     * Retrieves all job role IDs from the database.
     *
     * @return a List of Strings representing all job role IDs.
     */
    public List<String> getAllJobRoles() {
        List<String> allJobRolesList = new ArrayList<>();
        String query = "SELECT RoleID FROM JobRoles";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    allJobRolesList.add(rs.getString("RoleID"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Consider replacing with proper logging
        }

        return allJobRolesList;
    }

    /**
     * Retrieves all training records for a specific staff member as StaffTraining objects.
     *
     * @param staffID the ID of the staff member whose training records are to be retrieved.
     * @return a List of StaffTraining objects representing the staff member's trainings.
     */
    public List<StaffTraining> getAllStaffsTrainings(String staffID) {
        List<StaffTraining> staffTrainingList = new ArrayList<>();

        String query = "SELECT StaffID, TrainingID, Status FROM Staff_Training WHERE StaffID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, staffID);  // Set the parameter first

            try (ResultSet rs = stmt.executeQuery()) { // Then execute the query
                while (rs.next()) {
                    StaffTraining memberOfStaff = new StaffTraining(
                            rs.getString("StaffID"),
                            rs.getInt("TrainingID"),
                            rs.getString("Status")
                    );
                    staffTrainingList.add(memberOfStaff);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging if needed
        }

        return staffTrainingList;
    }

    /**
     * Retrieves all required training IDs for a given job role.
     *
     * @param roleID the ID of the job role whose required training IDs are to be retrieved.
     * @return a List of Strings representing the TrainingIDs required for the specified role.
     */
    public List<String> getRequiredTrainingForRole(String roleID) {
        List<String> trainingIDs = new ArrayList<>();
        String query = "SELECT TrainingID FROM JobRoleTraining WHERE RoleID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, roleID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    trainingIDs.add(rs.getString("TrainingID"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trainingIDs;
    }

    /**
     * Retrieves all staff IDs from the database.
     *
     * @return a List of Strings representing all staff IDs.
     */
    public List<String> getAllStaffIDs() {
        List<String> staffIDList = new ArrayList<>();
        String query = "SELECT StaffID FROM Staff";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                staffIDList.add(rs.getString("StaffID"));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging if needed
        }

        return staffIDList;
    }
}

