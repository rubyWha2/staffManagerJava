package o.github.rubywha2.onboardingtests.dao;
import io.github.rubywha2.onboarding.dao.JobRoleDAO;
import io.github.rubywha2.onboarding.model.JobRoles;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class JobRoleDAOTest {
    private JobRoleDAO dao;

    @BeforeEach
    void setUp() {
        dao = new JobRoleDAO();
    }

    @Test
    void testGetAllJobRoles() {
        List<JobRoles> roles = dao.getAllJobRoles();
        assertTrue(roles.size() > 0, "Expected at least one job role in the database");
        roles.forEach(role -> System.out.println(role.getRoleID()));
    }

    @Test
    void testAddUpdateDeleteRole() {
        JobRoles testRole = new JobRoles("TestRole", 25.0, "Test Description");

        // Add
        assertTrue(dao.AddNewRole(testRole), "Adding new role should succeed");

        // Update
        testRole.setRateOfPay(30.0);
        assertTrue(dao.SaveJobRole(testRole, "TestRole"), "Updating role should succeed");

        // Delete
        assertTrue(dao.DeleteRole("TestRole"), "Deleting role should succeed");
    }
}
