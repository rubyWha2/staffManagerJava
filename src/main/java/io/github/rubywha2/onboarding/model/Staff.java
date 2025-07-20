package io.github.rubywha2.onboarding.model;

public class Staff {
    private String firstname;
    private String lastname;
    private String email;
    private String postcode;
    private String StaffID;
    private int DBSnumber;
    private String RoleID;

    Staff(String firstname, String lastname, String email, String postcode, String StaffID,int DBSnumber, String RoleID) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.postcode = postcode;
        this.StaffID = StaffID;
        this.DBSnumber = DBSnumber;
        this.RoleID = RoleID;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getStaffID() {
        return StaffID;
    }
    public void setStaffID(String staffID) {
        this.StaffID = staffID;
    }
    public int getDBSnumber() {
        return DBSnumber;
    }
    public void setDBSnumber(int DBSnumber) {
        this.DBSnumber = DBSnumber;
    }
    public String getRoleID() {
        return RoleID;
    }
    public void setRoleID(String roleID) {
        RoleID = roleID;
    }

}

