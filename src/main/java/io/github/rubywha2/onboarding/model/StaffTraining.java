package io.github.rubywha2.onboarding.model;
/**
 * Represents the relationship between a staff member and a training course,
 * including the training status.
 */
public class StaffTraining {
    // Unique identifier for the staff member
    private String StaffID;
    // Unique identifier for the training course
    private int TrainingID;
    // Status of the training (e.g., "Started", "Completed", "Failed")
    private String Status;

    /**
     * Constructor to initialize a StaffTraining object with all attributes.
     *
     * @param StaffID    Unique staff member ID
     * @param TrainingID Unique training course ID
     * @param Status     Training status
     */
    public StaffTraining(String StaffID, int TrainingID, String Status) {
        this.StaffID = StaffID;
        this.TrainingID = TrainingID;
        this.Status = Status;
    }

    /**
     * Gets the staff member ID.
     *
     * @return StaffID
     */
    public String getStaffID() {
        return StaffID;
    }

    /**
     * Sets the staff member ID.
     *
     * @param StaffID ID to set
     */
    public void setStaffID(String StaffID) {
        this.StaffID = StaffID;
    }

    /**
     * Gets the training course ID.
     *
     * @return TrainingID
     */
    public int getTrainingID() {
        return TrainingID;
    }

    /**
     * Sets the training course ID.
     *
     * @param TrainingID ID to set
     */
    public void setTrainingID(int TrainingID) {
        this.TrainingID = TrainingID;
    }

    /**
     * Gets the status of the training.
     *
     * @return Training status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * Sets the status of the training.
     *
     * @param Status Training status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }
}
