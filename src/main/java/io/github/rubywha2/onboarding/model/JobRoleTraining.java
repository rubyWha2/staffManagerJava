package io.github.rubywha2.onboarding.model;

import java.util.List;

public class JobRoleTraining {
    private String RoleID;
    private String TrainingID;

    public JobRoleTraining(String RoleID, String TrainingID) {
        this.RoleID = RoleID;
        this.TrainingID = TrainingID;
    }

    public String getRoleID() {
        return RoleID;
    }
    public String getTrainingID() {
        return TrainingID;
    }

}
