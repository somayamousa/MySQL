package application;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;



import javafx.beans.property.StringProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;

import javafx.beans.property.SimpleObjectProperty;

public class BillingData {

    private final StringProperty billingId;
    private final ObjectProperty<LocalDate> serviceDate;
    private final StringProperty serviceDescription;
    private final StringProperty billedAmount;

    public BillingData(String billingId, LocalDate serviceDate, String serviceDescription, String billedAmount) {
        this.billingId = new SimpleStringProperty(billingId);
        this.serviceDate = new SimpleObjectProperty<>(serviceDate);
        this.serviceDescription = new SimpleStringProperty(serviceDescription);
        this.billedAmount = new SimpleStringProperty(billedAmount);
    }

    // Getter methods
    public String getBillingId() {
        return billingId.get();
    }

    public LocalDate getServiceDate() {
        return serviceDate.get();
    }

    public String getServiceDescription() {
        return serviceDescription.get();
    }

    public String getBilledAmount() {
        return billedAmount.get();
    }

    // Setter methods
    public void setBillingId(String billingId) {
        this.billingId.set(billingId);
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate.set(serviceDate);
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription.set(serviceDescription);
    }

    public void setBilledAmount(String billedAmount) {
        this.billedAmount.set(billedAmount);
    }

    // Property methods
    public StringProperty billingIdProperty() {
        return billingId;
    }

    public ObjectProperty<LocalDate> serviceDateProperty() {
        return serviceDate;
    }

    public StringProperty serviceDescriptionProperty() {
        return serviceDescription;
    }

    public StringProperty billedAmountProperty() {
        return billedAmount;
    }

    // Method to check if the BillingData matches a given Billing ID
    public boolean matchesBillingId(String searchTerm) {
        return billingId.get().equalsIgnoreCase(searchTerm);
    }
}
