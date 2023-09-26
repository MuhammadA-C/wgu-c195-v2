package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.helper.CountryAndState;
import com.example.muhammad.chambers.c195.pa.helper.InputValidation;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.sql.Timestamp;

/** This class is used to create a Customer object.*/
public class Customer {
    /** Holds the customer name*/
    private String customerName;
    /** Holds the customers address*/
    private String address;
    /** Holds the customers postal code*/
    private String postalCode;
    /** Holds the customers phone number*/
    private String phone;
    /** Holds the customerID*/
    private int customerID;
    /** Holds the date and time the object was created*/
    private Timestamp createDate;
    /** Holds who created the object*/
    private String createdBy;
    /** Holds the date and time the object was last updated*/
    private Timestamp lastUpdate;
    /** Holds who created last updated the object*/
    private String lastUpdatedBy;
    /** Holds the stateID*/
    private int stateID;

    /*
        Below are extra variables I added to be able to get the State/Province name & the Country name,
        which will be used to populate the Customer Records table columns for Country & State name.
     */
    /** Holds the stateName*/
    private String stateName;
    /** Holds the countryName*/
    private String countryName;


    /** This is the Constructor for the Customer class.
     This Constructor sets all the fields for the Customer object.
     @param customerName the customerName to set
     @param address the address to set
     @param postalCode the postalCode to set
     @param phone the phone to set
     @param stateID the stateID to set*/
    public Customer(String customerName, String address, String postalCode, String phone, int stateID) {
        this.setCustomerName(customerName);
        this.setAddress(address);
        this.setPostalCode(postalCode);
        this.setPhone(phone);
        this.setStateID(stateID);
    }


    /** This is the getCustomerID method.
     This method returns the customerID field.
     @return Returns the customerID*/
    public int getCustomerID() {
        return this.customerID;
    }

    /** This is the getStateID method.
     This method returns the stateID field.
     @return Returns the stateID*/
    public int getStateID() {
        return this.stateID;
    }

    /** This is the getCustomerName method.
     This method returns the customerName field.
     @return Returns the customerName*/
    public String getCustomerName() {
        return this.customerName;
    }

    /** This is the getPostalCode method.
     This method returns the postalCode field.
     @return Returns the postalCode*/
    public String getPostalCode() {
        return this.postalCode;
    }

    /** This is the getAddress method.
     This method returns the address field.
     @return Returns the address*/
    public String getAddress() {
        return this.address;
    }

    /** This is the getLastUpdate method.
     This method returns the lastUpdate field.
     @return Returns the lastUpdate*/
    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    /** This is the getPhone method.
     This method returns the phone field.
     @return Returns the phone*/
    public String getPhone() {
        return this.phone;
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

    /** This is the getStateName method.
     This method returns the stateName field.
     @return Returns the stateName*/
    public String getStateName() throws SQLException {
        setStateName();
        return this.stateName;
    }

    /** This is the getCountryName method.
     This method returns the countryName field.
     @return Returns the countryName*/
    public String getCountryName() throws SQLException {
        setCountryName();
        return this.countryName;
    }

    /** This is the setName method.
     This method sets the name field.
     @param name the name to set*/
    public void setCustomerName(String name) {
        if(InputValidation.isInputLengthValidStr(name, InputValidation.MAX_LENGTH_50)) {
            this.customerName = name;
        }
    }

    /** This is the setAddress method.
     This method sets the address field.
     @param address the address to set*/
    public void setAddress(String address) {
        if(InputValidation.isInputLengthValidStr(address, InputValidation.MAX_LENGTH_100)) {
            this.address = address;
        }
    }

    /** This is the setPhone method.
     This method sets the phone field.
     @param phoneNumber the phone to set*/
    public void setPhone(String phoneNumber) {
        if(InputValidation.isInputLengthValidStr(phoneNumber, InputValidation.MAX_LENGTH_50)) {
            this.phone = phoneNumber;
        }
    }

    /** This is the setPostalCode method.
     This method sets the postalCode field.
     @param postalCode the postalCode to set*/
    public void setPostalCode(String postalCode) {
        if(InputValidation.isInputLengthValidStr(postalCode, InputValidation.MAX_LENGTH_50)) {
            this.postalCode = postalCode;
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

    /** This is the setStateID method.
     This method sets the stateID field.
     @param stateID the stateID to set*/
    public void setStateID(int stateID) {
        if(InputValidation.isInputLengthValidInt(stateID, InputValidation.MAX_LENGTH_10)) {
            this.stateID = stateID;
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

    /** This is the setStateName method.
     This method sets the stateName field by looking up the state name associated with the stateID.*/
    private void setStateName() throws SQLException {
        if(this.getStateID() <= InputValidation.DOES_NOT_EXIST_IN_DATABASE) {
            this.stateName = InputValidation.NOT_FOUND;
            return;
        }
        this.stateName = StateOrProvince.getStateName(this.getStateID());
    }

    /** This is the setCountryName method.
     This method sets the countryName field by looking up the country name associated with the countryID.*/
    private void setCountryName() throws SQLException {
        if(this.getStateID() <= InputValidation.DOES_NOT_EXIST_IN_DATABASE) {
            this.countryName = InputValidation.NOT_FOUND;
            return;
        }
        this.countryName = CountryAndState.getCountryNameForState(this.getStateID());
    }

    /** This is the createCustomerObjectFromTextFieldsAndComboBox method.
     This method is used to create a customer object from the input form values.
     @param customerName the customerName to set
     @param address the address to set
     @param postalCode the postalCode to set
     @param phoneNumber the phoneNumber to set
     @param createDate the createDate to set
     @param createdBy the createdBy to set
     @param lastUpdate the lastUpdate to set
     @param lastUpdatedBy the lastUpdatedBy to set
     @param stateID the stateID to set
     @return Returns new customer object*/
    public static Customer createCustomerObjectFromTextFieldsAndComboBox(TextField customerName, TextField address, TextField postalCode, TextField phoneNumber, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, ComboBox<StateOrProvince> stateID) {
        Customer customer = new Customer(customerName.getText(), address.getText(), postalCode.getText(), phoneNumber.getText(), stateID.getValue().getStateID());

        customer.setCreateDate(createDate);
        customer.setCreatedBy(createdBy);
        customer.setLastUpdate(lastUpdate);
        customer.setLastUpdatedBy(lastUpdatedBy);

        return customer;
    }

    /** This is the areSame method.
     This method is used to check if two customer objects are the same.
     @param customer1 the customer1 to compare
     @param customer2 the customer2 to compare to
     @return Returns a boolean, true if they are the same and false if they are NOT*/
    public static boolean areSame(Customer customer1, Customer customer2) {
        if(customer1.getCustomerName().equals(customer2.getCustomerName()) && customer1.getAddress().equals(customer2.getAddress()) && customer1.getPostalCode().equals(customer2.getPostalCode()) && customer1.getStateID() == customer2.getStateID()) {
            return true;
        }
        return false;
    }
}