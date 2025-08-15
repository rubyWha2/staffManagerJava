package o.github.rubywha2.onboardingtests.dao;

import io.github.rubywha2.onboarding.dao.JobRoleDAO;
import io.github.rubywha2.onboarding.model.JobRoles;
import io.github.rubywha2.onboarding.model.Staff;
import org.junit.jupiter.api.BeforeEach;
import io.github.rubywha2.onboarding.dao.StaffDAO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StaffDAOTests {
    private StaffDAO dao;

    @BeforeEach
    void setUp() {
        dao = new StaffDAO();
    }

    @Test
    void testGetAllStaffIDs() {
        List<Staff> staffList = dao.getStaffData();
        assertTrue(staffList.size() > 11, "Expected at least 6 staff members in the database");
        staffList.forEach(role -> System.out.println(role.getStaffID()));
    }

    @Test
    void testAddUpdateDeleteStaff() {
        Staff testStaff = new Staff("TestFirstnameStaff", "TestLastnameStaff", "Test@email.com","LSTest", "STest1",01010101,"IS1");

        // Add
        assertTrue(dao.AddNewStaff(testStaff), "Adding new staff member should succeed");

        // Update or Save
        assertTrue(dao.SaveStaff(testStaff), "Updating staff member should succeed");

        // Delete
        assertTrue(dao.DeleteStaff("STest1"), "Deleting staff member should succeed");
    }
}
