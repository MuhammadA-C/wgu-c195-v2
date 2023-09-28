package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.dao.StateOrProvinceDAOImpl;
import com.example.muhammad.chambers.c195.pa.helper.InputValidation;

import java.sql.SQLException;
import java.sql.Timestamp;

/** This class is used to create StateOrProvince objects, also referred to as First Level Division*/
public class StateOrProvince {
    /** Holds the stateID*/
    private int stateID;
    /** Holds the state name*/
    private String state;
    /** Holds the date and time the object was created*/
    private Timestamp createDate;
    /** Holds who created the object*/
    private String createdBy;
    /** Holds the date and time the last change was made to the object*/
    private Timestamp lastUpdate;
    /** Holds who made the last change to the object*/
    private String lastUpdatedBy;
    /** Holds the countryID associated to the state*/
    private int countryID;


    /** This is the Constructor for the StateOrProvince class.
     This Constructor sets all the fields for the StateOrProvince object.
     @param stateID the stateID to set
     @param state the state to set
     @param createDate the createDate to set
     @param createdBy the createdBy to set
     @param lastUpdate the lastUpdate to set
     @param lastUpdatedBy the lastUpdatedBy to set
     @param countryID the countryID to set*/
    public StateOrProvince(int stateID, String state, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int countryID) {
        this.setStateID(stateID);
        this.setState(state);
        this.setCreateDate(createDate);
        this.setCreatedBy(createdBy);
        this.setLastUpdate(lastUpdate);
        this.setLastUpdatedBy(lastUpdatedBy);
        this.setCountryID(countryID);
    }


    /** This is the getStateID method.
     This method returns the stateID field.
     @return Returns the stateID*/
    public int getStateID() {
        return this.stateID;
    }

    /** This is the getCountryID method.
     This method returns the countryID field.
     @return Returns the countryID*/
    public int getCountryID() {
        return this.countryID;
    }

    /** This is the getState method.
     This method returns the state field.
     @return Returns the state*/
    public String getState() {
        return this.state;
    }

    /** This is the getCreatedBy method.
     This method returns the createdBy field.
     @return Returns the createdBy*/
    public String getCreatedBy() {
        return this.createdBy;
    }

    /** This is the getLastUpdatedBy method.
     This method returns the lastUpdatedBy field.
     @return Returns the lastUpdatedBy*/
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
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

    /** This is the setStateID method.
     This method sets the stateID field.
     @param stateID the stateID to set*/
    public void setStateID(int stateID) {
        if(InputValidation.isInputLengthValidInt(stateID, InputValidation.MAX_LENGTH_10)) {
            this.stateID = stateID;
        }
    }

    /** This is the setCountryID method.
     This method sets the countryID field.
     @param countryID the countryID to set*/
    public void setCountryID(int countryID) {
        if(InputValidation.isInputLengthValidInt(countryID, InputValidation.MAX_LENGTH_10)) {
            this.countryID = countryID;
        }
    }

    /** This is the setState method.
     This method sets the state field.
     @param state the state to set*/
    public void setState(String state) {
        if(InputValidation.isInputLengthValidStr(state, InputValidation.MAX_LENGTH_50)) {
            this.state = state;
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

    /** This is the getStateName method.
     This method takes a stateID, integer, as input and outputs the state name, string.
     @param stateID the integer stateID
     @return Returns a state as a string
     @throws SQLException due to the SQL queries*/
    public static String getStateName(int stateID) throws SQLException {
        for(StateOrProvince i : StateOrProvinceDAOImpl.getStatesList()) {
            if(i.getStateID() == stateID) {
                return i.getState();
            }
        }
        return InputValidation.NOT_FOUND;
    }

    /** This is the toString method.
     This method formats how this object will be printed.
     @return Returns the state as a string*/
    @Override
    public String toString() {
        /*
            Had to override the toString method to print out the object how I want to,
            in this case print out the objects name
         */
        return this.getState();
    }
}