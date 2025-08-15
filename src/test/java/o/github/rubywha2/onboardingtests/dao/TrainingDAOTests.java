package o.github.rubywha2.onboardingtests.dao;
import io.github.rubywha2.onboarding.Database.DatabaseConnector;
import io.github.rubywha2.onboarding.model.StaffTraining;
import io.github.rubywha2.onboarding.model.Training;
import io.github.rubywha2.onboarding.dao.TrainingDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TrainingDAOTest {

    private TrainingDAO dao;
    private int testTrainingID;
    private final String testStaffID = "TEST_STAFF";

    @BeforeEach
    void setUp() throws SQLException {
        dao = new TrainingDAO();

        // Generate a unique TrainingID using current time
        testTrainingID = (int) (System.currentTimeMillis() % Integer.MAX_VALUE);

        // Insert test training data
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO Training (Training_Course_Name, Training_Description, Training_Provider, TrainingID) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, "Test Course");
            stmt.setString(2, "Test Description");
            stmt.setString(3, "Test Provider");
            stmt.setInt(4, testTrainingID);
            stmt.executeUpdate();
        }

        // Insert test staff training assignment
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO Staff_Training (StaffID, TrainingID, Status) VALUES (?, ?, ?)")) {

            stmt.setString(1, testStaffID);
            stmt.setInt(2, testTrainingID);
            stmt.setString(3, "Pending");
            stmt.executeUpdate();
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Remove test staff training
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Staff_Training WHERE StaffID = ? AND TrainingID = ?")) {
            stmt.setString(1, testStaffID);
            stmt.setInt(2, testTrainingID);
            stmt.executeUpdate();
        }

        // Remove test training data
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Training WHERE TrainingID = ?")) {
            stmt.setInt(1, testTrainingID);
            stmt.executeUpdate();
        }
    }

    @Test
    void testGetAlltrainingCourses() {
        List<Training> trainingList = dao.getAlltrainingCourses();

        // Check that our test training exists in the returned list
        boolean found = trainingList.stream().anyMatch(t -> t.getTrainingID() == 10);
        assertTrue(found, "Test training should be present in the list");
    }

    @Test
    void testgetAllStafftraining() {
        List<StaffTraining> staffTrainingList = dao.getAllStafftraining();
        assertTrue(staffTrainingList.size() > 18, "Expected at least 18 training courses in the database");
        staffTrainingList.forEach(ID -> System.out.println(ID.getTrainingID()));
    }

    @Test
    void testgetTrainingByID() {
        List<Integer> trainingIDs= dao.getAllTrainingIDs();
        assertTrue(trainingIDs.size() > 9, "Expected at least 10 training courses in the database");
        trainingIDs.forEach(ID -> System.out.println(ID));
    }

    @Test
    void testGetAllStaffIDs() {
        List<String> staffList = dao.getAllStaffIDs();
        assertTrue(staffList.size() > 11, "Expected at least 6 staff members in the database");
        staffList.forEach(staffID -> System.out.println(staffID));
    }
    @Test
    void testCompleteTraining() {
        boolean completed = dao.CompleteTraining(String.valueOf(testTrainingID), testStaffID);
        assertTrue(completed, "Training should be marked as completed");

        // Verify the status is updated
        List<StaffTraining> staffTrainingList = dao.getAllStafftraining();
        StaffTraining updated = staffTrainingList.stream()
                .filter(t -> t.getTrainingID() == testTrainingID && t.getStaffID().equals(testStaffID))
                .findFirst()
                .orElse(null);
        assertNotNull(updated);
        assertEquals("Completed", updated.getStatus(), "Status should be 'Completed'");
    }

    @Test
    void testAssignTraining() {
        StaffTraining newTraining = new StaffTraining("NEW_STAFF", testTrainingID, "Pending");
        boolean assigned = dao.AssignTraining(newTraining);
        assertTrue(assigned, "Training should be successfully assigned");

        // Cleanup the new assignment
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Staff_Training WHERE StaffID = ? AND TrainingID = ?")) {
            stmt.setString(1, "NEW_STAFF");
            stmt.setInt(2, testTrainingID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

