package io.github.rubywha2.onboarding;

public class JobRoles {
    private String JobRole;
    private String RateOfPay;
    private String Description;

    JobRoles(String JobRole, String RateOfPay, String Description) {
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
    public String getRateOfPay() {
        return RateOfPay;
    }
    public void setRateOfPay(String RateOfPay) {
        this.RateOfPay = RateOfPay;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String Description) {
        this.Description = Description;
    }

}
