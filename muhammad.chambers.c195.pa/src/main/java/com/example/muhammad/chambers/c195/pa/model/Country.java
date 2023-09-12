package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.helper.InputValidation;

import java.sql.Timestamp;

public class Country {
    private String country;
    private int countryID;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;


    //Constructor
    public Country(int countryID, String country, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.setCountryID(countryID);
        this.setCountry(country);
        this.setCreateDate(createDate);
        this.setCreatedBy(createdBy);
        this.setLastUpdate(lastUpdate);
        this.setLastUpdatedBy(lastUpdatedBy);
    }


    //Getter methods
    public int getCountryID() {
        return this.countryID;
    }

    public String getCountry() {
        return this.country;
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
    public void setCountryID(int countryID) {
        if(InputValidation.isInputLengthValidInt(countryID, InputValidation.MAX_LENGTH_10)) {
            this.countryID = countryID;
        }
    }

    public void setCountry(String country) {
        if(InputValidation.isInputLengthValidStr(country, InputValidation.MAX_LENGTH_50)) {
            this.country = country;
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

    @Override
    public String toString() {
        /*
            Had to override the toString method to print out the object how I want to,
            in this case print out the objects name
         */
        return this.getCountry();
    }
}