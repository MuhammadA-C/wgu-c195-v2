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

public class AddCustomerController implements Initializable {
    private FilePath filePath = new FilePath();

    @FXML
    ComboBox<Country> countryComboBox;
    @FXML
    ComboBox<StateOrProvince> stateComboBox;
    @FXML
    TextField customerNameTxtField;
    @FXML
    TextField addressTxtField;
    @FXML
    TextField phoneNumberTxtField;
    @FXML
    TextField postalCodeTxtField;


    private boolean doesCustomerExist(Customer addCustomer) throws SQLException {
        for(int i = 0; i < CustomerDAOImpl.getCustomersList().size(); i++) {
            ObservableList<Customer> customers = CustomerDAOImpl.getCustomersList();

            if(Customer.areSame(addCustomer, customers.get(i))) {
                return true;
            }
        }
        return false;
    }

    private void setCountryComboBox() throws SQLException {
        countryComboBox.setItems(CountryDAOImpl.getCountriesList());
    }

    private void setStateComboBox(String countryName) throws SQLException {
        stateComboBox.setItems(CountryAndState.getAllStatesForCountry(countryName));
    }

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

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getCustomerRecordFilePath(), ScreenEnum.CUSTOMER_RECORD.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setCountryComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}