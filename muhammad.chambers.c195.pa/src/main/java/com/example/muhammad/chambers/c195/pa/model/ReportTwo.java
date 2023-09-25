package com.example.muhammad.chambers.c195.pa.model;

import java.sql.Timestamp;

public class ReportTwo {
    private int appointmentID;
    private int customerID;
    private String type;
    private String contact;
    private String description;
    private String title;
    private Timestamp start;
    private Timestamp end;


    //Constructor
    public ReportTwo(int appointmentID, int customerID, String type, String title, String contact, String description, Timestamp start, Timestamp end) {
        this.setType(type);
        this.setDescription(description);
        this.setTitle(title);
        this.setContact(contact);
        this.setCustomerID(customerID);
        this.setAppointmentID(appointmentID);
        this.setEnd(end);
        this.setStart(start);
    }

    //Getter methods
    public int getCustomerID() {
        return this.customerID;
    }

    public int getAppointmentID() {
        return this.appointmentID;
    }

    public String getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getContact() {
        return this.contact;
    }

    public Timestamp getStart() {
        return this.start;
    }

    public Timestamp getEnd() {
        return this.end;
    }


    //Setter methods
    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }
}
