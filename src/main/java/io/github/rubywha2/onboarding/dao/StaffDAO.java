package io.github.rubywha2.onboarding.dao;
import io.github.rubywha2.onboarding.Database.DatabaseConnector;
import io.github.rubywha2.onboarding.model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {

    public List<Staff> getStaffData() {
        List<Staff> staffList = new ArrayList<>();

        String query = "SELECT Firstname, Lastname, Email, Postcode,StaffID, DBS_Number, RoleID  FROM Staff";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Staff MemberOfstaff = new Staff(
                        rs.getString("Firstname"),
                        rs.getString("Lastname"),
                        rs.getString("Email"),
                        rs.getString("Postcode"),
                        rs.getString("StaffID"),
                        rs.getInt("DBS_Number"),
                        rs.getString("RoleID")
                );
                staffList.add(MemberOfstaff);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with logging later
        }

        return staffList;
    }


    public Boolean SaveStaff(Staff updatedStaff) {
        String query = "UPDATE Staff SET Firstname = ?, Lastname = ?, Email = ?, Postcode = ?, DBS_Number = ?, RoleID = ? WHERE StaffID = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            //sets 1st question mark into Firstname
            stmt.setString(1, updatedStaff.getFirstname());
            stmt.setString(2, updatedStaff.getLastname());
            stmt.setString(3, updatedStaff.getEmail());
            stmt.setString(4, updatedStaff.getPostcode());
            stmt.setInt(5, updatedStaff.getDBSnumber());
            stmt.setString(6, updatedStaff.getRoleID());
            stmt.setString(7, updatedStaff.getStaffID());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
