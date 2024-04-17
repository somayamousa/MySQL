package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dector {
	IntegerProperty Doctor_ID;
	StringProperty First_Name;
	StringProperty Middle_Name;
	StringProperty Last_Name;
	StringProperty Specially;
	//StringProperty Gender;
	LongProperty Phone_Number;
	StringProperty StartTime;
	StringProperty EndTime;
	StringProperty Diagnosis;
	StringProperty Email;

	public Dector() {
		super();
	}

	public Dector(IntegerProperty doctor_ID, StringProperty first_Name, StringProperty middle_Name,
			StringProperty last_Name, StringProperty specially, StringProperty gender, LongProperty phone_Number,
			StringProperty startTime, StringProperty endTime, StringProperty email, StringProperty diagnosis) {
		super();
		Doctor_ID = doctor_ID;
		First_Name = first_Name;
		Middle_Name = middle_Name;
		Last_Name = last_Name;
		Specially = specially;
		//Gender = gender;
		Phone_Number = phone_Number;
		StartTime = startTime;
		EndTime = endTime;
		Diagnosis = diagnosis;
		Email = email;
	}

	public Dector(int doctor_ID, String first_Name, String middle_Name, String last_Name, String specially,
		 long phone_Number, String startTime, String endTime, String email, String diagnosis) {

		this.Doctor_ID = new SimpleIntegerProperty(doctor_ID);
		this.First_Name = new SimpleStringProperty(first_Name);
		this.Middle_Name = new SimpleStringProperty(middle_Name);
		this.Last_Name = new SimpleStringProperty(last_Name);
		this.Specially = new SimpleStringProperty(specially);
		//this.Gender = new SimpleStringProperty(gender);
		this.Phone_Number = new SimpleLongProperty(phone_Number);
		this.StartTime = new SimpleStringProperty(startTime);
		this.EndTime = new SimpleStringProperty(endTime);
		this.Email = new SimpleStringProperty(email);
		this.Diagnosis = new SimpleStringProperty(diagnosis);
	}

	public Integer getDoctor_ID() {
		return Doctor_ID.get();
	}

	public void setDoctor_ID(int Doctor_ID) {
		this.Doctor_ID.set(Doctor_ID);
	}

	public IntegerProperty Doctor_IDProperty() {
		if (Doctor_ID == null) {
			Doctor_ID = new SimpleIntegerProperty();
		}
		return Doctor_ID;
	}

	public String getFirst_Name() {
		return First_Name.get();
	}

	public void setFirst_Name(String First_Name) {
		this.First_Name.set(First_Name);
	}

	public StringProperty First_NameProperty() {
		if (First_Name == null) {
			First_Name = new SimpleStringProperty();
		}
		return First_Name;

	}

	public String getMiddle_Name() {
		return Middle_Name.get();
	}

	public void setMiddle_Name(String Middle_Name) {
		this.Middle_Name.set(Middle_Name);
	}

	public StringProperty Middle_NameProperty() {
		if (Middle_Name == null) {
			Middle_Name = new SimpleStringProperty();
		}
		return Middle_Name;

	}

	public String getLast_Name() {
		return Middle_Name.get();
	}

	public void setLast_Name(String Last_Name) {
		this.Last_Name.set(Last_Name);
	}

	public StringProperty Last_NameProperty() {
		if (Last_Name == null) {
			Last_Name = new SimpleStringProperty();
		}
		return Last_Name;

	}

	public String getSpecially() {
		return Specially.get();
	}

	public void setSpecially(String Specially) {
		this.Specially.set(Specially);
	}

	public StringProperty SpeciallyProperty() {
		if (Specially == null) {
			Specially = new SimpleStringProperty();
		}
		return Specially;

	}

//	public String getGender() {
//		return Gender.get();
//	}
//
//	public void setGender(String Gender) {
//		this.Gender.set(Gender);
//	}
//
//	public StringProperty GenderProperty() {
//		if (Gender == null) {
//			Gender = new SimpleStringProperty();
//		}
//		return Gender;
//
//	}

	public String getstartTime() {
		return StartTime.get();
	}

	public void setWork_Houres(String Work_Houres) {
		this.StartTime.set(Work_Houres);
	}

	public StringProperty Work_HouresProperty() {
		if (StartTime == null) {
			StartTime = new SimpleStringProperty();
		}
		return StartTime;

	}

	public String getendTime() {
		return EndTime.get();
	}

	public void setEndTime(String Work_Houres) {
		this.EndTime.set(Work_Houres);
	}

	public StringProperty EndTimeProperty() {
		if (EndTime == null) {
			EndTime = new SimpleStringProperty();
		}
		return StartTime;

	}

	public Long getPhone_Number() {
		return Phone_Number.get();
	}

	public void setPhone_Number(Long Phone_Number) {
		this.Phone_Number.set(Phone_Number);
	}

	public LongProperty Phone_NumberProperty() {
		if (Phone_Number == null) {
			Phone_Number = new SimpleLongProperty();
		}
		return Phone_Number;

	}
	public String getEmail() {
		return Email.get();
	}

	public void setEmail(String email) {
		this.Email.set(email);
	}

	public StringProperty EmailProperty() {
		if (Email == null) {
			Email = new SimpleStringProperty();
		}
		return Email;

	}
	public String getDiagnosis() {
		return Diagnosis.get();
	}

	public void setDiagnosis(String email) {
		this.Diagnosis.set(email);
	}

	public StringProperty DiagnosisProperty() {
		if (Diagnosis==null) {
			Diagnosis = new SimpleStringProperty();
		}
		return Diagnosis;

	}

}
