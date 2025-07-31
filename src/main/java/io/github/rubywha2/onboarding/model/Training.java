package io.github.rubywha2.onboarding.model;

public class Training {
    private String trainingCourseName;
    private String trainingDescription;
    private String trainingProvider;
    private int trainingID;

    public Training(String trainingCourseName, String trainingDescription, String trainingProvider, int trainingID) {
        this.trainingCourseName = trainingCourseName;
        this.trainingDescription = trainingDescription;
        this.trainingProvider = trainingProvider;
        this.trainingID = trainingID;
    }

    public String getTrainingCourseName(){
        return trainingCourseName;
    }
    public void setTrainingCourseName(String trainingCourseName){
        this.trainingCourseName = trainingCourseName;
    }
    public String getTrainingDescription(){
        return trainingDescription;
    }
    public void setTrainingDescription(String trainingDescription){
        this.trainingDescription = trainingDescription;
    }
    public String getTrainingProvider(){
        return trainingProvider;
    }
    public void setTrainingProvider(String trainingProvider){
        this.trainingProvider = trainingProvider;
    }
    public int getTrainingID(){
        return trainingID;
    }
    public void setTrainingID(int trainingID){
        this.trainingID = trainingID;
    }

}
