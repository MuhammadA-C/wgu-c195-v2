package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.helper.InputValidation;

import java.sql.Timestamp;

/** This class is used to create a Country object.*/
public class Country {
    /** Holds the countries name*/
    private String country;
    /** Holds the countries id*/
    private int countryID;
    /** Holds the date and time the object was created*/
    private Timestamp createDate;
    /** Holds who created the object*/
    private String createdBy;
    /** Holds the date and time the object was last updated*/
    private Timestamp lastUpdate;
    /** Holds who created last updated the object*/
    private String lastUpdatedBy;


    /** This is the Constructor for the Country class.
     This Constructor sets all the fields for the Country object.
     @param countryID the countryID to set
     @param country the country to set
     @param createDate the createDate to set
     @param createdBy the createdBy to set
     @param lastUpdate the lastUpdate to set
     @param lastUpdatedBy the lastUpdatedBy to set*/
    public Country(int countryID, String country, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.setCountryID(countryID);
        this.setCountry(country);
        this.setCreateDate(createDate);
        this.setCreatedBy(createdBy);
        this.setLastUpdate(lastUpdate);
        this.setLastUpdatedBy(lastUpdatedBy);
    }


    /** This is the getCountryID method.
     This method returns the countryID field.
     @return Returns the countryID*/
    public int getCountryID() {
        return this.countryID;
    }

    /** This is the getCountry method.
     This method returns the country field.
     @return Returns the country*/
    public String getCountry() {
        return this.country;
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

    /** This is the setCountryID method.
     This method sets the countryID field.
     @param countryID the countryID to set*/
    public void setCountryID(int countryID) {
        if(InputValidation.isInputLengthValidInt(countryID, InputValidation.MAX_LENGTH_10)) {
            this.countryID = countryID;
        }
    }

    /** This is the setCountry method.
     This method sets the country field.
     @param country the country to set*/
    public void setCountry(String country) {
        if(InputValidation.isInputLengthValidStr(country, InputValidation.MAX_LENGTH_50)) {
            this.country = country;
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

    /** This is the toString method.
     This method formats how this object will be printed.
     @return Returns the country as a string*/
    @Override
    public String toString() {
        /*
            Had to override the toString method to print out the object how I want to,
            in this case print out the objects name
         */
        return this.getCountry();
    }
}