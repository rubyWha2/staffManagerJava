package io.github.rubywha2.onboarding.model;

public class StaffTraining {
    private String StaffID;
    private int TrainingID;
    private String Status;

    public StaffTraining(String StaffID, int TrainingID, String Status) {
        this.StaffID = StaffID;
        this.TrainingID = TrainingID;
        this.Status = Status;
    }
    public String getStaffID() {
        return StaffID;
    }
    public void setStaffID(String StaffID) {
        this.StaffID = StaffID;
    }
    public int getTrainingID() {
        return TrainingID;
    }
    public void setTrainingID(int TrainingID) {
        this.TrainingID = TrainingID;
    }
    public String getStatus() {
        return Status;
    }
    public void setStatus(String TrainingName) {
        this.Status = Status;
    }

}
