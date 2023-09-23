package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.CountryDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.CustomerDAOImpl;
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

public class UpdateCustomerController implements Initializable {

    private FilePath filePath = new FilePath();

    //FXML Fields
    @FXML
    ComboBox<String> countryComboBox;
    @FXML
    ComboBox<String> stateComboBox;
    @FXML
    TextField customerNameTxtField;
    @FXML
    TextField addressTxtField;
    @FXML
    TextField phoneNumberTxtField;
    @FXML
    TextField postalCodeTxtField;
    @FXML
    TextField customerIDTxtField;


    private void setCountryComboBox() throws SQLException {
        ObservableList<String> countries = FXCollections.observableArrayList();

        for(Country country: CountryDAOImpl.getCountriesList()) {
            countries.add(country.getCountry());
        }

        countryComboBox.setItems(countries);
    }

    private void setStateComboBox(String countryName) throws SQLException {
        ObservableList<String> states = FXCollections.observableArrayList();

        for(StateOrProvince state: CountryAndState.getAllStatesForCountry(countryName)) {
            states.add(state.getState());
        }

        stateComboBox.setItems(states);
    }

    private boolean areAllTextFieldsFieldOut() {
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
        String selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();

        if(selectedCountry != null) {
            setStateComboBox(selectedCountry);
        }
    }

    @FXML
    void onActionSave(ActionEvent event) throws SQLException, IOException {
        if(!areAllTextFieldsFieldOut()) {
            DialogBox.errorAlert("Error Dialog", "Error: You must fill in all input fields prior to clicking save");
            return;
        }


        String loggedInUsername = LoggedIn.getLoggedInUsername();
        Timestamp currentDateAndTime = Timestamp.valueOf(DateTimeConversion.getCurrentDateTimeFormatted());

        CustomerDAOImpl.updateForStrColumn(SelectedItem.getSelectedCustomer().getCustomerID(), CustomerDAOImpl.CUSTOMER_NAME_COL_NAME, customerNameTxtField.getText());
        CustomerDAOImpl.updateForStrColumn(SelectedItem.getSelectedCustomer().getCustomerID(), CustomerDAOImpl.ADDRESS_COL_NAME, addressTxtField.getText());
        CustomerDAOImpl.updateForStrColumn(SelectedItem.getSelectedCustomer().getCustomerID(), CustomerDAOImpl.POSTAL_CODE_COL_NAME, postalCodeTxtField.getText());
        CustomerDAOImpl.updateForStrColumn(SelectedItem.getSelectedCustomer().getCustomerID(), CustomerDAOImpl.PHONE_COL_NAME, phoneNumberTxtField.getText());
        CustomerDAOImpl.updateForTimestampColumn(SelectedItem.getSelectedCustomer().getCustomerID(), CustomerDAOImpl.LAST_UPDATE_COL_NAME, currentDateAndTime);
        CustomerDAOImpl.updateForStrColumn(SelectedItem.getSelectedCustomer().getCustomerID(), CustomerDAOImpl.LAST_UPDATED_BY_COL_NAME, loggedInUsername);
        CustomerDAOImpl.updateForIntColumn(SelectedItem.getSelectedCustomer().getCustomerID(), CustomerDAOImpl.DIVISION_ID_COL_NAME, StateOrProvinceDAOImpl.findStateInListByName(stateComboBox.getValue()).getStateID());

        SelectedItem.clearSelectedCustomer();
        filePath.switchScreen(event, filePath.getCustomerRecordFilePath(), ScreenEnum.CUSTOMER_RECORD.toString());
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedCustomer();
        filePath.switchScreen(event, filePath.getCustomerRecordFilePath(), ScreenEnum.CUSTOMER_RECORD.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            //Populates the list with all selection options
            setCountryComboBox();
            //Sets the selected value
            countryComboBox.setValue(CountryDAOImpl.findCountryInListByName(SelectedItem.getSelectedCustomer().getCountryName()).getCountry());
            //Populates with list with all selection options
            setStateComboBox(SelectedItem.getSelectedCustomer().getCountryName());
            //Sets the selected value
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