package io.github.rubywha2.onboarding.model;

/**
 * Represents a staff member with personal and job-related details.
 */
public class Staff {
    // Staff member's first name
    private String firstname;
    // Staff member's last name
    private String lastname;
    // Staff member's email address
    private String email;
    // Staff member's postal code
    private String postcode;
    // Unique identifier for the staff member
    private String StaffID;
    // DBS (background check) number for the staff member
    private int DBSnumber;
    // Role identifier associated with the staff member
    private String RoleID;

    /**
     * Constructor to initialize a Staff object with all attributes.
     *
     * @param firstname Staff's first name
     * @param lastname Staff's last name
     * @param email Staff's email
     * @param postcode Staff's postcode
     * @param StaffID Unique staff ID
     * @param DBSnumber Staff's DBS number
     * @param RoleID Role ID assigned to staff
     */
    public Staff(String firstname, String lastname, String email, String postcode, String StaffID, int DBSnumber, String RoleID) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.postcode = postcode;
        this.StaffID = StaffID;
        this.DBSnumber = DBSnumber;
        this.RoleID = RoleID;
    }

    // Getter and setter methods for each field below

    /**
     * Gets the staff member's first name.
     *
     * @return First name
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the staff member's first name.
     *
     * @param firstname First name to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets the staff member's last name.
     *
     * @return Last name
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the staff member's last name.
     *
     * @param lastname Last name to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets the staff member's email address.
     *
     * @return Email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the staff member's email address.
     *
     * @param email Email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the staff member's postcode.
     *
     * @return Postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the staff member's postcode.
     *
     * @param postcode Postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Gets the unique staff ID.
     *
     * @return Staff ID
     */
    public String getStaffID() {
        return StaffID;
    }

    /**
     * Sets the unique staff ID.
     *
     * @param staffID Staff ID to set
     */
    public void setStaffID(String staffID) {
        this.StaffID = staffID;
    }

    /**
     * Gets the staff member's DBS number.
     *
     * @return DBS number
     */
    public int getDBSnumber() {
        return DBSnumber;
    }

    /**
     * Sets the staff member's DBS number.
     *
     * @param DBSnumber DBS number to set
     */
    public void setDBSnumber(int DBSnumber) {
        this.DBSnumber = DBSnumber;
    }

    /**
     * Gets the role ID assigned to the staff member.
     *
     * @return Role ID
     */
    public String getRoleID() {
        return RoleID;
    }

    /**
     * Sets the role ID assigned to the staff member.
     *
     * @param roleID Role ID to set
     */
    public void setRoleID(String roleID) {
        RoleID = roleID;
    }
}


