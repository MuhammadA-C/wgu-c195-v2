package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.helper.InputValidation;

import java.sql.Timestamp;

/** This class is used to create User objects*/
public class User {
    /** Holds the userID*/
    private int userID;
    /** Holds the userName*/
    private String userName;
    /** Holds the password for the user account*/
    private String password;
    /** Holds the date and time the user account was created*/
    private Timestamp createDate;
    /** Holds the data for who created the user account*/
    private String createdBy;
    /** Holds the date and time the user account had any changes made to it*/
    private Timestamp lastUpdate;
    /** Holds the data for who last updated the user account*/
    private String lastUpdatedBy;


    /** This is the Constructor for the User class.
     This Constructor sets all the fields for the User object.
     @param userID the userID to set
     @param userName the userName to set
     @param password the password to set
     @param createDate the createDate to set
     @param createdBy the createdBy to set
     @param lastUpdate the lastUpdate to set
     @param lastUpdatedBy the lastUpdatedBy to set*/
    public User(int userID, String userName, String password, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.setUserID(userID);
        this.setUserName(userName);
        this.setPassword(password);
        this.setCreateDate(createDate);
        this.setCreatedBy(createdBy);
        this.setLastUpdate(lastUpdate);
        this.setLastUpdatedBy(lastUpdatedBy);
    }

    /** This is the getUserName method.
     This method returns the userName field.
     @return Returns the userName*/
    public String getUserName() {
        return this.userName;
    }

    /** This is the getPassword method.
     This method returns the password field.
     @return Returns the userName*/
    public String getPassword() {
        return this.password;
    }

    /** This is the getUserID method.
     This method returns the password field.
     @return Returns the userID*/
    public int getUserID() {
        return this.userID;
    }

    /** This is the getCreatedBy method.
     This method returns the password field.
     @return Returns the createdBy*/
    public String getCreatedBy() {
        return this.createdBy;
    }

    /** This is the getLastUpdatedBy method.
     This method returns the password field.
     @return Returns the lastUpdatedBy*/
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    /** This is the getCreateDate method.
     This method returns the password field.
     @return Returns the createDate*/
    public Timestamp getCreateDate() {
        return this.createDate;
    }

    /** This is the getLastUpdate method.
     This method returns the password field.
     @return Returns the lastUpdate*/
    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    /** This is the setUserID method.
     This method sets the userID field.
     @param userID the userID to set*/
    public void setUserID(int userID) {
        if(InputValidation.isInputLengthValidInt(userID, InputValidation.MAX_LENGTH_10)) {
            this.userID = userID;
        }
    }

    /** This is the setUserName method.
     This method sets the userName field.
     @param userName the userName to set*/
    public void setUserName(String userName) {
        if(InputValidation.isInputLengthValidStr(userName, InputValidation.MAX_LENGTH_50)) {
            this.userName = userName;
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

    /** This is the setCreatedBy method.
     This method sets the createdBy field.
     @param createdBy the createdBy to set*/
    public void setCreatedBy(String createdBy) {
        if(InputValidation.isInputLengthValidStr(createdBy, InputValidation.MAX_LENGTH_50)) {
            this.createdBy = createdBy;
        }
    }

    /** This is the setPassword method.
     This method sets the password field.
     @param password the password to set*/
    public void setPassword(String password) {
        this.password = password;
    }

    /** This is the setCreateDate method.
     This method sets the createDate field.
     @param createDate the createDate to set*/
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /** This is the setLastUpdate method.
     This method sets the lastUpdate field.
     @param lastUpdate the lastUpdate to set*/
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}