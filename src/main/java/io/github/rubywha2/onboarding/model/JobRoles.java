package io.github.rubywha2.onboarding.model;
public class JobRoles {
    // Unique identifier for the job role
    private String RoleID;

    // Hourly or salary rate of pay for this role
    private double RateOfPay;

    // Description of the job role
    private String Description;

    /**
     * Constructor to create a new JobRoles object with specified details.
     *
     * @param RoleID      Unique identifier for the role
     * @param RateOfPay   Rate of pay associated with the role
     * @param Description Text description of the role
     */
    public JobRoles(String RoleID, double RateOfPay, String Description) {
        this.RoleID = RoleID;
        this.RateOfPay = RateOfPay;
        this.Description = Description;
    }

    /**
     * Gets the role's unique identifier.
     * @return the RoleID
     */
    public String getRoleID() {
        return RoleID;
    }

    /**
     * Gets the rate of pay for the role.
     * @return the RateOfPay
     */
    public double getRateOfPay() {
        return RateOfPay;
    }

    /**
     * Sets the rate of pay for the role.
     */
    public void setRateOfPay(double rateOfPay) {
        this.RateOfPay = RateOfPay;
    }

    /**
     * Gets the description of the job role.
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }
}

