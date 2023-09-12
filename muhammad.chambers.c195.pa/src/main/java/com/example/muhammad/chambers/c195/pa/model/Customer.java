package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.helper.CountryAndState;
import com.example.muhammad.chambers.c195.pa.helper.InputValidation;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.sql.Timestamp;

public class Customer {
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int customerID;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int stateID;

    /*
        Below are extra variables I added to be able to get the State/Province name & the Country name,
        which will be used to populate the Customer Records table columns for Country & State name.
     */
    private String stateName;
    private String countryName;


    //Constructor
    public Customer(String customerName, String address, String postalCode, String phone, int stateID) {
        this.setCustomerName(customerName);
        this.setAddress(address);
        this.setPostalCode(postalCode);
        this.setPhone(phone);
        this.setStateID(stateID);
    }

    //Getter methods
    public int getCustomerID() {
        return this.customerID;
    }

    public int getStateID() {
        return this.stateID;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getAddress() {
        return this.address;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public String getPhone() {
        return this.phone;
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

    public String getStateName() throws SQLException {
        setStateName();
        return this.stateName;
    }

    public String getCountryName() throws SQLException {
        setCountryName();
        return this.countryName;
    }


    //Setter methods
    public void setCustomerName(String name) {
        if(InputValidation.isInputLengthValidStr(name, InputValidation.MAX_LENGTH_50)) {
            this.customerName = name;
        }
    }

    public void setAddress(String address) {
        if(InputValidation.isInputLengthValidStr(address, InputValidation.MAX_LENGTH_100)) {
            this.address = address;
        }
    }

    public void setPhone(String phoneNumber) {
        if(InputValidation.isInputLengthValidStr(phoneNumber, InputValidation.MAX_LENGTH_50)) {
            this.phone = phoneNumber;
        }
    }

    public void setPostalCode(String postalCode) {
        if(InputValidation.isInputLengthValidStr(postalCode, InputValidation.MAX_LENGTH_50)) {
            this.postalCode = postalCode;
        }
    }

    public void setCustomerID(int customerID) {
        if(InputValidation.isInputLengthValidInt(customerID, InputValidation.MAX_LENGTH_10)) {
            this.customerID = customerID;
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

    public void setStateID(int stateID) {
        if(InputValidation.isInputLengthValidInt(stateID, InputValidation.MAX_LENGTH_10)) {
            this.stateID = stateID;
        }
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    private void setStateName() throws SQLException {
        if(this.getStateID() <= InputValidation.DOES_NOT_EXIST_IN_DATABASE) {
            this.stateName = InputValidation.NOT_FOUND;
            return;
        }
        this.stateName = StateOrProvince.getStateName(this.getStateID());
    }

    private void setCountryName() throws SQLException {
        if(this.getStateID() <= InputValidation.DOES_NOT_EXIST_IN_DATABASE) {
            this.countryName = InputValidation.NOT_FOUND;
            return;
        }
        this.countryName = CountryAndState.getCountryNameForState(this.getStateID());
    }

    public static Customer createCustomerObjectFromTextFieldsAndComboBox(TextField customerName, TextField address, TextField postalCode, TextField phoneNumber, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, ComboBox<StateOrProvince> stateID) {
        Customer customer = new Customer(customerName.getText(), address.getText(), postalCode.getText(), phoneNumber.getText(), stateID.getValue().getStateID());

        customer.setCreateDate(createDate);
        customer.setCreatedBy(createdBy);
        customer.setLastUpdate(lastUpdate);
        customer.setLastUpdatedBy(lastUpdatedBy);

        return customer;
    }

    public static boolean areSame(Customer customer1, Customer customer2) {
        if(customer1.getCustomerName().equals(customer2.getCustomerName()) && customer1.getAddress().equals(customer2.getAddress()) && customer1.getPostalCode().equals(customer2.getPostalCode()) && customer1.getStateID() == customer2.getStateID()) {
            return true;
        }
        return false;
    }
}