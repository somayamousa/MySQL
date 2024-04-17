package application;

public class Insurance {
    private String insuranceId;
    private String insuranceCompany;
    private String policyStartDate;
    private String policyEndDate;

    // Constructor, setters, and other methods...

   

	public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public void setPolicyStartDate(String policyStartDate) {
		this.policyStartDate = policyStartDate;
	}

	public void setPolicyEndDate(String policyEndDate) {
		this.policyEndDate = policyEndDate;
	}

	public Insurance(String insuranceId, String insuranceCompany, String policyStartDate, String policyEndDate) {
		super();
		this.insuranceId = insuranceId;
		this.insuranceCompany = insuranceCompany;
		this.policyStartDate = policyStartDate;
		this.policyEndDate = policyEndDate;
	}

	public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public String getPolicyStartDate() {
        return policyStartDate;
    }

    public String getPolicyEndDate() {
        return policyEndDate;
    }
}