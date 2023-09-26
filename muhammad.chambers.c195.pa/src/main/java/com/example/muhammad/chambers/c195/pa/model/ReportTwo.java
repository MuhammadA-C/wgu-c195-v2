package com.example.muhammad.chambers.c195.pa.model;

import java.sql.Timestamp;

/** This class is used to create a ReportTwo object.*/
public class ReportTwo {
    /** Holds the appointmentID*/
    private int appointmentID;
    /** Holds the customerID*/
    private int customerID;
    /** Holds the type of appointment*/
    private String type;
    /** Holds the appointment contact*/
    private String contact;
    /** Holds the appointment description*/
    private String description;
    /** Holds the appointment title*/
    private String title;
    /** Holds the appointment start date and time*/
    private Timestamp start;
    /** Holds the appointment end date and time*/
    private Timestamp end;


    /** This is the Constructor for the ReportTwo class.
     This Constructor sets all the fields for the ReportTwo object.
     @param appointmentID the appointmentID to set
     @param customerID the customerID to set
     @param type the type to set
     @param title the title to set
     @param contact the contact to set
     @param description the description to set
     @param start the start to set
     @param end the end to set*/
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


    /** This is the getCustomerID method.
     This method returns the customerID field.
     @return Returns the customerID*/
    public int getCustomerID() {
        return this.customerID;
    }

    /** This is the getAppointmentID method.
     This method returns the appointmentID field.
     @return Returns the appointmentID*/
    public int getAppointmentID() {
        return this.appointmentID;
    }

    /** This is the getType method.
     This method returns the type field.
     @return Returns the type*/
    public String getType() {
        return this.type;
    }

    /** This is the getTitle method.
     This method returns the title field.
     @return Returns the title*/
    public String getTitle() {
        return this.title;
    }

    /** This is the getDescription method.
     This method returns the description field.
     @return Returns the description*/
    public String getDescription() {
        return this.description;
    }

    /** This is the getContact method.
     This method returns the contact field.
     @return Returns the contact*/
    public String getContact() {
        return this.contact;
    }

    /** This is the getStart method.
     This method returns the start field.
     @return Returns the start*/
    public Timestamp getStart() {
        return this.start;
    }

    /** This is the getEnd method.
     This method returns the end field.
     @return Returns the end*/
    public Timestamp getEnd() {
        return this.end;
    }

    /** This is the setType method.
     This method sets the type field.
     @param type the type to set*/
    public void setType(String type) {
        this.type = type;
    }

    /** This is the setDescription method.
     This method sets the description field.
     @param description the description to set*/
    public void setDescription(String description) {
        this.description = description;
    }

    /** This is the setContact method.
     This method sets the contact field.
     @param contact the contact to set*/
    public void setContact(String contact) {
        this.contact = contact;
    }

    /** This is the setTitle method.
     This method sets the title field.
     @param title the title to set*/
    public void setTitle(String title) {
        this.title = title;
    }

    /** This is the setCustomerID method.
     This method sets the customerID field.
     @param customerID the customerID to set*/
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /** This is the setAppointmentID method.
     This method sets the appointmentID field.
     @param appointmentID the appointmentID to set*/
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /** This is the setEnd method.
     This method sets the end field.
     @param end the end to set*/
    public void setEnd(Timestamp end) {
        this.end = end;
    }

    /** This is the setStart method.
     This method sets the start field.
     @param start the start to set*/
    public void setStart(Timestamp start) {
        this.start = start;
    }
}
