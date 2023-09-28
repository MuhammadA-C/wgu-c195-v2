package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.CountryDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.CustomerDAOImpl;
import com.example.muhammad.chambers.c195.pa.helper.*;
import com.example.muhammad.chambers.c195.pa.model.Country;
import com.example.muhammad.chambers.c195.pa.model.Customer;
import com.example.muhammad.chambers.c195.pa.model.StateOrProvince;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/** This class holds the code for the add customer fxml file*/
public class AddCustomerController implements Initializable {
    /** Holds a reference to the file path class*/
    private FilePath filePath = new FilePath();

    /** Holds the country combo box*/
    @FXML
    ComboBox<Country> countryComboBox;
    /** Holds the state combo box*/
    @FXML
    ComboBox<StateOrProvince> stateComboBox;
    /** Holds the customer name text field*/
    @FXML
    TextField customerNameTxtField;
    /** Holds the address text field*/
    @FXML
    TextField addressTxtField;
    /** Holds the phone number text field*/
    @FXML
    TextField phoneNumberTxtField;
    /** Holds the postal code text field*/
    @FXML
    TextField postalCodeTxtField;


    /** This is the doesCustomerExist method.
     This method is used to check if the customer to be added already exists in the database.
     @param addCustomer the customer to add
     @throws SQLException due to using SQL for database queries*/
    private boolean doesCustomerExist(Customer addCustomer) throws SQLException {
        for(int i = 0; i < CustomerDAOImpl.getCustomersList().size(); i++) {
            ObservableList<Customer> customers = CustomerDAOImpl.getCustomersList();

            if(Customer.areSame(addCustomer, customers.get(i))) {
                return true;
            }
        }
        return false;
    }

    /** This is the setCountryComboBox method.
     This method is used to set the selection values for the country combo box with the values from the database.
     @throws SQLException due to using SQL for database queries*/
    private void setCountryComboBox() throws SQLException {
        countryComboBox.setItems(CountryDAOImpl.getCountriesList());
    }

    /** This is the setStateComboBox method.
     This method is used to set the selection values for the state combo box with the values from the database.
     @throws SQLException due to using SQL for database queries*/
    private void setStateComboBox(String countryName) throws SQLException {
        stateComboBox.setItems(CountryAndState.getAllStatesForCountry(countryName));
    }

    /** This is the areAllTextFieldsFilledOut method.
     This method is used to check if all the text fields have a value, and returns a boolean.
     @return Returns a boolean, true if all fields are filled out; or false otherwise*/
    private boolean areAllInputFieldsFilledOut() {
        if(customerNameTxtField.getText().isEmpty()) {
            return false;
        } else if(addressTxtField.getText().isEmpty()) {
            return false;
        } else if(postalCodeTxtField.getText().isEmpty()) {
            return false;
        } else if(phoneNumberTxtField.getText().isEmpty()) {
            return false;
        } else if(countryComboBox.getValue() == null) {
            return false;
        } else if(stateComboBox.getValue() == null) {
            return false;
        }
        return true;
    }

    /** This is the onCountryComboBoxSelected method.
     This method is used to set the values for the state combo box if the country combo box has a value selected.
     @throws SQLException due to using SQL for database queries*/
    @FXML
    void onCountryComboBoxSelected() throws SQLException {
        /*
            If the user selected a country from the country combo box,
            then the options for the states/provinces within said country
            will be populated in the states combo box.
         */
        Country selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();

        if(selectedCountry != null) {
            setStateComboBox(selectedCountry.getCountry());
        }
    }

    /** This is the onActionSave method.
     This method is used to save any changes made to the customer object, and takes the user back to the customer records screen.
     @param event the event
     @throws IOException due to switchScreen method*/
    @FXML
    void onActionSave(ActionEvent event) throws SQLException, IOException {
        if(!areAllInputFieldsFilledOut()) {
            DialogBox.errorAlert("Error Dialog", "Error: You must fill in all input fields prior to clicking save");
            return;
        }

        String loggedInUsername = LoggedIn.getLoggedInUsername();
        Timestamp currentDateAndTime = Timestamp.valueOf(DateTimeConversion.getCurrentDateTimeFormatted());
        Customer addCustomer = Customer.createCustomerObjectFromTextFieldsAndComboBox(customerNameTxtField, addressTxtField, postalCodeTxtField, phoneNumberTxtField, currentDateAndTime, loggedInUsername, currentDateAndTime, loggedInUsername, stateComboBox);

        //Customer is ONLY added if it already doesn't exist in the database
        if(!doesCustomerExist(addCustomer)) {
            CustomerDAOImpl.insert(addCustomer);
            filePath.switchScreen(event, filePath.getCustomerRecordFilePath(), ScreenEnum.CUSTOMER_RECORD.toString());
        } else {
            DialogBox.errorAlert("Error Dialog", "Error: Customer already exists");
        }
    }

    /** This is the onActionCancel method.
     This method is used to cancel any changes and take the user back to the customer records screen.
     @param event the event
     @throws IOException due to switchScreen method*/
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getCustomerRecordFilePath(), ScreenEnum.CUSTOMER_RECORD.toString());
    }

    /** This is the initialize method.
     This method is used to initialize values as soon as this screen is loaded such as the country combo box.
     @param url the url
     @param rb the resource bundle*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setCountryComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}