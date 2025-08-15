package o.github.rubywha2.onboardingtests.dao;
import io.github.rubywha2.onboarding.dao.JobFitDAO;
import io.github.rubywha2.onboarding.Database.DatabaseConnector;
import io.github.rubywha2.onboarding.model.JobRoleTraining;
import io.github.rubywha2.onboarding.model.StaffTraining;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JobFitDAOTest {

    private JobFitDAO dao;

    @BeforeEach
    void setUp() throws SQLException {
        dao = new JobFitDAO();

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            // Make sure table exists (will not overwrite real data if it already exists)
            stmt.execute("CREATE TABLE IF NOT EXISTS JobRoleTraining(RoleID VARCHAR(100), TrainingID int)");

            // Insert test data
            stmt.execute("INSERT INTO JobRoleTraining(RoleID, TrainingID) VALUES ('ASHP', 11)");
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            // Remove the inserted row to keep DB clean
            stmt.execute("DELETE FROM JobRoleTraining WHERE RoleID='ASHP' AND TrainingID=11");
        }
    }

    @Test
    void testGetAllJobRolesTrainingInstances() {
        // Call the method being tested
        List<JobRoleTraining> results = dao.getAllJobRolesTrainingInstances();
        assertEquals(17, results.size()); // Expecting 17 rows from the database
    }

    @Test
    void testgetStaffTraining() {
        // Call the method being tested
        List<String[]> results = dao.getStaffTraining("STest");
        assertEquals(2, results.size()); // Expecting 2 rows from the database
    }

    @Test
    void testgetAllJobRoles() {
        // Call the method being tested
        List<String> results = dao.getAllJobRoles();
        assertEquals(6, results.size()); // Expecting 6 rows from the database
    }


    @Test
    void testgetAllStaffsTrainings() {
        // Call the method being tested
        List<StaffTraining> staffTrainingList = dao.getAllStaffsTrainings("STest");
        assertEquals(2, staffTrainingList.size());
        assertEquals("STest", staffTrainingList.get(0).getStaffID());
        //Thw first trainingID should be 12
        assertEquals(12, staffTrainingList.get(0).getTrainingID());
        //The status of the first trainingID should be Completed
        assertEquals("Completed", staffTrainingList.get(0).getStatus());
    }

    @Test
    void testgetRequiredTrainingForRole() {
        // Call the method being tested
        List<String>trainingIDs = dao.getRequiredTrainingForRole("CW1");
        //There should be 5 trainingIDs returned for CW1
        assertEquals(5, trainingIDs.size());
        //The first trainingID returned should be 10
        assertEquals("10", trainingIDs.get(0));

    }

    @Test
    void testgetAllStaffIDs() {
        // Call the method being tested
        List<String> staffIDList = dao.getAllStaffIDs();
        //Therer should be 12 staffIDs returned
        assertEquals(12, staffIDList.size());

    }
}

