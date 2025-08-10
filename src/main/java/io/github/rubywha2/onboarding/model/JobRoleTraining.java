package io.github.rubywha2.onboarding.model;

/**
 * Represents the relationship between a job role and the training required for that role.
 */
public class JobRoleTraining {
    // Identifier for the job role
    private String RoleID;

    // Identifier for the training associated with the job role
    private String TrainingID;

    /**
     * Constructor to create a new JobRoleTraining mapping.
     *
     * @param RoleID     The job role's unique identifier
     * @param TrainingID The training's unique identifier
     */
    public JobRoleTraining(String RoleID, String TrainingID) {
        this.RoleID = RoleID;
        this.TrainingID = TrainingID;
    }

    /**
     * Gets the job role ID.
     *
     * @return RoleID
     */
    public String getRoleID() {
        return RoleID;
    }

    /**
     * Gets the training ID.
     *
     * @return TrainingID
     */
    public String getTrainingID() {
        return TrainingID;
    }
}
