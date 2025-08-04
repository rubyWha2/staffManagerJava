package io.github.rubywha2.onboarding.dao;

import io.github.rubywha2.onboarding.Database.DatabaseConnector;
import io.github.rubywha2.onboarding.model.JobRoleTraining;
import io.github.rubywha2.onboarding.model.JobRoles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobFitDAO {

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
}
