package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.dao.StateOrProvinceDAOImpl;
import com.example.muhammad.chambers.c195.pa.helper.InputValidation;

import java.sql.SQLException;
import java.sql.Timestamp;

public class StateOrProvince {
    private int stateID;
    private String state;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int countryID;


    //Constructor
    public StateOrProvince(int stateID, String state, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int countryID) {
        this.setStateID(stateID);
        this.setState(state);
        this.setCreateDate(createDate);
        this.setCreatedBy(createdBy);
        this.setLastUpdate(lastUpdate);
        this.setLastUpdatedBy(lastUpdatedBy);
        this.setCountryID(countryID);
    }


    //Getter methods
    public int getStateID() {
        return this.stateID;
    }

    public int getCountryID() {
        return this.countryID;
    }

    public String getState() {
        return this.state;
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
    public void setStateID(int stateID) {
        if(InputValidation.isInputLengthValidInt(stateID, InputValidation.MAX_LENGTH_10)) {
            this.stateID = stateID;
        }
    }

    public void setCountryID(int countryID) {
        if(InputValidation.isInputLengthValidInt(countryID, InputValidation.MAX_LENGTH_10)) {
            this.countryID = countryID;
        }
    }

    public void setState(String state) {
        if(InputValidation.isInputLengthValidStr(state, InputValidation.MAX_LENGTH_50)) {
            this.state = state;
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

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public static String getStateName(int stateID) throws SQLException {
        for(StateOrProvince i : StateOrProvinceDAOImpl.getStatesList()) {
            if(i.getStateID() == stateID) {
                return i.getState();
            }
        }
        return InputValidation.NOT_FOUND;
    }

    @Override
    public String toString() {
        /*
            Had to override the toString method to print out the object how I want to,
            in this case print out the objects name
         */
        return this.getState();
    }
}