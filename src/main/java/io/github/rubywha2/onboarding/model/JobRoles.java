package io.github.rubywha2.onboarding.model;

public class JobRoles {
    private String RoleID;
    private double RateOfPay;
    private String Description;

    public JobRoles(String RoleID, double RateOfPay, String Description) {
        this.RoleID = RoleID;
        this.RateOfPay = RateOfPay;
        this.Description = Description;
    }
    public String getRoleID() {
        return RoleID;
    }
    public void setRoleID(String RoleID) {
        this.RoleID = RoleID;
    }
    public double getRateOfPay() {
        return RateOfPay;
    }
    public void setRateOfPay(double RateOfPay) {
        this.RateOfPay = RateOfPay;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String Description) {
        this.Description = Description;
    }

}
