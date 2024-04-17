package application;

import java.time.LocalDate;
import java.time.LocalTime;

import java.time.LocalDate;
import java.sql.Time;

public class Appointment {
    private String appointmentID;
    private String patientID;
    private String doctorID;
    private LocalDate appointmentDate;
    private Time appointmentTime;
    private Time duration;
    private String appointmentType;
    private String paymentStatus;
    private String comments;

    public Appointment() {
    }

    public Appointment(String appointmentID, String patientID, String doctorID, LocalDate appointmentDate,
                       Time appointmentTime, Time duration, String appointmentType, String paymentStatus,
                       String comments) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.duration = duration;
        this.appointmentType = appointmentType;
        this.paymentStatus = paymentStatus;
        this.comments = comments;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public Time getDuration() {
        return duration;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getComments() {
        return comments;
    }

	public void setAppointmentID(String appointmentID) {
		this.appointmentID = appointmentID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public void setAppointmentTime(Time appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
   
}
