package io.github.rubywha2.onboarding;

public class StaffTraining {
    private String StaffID;
    private String TrainingID;
    private String TrainingName;

    StaffTraining(String StaffID, String TrainingID, String TrainingName) {
        this.StaffID = StaffID;
        this.TrainingID = TrainingID;
        this.TrainingName = TrainingName;
    }
    public String getStaffID() {
        return StaffID;
    }
    public void setStaffID(String StaffID) {
        this.StaffID = StaffID;
    }
    public String getTrainingID() {
        return TrainingID;
    }
    public void setTrainingID(String TrainingID) {
        this.TrainingID = TrainingID;
    }
    public String getTrainingName() {
        return TrainingName;
    }
    public void setTrainingName(String TrainingName) {
        this.TrainingName = TrainingName;
    }

}
