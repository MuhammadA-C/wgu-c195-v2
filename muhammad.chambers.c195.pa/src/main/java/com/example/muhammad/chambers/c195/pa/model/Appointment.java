package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.dao.ContactDAOImpl;
import com.example.muhammad.chambers.c195.pa.helper.InputValidation;

import java.sql.SQLException;
import java.sql.Timestamp;

/** This class is used to create an Appointment object.*/
public class Appointment {
    /** Holds the appointment id*/
    private int appointmentID;
    /** Holds the appointment title*/
    private String title;
    /** Holds the appointment description*/
    private String description;
    /** Holds the appointment location*/
    private String location;
    /** Holds the appointment type*/
    private String type;
    /** Holds the appointment start date and time*/
    private Timestamp start;
    /** Holds the appointment end date and time*/
    private Timestamp end;
    /** Holds the date and time the appointment was created*/
    private Timestamp createDate;
    /** Holds who created the appointment*/
    private String createdBy;
    /** Holds the date and time the appointment was last updated*/
    private Timestamp lastUpdate;
    /** Holds who last updated the appointment*/
    private String lastUpdatedBy;
    /** Holds the customer id associated with the appointment*/
    private int customerID;
    /** Holds the user id*/
    private int userID;
    /** Holds the contact id*/
    private int contactID;

    //Extra field I created for the table views
    /** Holds the contact name*/
    private String contactName;
    /** Holds the start date and time as a string*/
    private String startStr;
    /** Holds the end date and time as a string*/
    private String endStr;


    /** This is the Constructor for the Appointment class.
     This Constructor sets all the fields for the Appointment object.
     @param title the title to set
     @param description the description to set
     @param location the location to set
     @param type the type to set
     @param start the start to set
     @param end the end to set
     @param contactID the contactID to set
     @param customerID the customerID to set
     @param userID the userID to set*/
    public Appointment(String title, String description, String location, String type, Timestamp start, Timestamp end, int contactID, int customerID, int userID) {
        this.setTitle(title);
        this.setDescription(description);
        this.setLocation(location);
        this.setType(type);
        this.setStart(start);
        this.setEnd(end);
        this.setContactID(contactID);
        this.setCustomerID(customerID);
        this.setUserID(userID);
    }


    /** This is the getAppointmentID method.
     This method returns the appointmentID field.
     @return Returns the appointmentID*/
    public int getAppointmentID() {
        return this.appointmentID;
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

    /** This is the getLocation method.
     This method returns the location field.
     @return Returns the location*/
    public String getLocation() {
        return this.location;
    }

    /** This is the getType method.
     This method returns the type field.
     @return Returns the type*/
    public String getType() {
        return this.type;
    }

    /** This is the getLastUpdatedBy method.
     This method returns the lastUpdatedBy field.
     @return Returns the lastUpdatedBy*/
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    /** This is the getContactID method.
     This method returns the contactID field.
     @return Returns the contactID*/
    public int getContactID() {
        return this.contactID;
    }

    /** This is the getCustomerID method.
     This method returns the customerID field.
     @return Returns the customerID*/
    public int getCustomerID() {
        return this.customerID;
    }

    /** This is the getUserID method.
     This method returns the userID field.
     @return Returns the userID*/
    public int getUserID() {
        return this.userID;
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

    /** This is the getCreateDate method.
     This method returns the createDate field.
     @return Returns the createDate*/
    public Timestamp getCreateDate() {
        return this.createDate;
    }

    /** This is the getLastUpdate method.
     This method returns the lastUpdate field.
     @return Returns the lastUpdate*/
    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    /** This is the getCreatedBy method.
     This method returns the createdBy field.
     @return Returns the createdBy*/
    public String getCreatedBy() {
        return this.createdBy;
    }

    /** This is the getContactName method.
     This method returns the contactName field.
     @return Returns the contactName
     @throws SQLException due to using SQL for database queries*/
    public String getContactName() throws SQLException {
        this.setContactName();
        return this.contactName;
    }

    /** This is the getStartStr method.
     This method returns the startStr field.
     @return Returns the startStr*/
    public String getStartStr() {
        setStartStr();
        return this.startStr;
    }

    /** This is the getEndStr method.
     This method returns the endStr field.
     @return Returns the endStr*/
    public String getEndStr() {
        setEndStr();
        return this.endStr;
    }

    /** This is the setAppointmentID method.
     This method sets the appointmentID field.
     @param appointmentID the appointmentID to set*/
    public void setAppointmentID(int appointmentID) {
        if(InputValidation.isInputLengthValidInt(appointmentID, InputValidation.MAX_LENGTH_10)) {
            this.appointmentID = appointmentID;
        }
    }

    /** This is the setTitle method.
     This method sets the title field.
     @param title the title to set*/
    public void setTitle(String title) {
        if(InputValidation.isInputLengthValidStr(title, InputValidation.MAX_LENGTH_50)) {
            this.title = title;
        }
    }

    /** This is the setDescription method.
     This method sets the description field.
     @param description the description to set*/
    public void setDescription(String description) {
        if(InputValidation.isInputLengthValidStr(description, InputValidation.MAX_LENGTH_50)) {
            this.description = description;
        }
    }

    /** This is the setLocation method.
     This method sets the location field.
     @param location the location to set*/
    public void setLocation(String location) {
        if(InputValidation.isInputLengthValidStr(location, InputValidation.MAX_LENGTH_50)) {
            this.location = location;
        }
    }

    /** This is the setType method.
     This method sets the type field.
     @param type the type to set*/
    public void setType(String type) {
        if(InputValidation.isInputLengthValidStr(type, InputValidation.MAX_LENGTH_50)) {
            this.type = type;
        }
    }

    /** This is the setCreatedBy method.
     This method sets the createdBy field.
     @param createdBy the createdBy to set*/
    public void setCreatedBy(String createdBy) {
        if(InputValidation.isInputLengthValidStr(createdBy, InputValidation.MAX_LENGTH_50)) {
            this.createdBy = createdBy;
        }
    }

    /** This is the setLastUpdatedBy method.
     This method sets the lastUpdatedBy field.
     @param lastUpdatedBy the lastUpdatedBy to set*/
    public void setLastUpdatedBy(String lastUpdatedBy) {
        if(InputValidation.isInputLengthValidStr(lastUpdatedBy, InputValidation.MAX_LENGTH_50)) {
            this.lastUpdatedBy = lastUpdatedBy;
        }
    }

    /** This is the setCustomerID method.
     This method sets the customerID field.
     @param customerID the customerID to set*/
    public void setCustomerID(int customerID) {
        if(InputValidation.isInputLengthValidInt(customerID, InputValidation.MAX_LENGTH_10)) {
            this.customerID = customerID;
        }
    }

    /** This is the setUserID method.
     This method sets the userID field.
     @param userID the userID to set*/
    public void setUserID(int userID) {
        if (InputValidation.isInputLengthValidInt(userID, InputValidation.MAX_LENGTH_10)) {
            this.userID = userID;
        }
    }

    /** This is the setContactID method.
     This method sets the contactID field.
     @param contactID the contactID to set*/
    public void setContactID(int contactID) {
        if(InputValidation.isInputLengthValidInt(contactID, InputValidation.MAX_LENGTH_10)) {
            this.contactID = contactID;
        }
    }

    /** This is the setStart method.
     This method sets the start field.
     @param start the start to set*/
    public void setStart(Timestamp start) {
        this.start = start;
    }

    /** This is the setEnd method.
     This method sets the end field.
     @param end the end to set*/
    public void setEnd(Timestamp end) {
        this.end = end;
    }

    /** This is the setLastUpdate method.
     This method sets the lastUpdate field.
     @param lastUpdate the lastUpdate to set*/
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /** This is the setCreateDate method.
     This method sets the createDate field.
     @param createDate the createDate to set*/
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /** This is the setContactName method.
     This method sets the contactName field by looking up the contactID and getting the contact name.
     @throws SQLException due to using SQL for database queries*/
    public void setContactName() throws SQLException {
        if(this.getContactID() <= InputValidation.DOES_NOT_EXIST_IN_DATABASE) {
            this.contactName = InputValidation.NOT_FOUND;
            return;
        }
        this.contactName = ContactDAOImpl.findContactInListByID(this.contactID);
    }

    /** This is the setStartStr method.
     This method converts the start field from a timestamp to a string and sets the startStr field with the string value*/
    public void setStartStr() {
        String start = getStart().toString();
        this.startStr = start.substring(0, (start.length() - 5));
    }

    /** This is the setEndStr method.
     This method converts the end field from a timestamp to a string and sets the endStr field with the string value*/
    public void setEndStr() {
        String end = getEnd().toString();
        this.endStr = end.substring(0, (end.length() - 5));
    }
}
