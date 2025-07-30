package io.github.rubywha2.onboarding.model;

public class JobRoles {
    private String JobRole;
    private double RateOfPay;
    private String Description;

    public JobRoles(String JobRole, double RateOfPay, String Description) {
        this.JobRole = JobRole;
        this.RateOfPay = RateOfPay;
        this.Description = Description;
    }
    public String getJobRole() {
        return JobRole;
    }
    public void setJobRole(String JobRole) {
        this.JobRole = JobRole;
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
