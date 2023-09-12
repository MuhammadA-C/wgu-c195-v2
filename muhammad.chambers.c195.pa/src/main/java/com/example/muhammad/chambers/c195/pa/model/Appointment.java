package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.dao.ContactDAOImpl;
import com.example.muhammad.chambers.c195.pa.helper.InputValidation;

import java.sql.SQLException;
import java.sql.Timestamp;

public class Appointment {
    /*
        Note: For the start and end date/time, I might need to change things around when adding it to the database
     */
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private Timestamp start;
    private Timestamp end;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;

    //Extra field I created to get the contact name
    private String contactName;
    private String startStr;
    private String endStr;


    //Constructor
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


    //Getter methods
    public int getAppointmentID() {
        return this.appointmentID;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLocation() {
        return this.location;
    }

    public String getType() {
        return this.type;
    }

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public int getContactID() {
        return this.contactID;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public int getUserID() {
        return this.userID;
    }

    public Timestamp getStart() {
        return this.start;
    }


    public Timestamp getEnd() {
        return this.end;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public String getContactName() throws SQLException {
        this.setContactName();
        return this.contactName;
    }

    public String getStartStr() {
        setStartStr();
        return this.startStr;
    }

    public String getEndStr() {
        setEndStr();
        return this.endStr;
    }

    //Setter methods
    public void setAppointmentID(int appointmentID) {
        if(InputValidation.isInputLengthValidInt(appointmentID, InputValidation.MAX_LENGTH_10)) {
            this.appointmentID = appointmentID;
        }
    }

    public void setTitle(String title) {
        if(InputValidation.isInputLengthValidStr(title, InputValidation.MAX_LENGTH_50)) {
            this.title = title;
        }
    }

    public void setDescription(String description) {
        if(InputValidation.isInputLengthValidStr(description, InputValidation.MAX_LENGTH_50)) {
            this.description = description;
        }
    }

    public void setLocation(String location) {
        if(InputValidation.isInputLengthValidStr(location, InputValidation.MAX_LENGTH_50)) {
            this.location = location;
        }
    }

    public void setType(String type) {
        if(InputValidation.isInputLengthValidStr(type, InputValidation.MAX_LENGTH_50)) {
            this.type = type;
        }
    }

    public void setCreatedBy(String createdBy) {
        if(InputValidation.isInputLengthValidStr(createdBy, InputValidation.MAX_LENGTH_50)) {
            this.createdBy = createdBy;
        }
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        if(InputValidation.isInputLengthValidStr(lastUpdatedBy, InputValidation.MAX_LENGTH_50)) {
            this.lastUpdatedBy = lastUpdatedBy;
        }
    }

    public void setCustomerID(int customerID) {
        if(InputValidation.isInputLengthValidInt(customerID, InputValidation.MAX_LENGTH_10)) {
            this.customerID = customerID;
        }
    }

    public void setUserID(int userID) {
        if (InputValidation.isInputLengthValidInt(userID, InputValidation.MAX_LENGTH_10)) {
            this.userID = userID;
        }
    }

    public void setContactID(int contactID) {
        if(InputValidation.isInputLengthValidInt(contactID, InputValidation.MAX_LENGTH_10)) {
            this.contactID = contactID;
        }
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setContactName() throws SQLException {
        if(this.getContactID() <= InputValidation.DOES_NOT_EXIST_IN_DATABASE) {
            this.contactName = InputValidation.NOT_FOUND;
            return;
        }
        this.contactName = ContactDAOImpl.findContactInListByID(this.contactID);
    }

    //Note: This needs to be fixed to convert the time to the users systems time zone; also want to add PM or AM
    public void setStartStr() {
        String start = getStart().toString();
        this.startStr = start.substring(0, (start.length() - 5));
    }

    //Note: This needs to be fixed to convert the time to the users systems time zone; also want to add PM or AM
    public void setEndStr() {
        String end = getEnd().toString();
        this.endStr = end.substring(0, (end.length() - 5));
    }

}
