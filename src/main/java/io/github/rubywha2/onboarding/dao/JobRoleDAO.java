package io.github.rubywha2.onboarding.dao;
import io.github.rubywha2.onboarding.Database.DatabaseConnector;
import io.github.rubywha2.onboarding.model.JobRoles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDAO {

    /**
     * Retrieves all job roles from the database.
     *
     * @return a List of JobRoles objects representing all job roles.
     */
    public List<JobRoles> getAllJobRoles() {
        List<JobRoles> jobRolesList = new ArrayList<>();

        String query = "SELECT RoleID, RateOfPay, Description  FROM JobRoles";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                JobRoles role = new JobRoles(
                        rs.getString("RoleID"),
                        rs.getDouble("RateOfPay"),
                        rs.getString("Description")
                );
                jobRolesList.add(role);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging later
        }

        return jobRolesList;
    }

    /**
     * Updates an existing job role in the database.
     *
     * @param updatedRole    the JobRoles object containing updated role information.
     * @param originalRoleID the original RoleID of the job role to be updated.
     * @return true if the update was successful, false otherwise.
     */
    public Boolean SaveJobRole(JobRoles updatedRole, String originalRoleID) {
        String query = "UPDATE JobRoles SET RoleID = ?, RateOfPay = ?, Description = ? WHERE RoleID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set parameters for the update query
            stmt.setString(1, updatedRole.getRoleID());
            stmt.setDouble(2, updatedRole.getRateOfPay());
            stmt.setString(3, updatedRole.getDescription());
            stmt.setString(4, originalRoleID);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a job role from the database based on the given RoleID.
     *
     * @param RoleID the ID of the job role to be deleted.
     * @return true if the deletion was successful, false otherwise.
     */
    public Boolean DeleteRole(String RoleID) {
        String query = "DELETE FROM JobRoles WHERE RoleID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the RoleID parameter for deletion
            stmt.setString(1, RoleID);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Adds a new job role to the database.
     *
     * @param newRole the JobRoles object representing the new job role to be added.
     * @return true if the insertion was successful, false otherwise.
     */
    public Boolean AddNewRole(JobRoles newRole) {
        String query = "INSERT INTO JobRoles VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set parameters for the insert query
            stmt.setString(1, newRole.getRoleID());
            stmt.setDouble(2, newRole.getRateOfPay());
            stmt.setString(3, newRole.getDescription());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

