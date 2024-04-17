package application;

import java.time.LocalDate;
import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tretments {
	

	IntegerProperty Patient_ID;
	IntegerProperty Tretment_ID;
	IntegerProperty Dector_ID;
	StringProperty Tretment_Name;
	StringProperty Description;
	DoubleProperty Cost;
	IntegerProperty Doctor_ID;
	ObjectProperty<LocalDate> dateofService;

	public Tretments() {
		super();
	}

	public Tretments(IntegerProperty patient_ID, IntegerProperty tretment_ID,IntegerProperty dector_ID, StringProperty tretment_Name,
			StringProperty description, DoubleProperty cost, ObjectProperty<LocalDate> DateofService) {
		super();
		Patient_ID = patient_ID;
		 Dector_ID= dector_ID;
		Tretment_ID = tretment_ID;
		Tretment_Name = tretment_Name;
		Description = description;
		Cost = cost;
		dateofService = DateofService;
	}

	public Tretments(int tretment_ID ,String tretment_Name, String description,
			LocalDate DateofService, double cost,int patient_ID,int dector_ID) {
		this.Tretment_ID = new SimpleIntegerProperty(tretment_ID);
		this.Tretment_Name = new SimpleStringProperty(tretment_Name);
		this.Description = new SimpleStringProperty(description);
		this.dateofService = new SimpleObjectProperty<>(DateofService);
		this.Cost = new SimpleDoubleProperty(cost);
		this.Patient_ID = new SimpleIntegerProperty(patient_ID);
		this.Doctor_ID=new SimpleIntegerProperty(dector_ID);
	}

	public Integer getPatient_ID() {
		return Patient_ID.get();
	}

	public void setPatient_ID(int Patient_ID) {
		this.Patient_ID.set(Patient_ID);
	}

	public IntegerProperty Patient_IDProperty() {
		if (Patient_ID == null) {
			Patient_ID = new SimpleIntegerProperty();
		}
		return Patient_ID;
	}

	public Integer getTretment_ID() {
		return Tretment_ID.get();
	}

	public void setTretment_ID(int Tretment_ID) {
		this.Tretment_ID.set(Tretment_ID);
	}

	public IntegerProperty Tretment_IDProperty() {
		if (Tretment_ID == null) {
			Tretment_ID = new SimpleIntegerProperty();
		}
		return Tretment_ID;
	}

	public String getTretment_Name() {
		return Tretment_Name.get();
	}

	public void setTretment_Name(String Tretment_Name) {
		this.Tretment_Name.set(Tretment_Name);
	}

	public StringProperty Tretment_NameProperty() {
		if (Tretment_Name == null) {
			Tretment_Name = new SimpleStringProperty();
		}
		return Tretment_Name;

	}
    public Integer getDector_ID() {
        return Doctor_ID.get();
    }
    
    public void setDector_ID(int Dector_ID) {
        this.Doctor_ID.set(Dector_ID);
    }
    
    public IntegerProperty Dector_IDProperty() {
        if (Doctor_ID == null) {
            Doctor_ID = new SimpleIntegerProperty();
        }
        return Doctor_ID;
    }


	public String getDescription() {
		return Description.get();
	}

	public void setDescription(String Description) {
		this.Description.set(Description);
	}

	public StringProperty DescriptionProperty() {
		if (Description == null) {
			Description = new SimpleStringProperty();
		}
		return Description;

	}

	public double getCost() {
		return Cost.get();
	}

	public void setCost(double Cost) {
		this.Cost.set(Cost);
	}

	public DoubleProperty CostProperty() {
		if (Cost == null) {
			Cost = new SimpleDoubleProperty();
		}
		return Cost;

	}

	public LocalDate getsetDateofService() {
		return dateofService.get();
	}

	public void setDateofService(LocalDate date) {
		this.dateofService.set(date);
	}

	public ObjectProperty<LocalDate> dateProperty() {
		return dateofService;
	}


}
