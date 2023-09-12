package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.helper.InputValidation;

import java.sql.Timestamp;


public class User {
    private int userID;
    private String userName;
    private String password;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;


    //Constructor
    public User(int userID, String userName, String password, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.setUserID(userID);
        this.setUserName(userName);
        this.setPassword(password);
        this.setCreateDate(createDate);
        this.setCreatedBy(createdBy);
        this.setLastUpdate(lastUpdate);
        this.setLastUpdatedBy(lastUpdatedBy);
    }

    //Getter methods
    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public int getUserID() {
        return this.userID;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }


    //Setter methods
    public void setUserID(int userID) {
        if(InputValidation.isInputLengthValidInt(userID, InputValidation.MAX_LENGTH_10)) {
            this.userID = userID;
        }
    }

    public void setUserName(String userName) {
        if(InputValidation.isInputLengthValidStr(userName, InputValidation.MAX_LENGTH_50)) {
            this.userName = userName;
        }
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        if(InputValidation.isInputLengthValidStr(lastUpdatedBy, InputValidation.MAX_LENGTH_50)) {
            this.lastUpdatedBy = lastUpdatedBy;
        }
    }

    public void setCreatedBy(String createdBy) {
        if(InputValidation.isInputLengthValidStr(createdBy, InputValidation.MAX_LENGTH_50)) {
            this.createdBy = createdBy;
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}