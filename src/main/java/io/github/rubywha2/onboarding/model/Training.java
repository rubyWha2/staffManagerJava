package io.github.rubywha2.onboarding.model;
/**
 * Represents a training course, including its name, description, provider, and unique ID.
 */
public class Training {

    // Name of the training course
    private String trainingCourseName;
    // Description of what the training covers
    private String trainingDescription;
    // Name of the training provider or organization
    private String trainingProvider;
    // Unique identifier for the training course
    private int trainingID;

    /**
     * Constructor to initialize a Training object with all details.
     *
     * @param trainingCourseName  Name of the training course
     * @param trainingDescription Description of the training content
     * @param trainingProvider    Name of the training provider
     * @param trainingID          Unique identifier for the training course
     */
    public Training(String trainingCourseName, String trainingDescription, String trainingProvider, int trainingID) {
        this.trainingCourseName = trainingCourseName;
        this.trainingDescription = trainingDescription;
        this.trainingProvider = trainingProvider;
        this.trainingID = trainingID;
    }

    /**
     * Gets the training course name.
     *
     * @return trainingCourseName
     */
    public String getTrainingCourseName() {
        return trainingCourseName;
    }

    /**
     * Sets the training course name.
     *
     * @param trainingCourseName Name to set
     */
    public void setTrainingCourseName(String trainingCourseName) {
        this.trainingCourseName = trainingCourseName;
    }

    /**
     * Gets the training description.
     *
     * @return trainingDescription
     */
    public String getTrainingDescription() {
        return trainingDescription;
    }

    /**
     * Sets the training description.
     *
     * @param trainingDescription Description to set
     */
    public void setTrainingDescription(String trainingDescription) {
        this.trainingDescription = trainingDescription;
    }

    /**
     * Gets the training provider name.
     *
     * @return trainingProvider
     */
    public String getTrainingProvider() {
        return trainingProvider;
    }

    /**
     * Sets the training provider name.
     *
     * @param trainingProvider Provider name to set
     */
    public void setTrainingProvider(String trainingProvider) {
        this.trainingProvider = trainingProvider;
    }

    /**
     * Gets the training course ID.
     *
     * @return trainingID
     */
    public int getTrainingID() {
        return trainingID;
    }

    /**
     * Sets the training course ID.
     *
     * @param trainingID ID to set
     */
    public void setTrainingID(int trainingID) {
        this.trainingID = trainingID;
    }
}
