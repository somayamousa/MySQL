package application;

import java.time.LocalDate;

/*-- patient table
create table patient(
patientId varchar(50),firstName varChar(50),middleName varchar(50),lastName varChar(50), gender char(1) , 
age double , DateOfBirth date, Address varChar(150) , phoneNumber  VARCHAR(15), MedicalHistory  VARCHAR(150),
 InsuranceID varchar(50),primary key(patientId)
 ,foreign key (
 InsuranceID) references HealthInsurance(InsuranceID)
);*/
public class Patient {
	private String patientId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private int age;
	private String dateOfBirth;
	private String address;
	private String phoneNumber;
	private String medicalHistory;
	private String insuranceId;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public Patient() {
		super();
	}

	public Patient(String patientId, String firstName, String middleName, String lastName, String gender, int age,
			String dateOfBirth, String address, String phoneNumber, String medicalHistory, String insuranceId) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.medicalHistory = medicalHistory;
		this.insuranceId = insuranceId;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

}