package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.CountryDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.CustomerDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.SQLHelper;
import com.example.muhammad.chambers.c195.pa.dao.StateOrProvinceDAOImpl;
import com.example.muhammad.chambers.c195.pa.helper.*;
import com.example.muhammad.chambers.c195.pa.model.Country;
import com.example.muhammad.chambers.c195.pa.model.StateOrProvince;
import javafx.collections.FXCollections;
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

/** This class holds the code for the update customer controller for the update customer fxml file*/
public class UpdateCustomerController implements Initializable {
    /** Holds a reference to the file path class*/
    private FilePath filePath = new FilePath();

    /** Holds the country combo box*/
    @FXML
    ComboBox<String> countryComboBox;
    /** Holds the state combo box*/
    @FXML
    ComboBox<String> stateComboBox;
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
    /** Holds the customer id text field*/
    @FXML
    TextField customerIDTxtField;


    /** This is the setCountryComboBox method.
     This method is used to set the selection values for the country combo box with the values from the database.*/
    private void setCountryComboBox() throws SQLException {
        ObservableList<String> countries = FXCollections.observableArrayList();

        for(Country country: CountryDAOImpl.getCountriesList()) {
            countries.add(country.getCountry());
        }

        countryComboBox.setItems(countries);
    }

    /** This is the setStateComboBox method.
     This method is used to set the selection values for the state combo box with the values from the database.*/
    private void setStateComboBox(String countryName) throws SQLException {
        ObservableList<String> states = FXCollections.observableArrayList();

        for(StateOrProvince state: CountryAndState.getAllStatesForCountry(countryName)) {
            states.add(state.getState());
        }

        stateComboBox.setItems(states);
    }

    /** This is the areAllTextFieldsFilledOut method.
     This method is used to check if all the text fields have a value, and returns a boolean.
     @return Returns a boolean, true if all fields are filled out; or false otherwise*/
    private boolean areAllTextFieldsFilledOut() {
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
     This method is used to set the values for the state combo box if the country combo box has a value selected.*/
    @FXML
    void onCountryComboBoxSelected() throws SQLException {
        String selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();

        if(selectedCountry != null) {
            setStateComboBox(selectedCountry);
        }
    }

    /** This is the onActionSave method.
     This method is used to save any changes made to the customer object, and takes the user back to the customer records screen.
     @param event the event*/
    @FXML
    void onActionSave(ActionEvent event) throws SQLException, IOException {
        if(!areAllTextFieldsFilledOut()) {
            DialogBox.errorAlert("Error Dialog", "Error: You must fill in all input fields prior to \nclicking save");
            return;
        }

        String loggedInUsername = LoggedIn.getLoggedInUsername();
        Timestamp currentDateAndTime = Timestamp.valueOf(DateTimeConversion.getCurrentDateTimeFormatted());

        //Updates all fields for the Customer object in the database
        SQLHelper.updateForStrColumn(CustomerDAOImpl.TABLE_NAME, CustomerDAOImpl.CUSTOMER_ID_COLUMN_NAME, SelectedItem.getSelectedCustomer().getCustomerID(), CustomerDAOImpl.CUSTOMER_NAME_COL_NAME, customerNameTxtField.getText());
        SQLHelper.updateForStrColumn(CustomerDAOImpl.TABLE_NAME, CustomerDAOImpl.CUSTOMER_ID_COLUMN_NAME, SelectedItem.getSelectedCustomer().getCustomerID(), CustomerDAOImpl.ADDRESS_COL_NAME, addressTxtField.getText());
        SQLHelper.updateForStrColumn(CustomerDAOImpl.TABLE_NAME, CustomerDAOImpl.CUSTOMER_ID_COLUMN_NAME, SelectedItem.getSelectedCustomer().getCustomerID(), CustomerDAOImpl.POSTAL_CODE_COL_NAME, postalCodeTxtField.getText());
        SQLHelper.updateForStrColumn(CustomerDAOImpl.TABLE_NAME, CustomerDAOImpl.CUSTOMER_ID_COLUMN_NAME, SelectedItem.getSelectedCustomer().getCustomerID(), CustomerDAOImpl.PHONE_COL_NAME, phoneNumberTxtField.getText());
        SQLHelper.updateForStrColumn(CustomerDAOImpl.TABLE_NAME, CustomerDAOImpl.CUSTOMER_ID_COLUMN_NAME, SelectedItem.getSelectedCustomer().getCustomerID(), CustomerDAOImpl.LAST_UPDATED_BY_COL_NAME, loggedInUsername);
        SQLHelper.updateForTimestampColumn(CustomerDAOImpl.TABLE_NAME, CustomerDAOImpl.CUSTOMER_ID_COLUMN_NAME, SelectedItem.getSelectedCustomer().getCustomerID(), CustomerDAOImpl.LAST_UPDATE_COL_NAME, currentDateAndTime);
        SQLHelper.updateForIntColumn(CustomerDAOImpl.TABLE_NAME, CustomerDAOImpl.CUSTOMER_ID_COLUMN_NAME, SelectedItem.getSelectedCustomer().getCustomerID(), CustomerDAOImpl.DIVISION_ID_COL_NAME, StateOrProvinceDAOImpl.findStateInListByName(stateComboBox.getValue()).getStateID());

        SelectedItem.clearSelectedCustomer();
        filePath.switchScreen(event, filePath.getCustomerRecordFilePath(), ScreenEnum.CUSTOMER_RECORD.toString());
    }

    /** This is the onActionCancel method.
     This method is used to cancel any changes and take the user back to the customer records screen.
     @param event the event*/
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedCustomer();
        filePath.switchScreen(event, filePath.getCustomerRecordFilePath(), ScreenEnum.CUSTOMER_RECORD.toString());
    }

    /** This is the initialize method.
     This method is used to initialize values as soon as this screen is loaded such as the country combo box, state combo box,
     and text fields based on the values from the selected row from the customer table.
     @param url the url
     @param rb the resource bundle*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            setCountryComboBox();
            countryComboBox.setValue(CountryDAOImpl.findCountryInListByName(SelectedItem.getSelectedCustomer().getCountryName()).getCountry());
            setStateComboBox(SelectedItem.getSelectedCustomer().getCountryName());
            stateComboBox.setValue(StateOrProvinceDAOImpl.findStateInListByID(SelectedItem.getSelectedCustomer().getStateID()).getState());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        customerIDTxtField.setText(String.valueOf(SelectedItem.getSelectedCustomer().getCustomerID()));
        customerNameTxtField.setText(SelectedItem.getSelectedCustomer().getCustomerName());
        addressTxtField.setText(SelectedItem.getSelectedCustomer().getAddress());
        postalCodeTxtField.setText(SelectedItem.getSelectedCustomer().getPostalCode());
        phoneNumberTxtField.setText(SelectedItem.getSelectedCustomer().getPhone());
    }
}