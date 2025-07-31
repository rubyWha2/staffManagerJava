package io.github.rubywha2.onboarding.dao;

import io.github.rubywha2.onboarding.Database.DatabaseConnector;
import io.github.rubywha2.onboarding.model.JobRoles;
import io.github.rubywha2.onboarding.model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDAO {
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
    public Boolean SaveJobRole(JobRoles updatedRole, String originalRoleID) {
        String query = "UPDATE JobRoles SET RoleID = ?, RateOfPay = ?, Description = ? WHERE RoleID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            //sets 1st question mark into Firstname
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

    public Boolean DeleteRole(String RoleID) {
        String query = "DELETE FROM JobRoles WHERE RoleID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            //sets StaffID to input ID
            stmt.setString(1, RoleID);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Boolean AddNewRole(JobRoles newRole) {
        String query = "INSERT INTO JobRoles VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            //sets 1st question mark into Firstname
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
