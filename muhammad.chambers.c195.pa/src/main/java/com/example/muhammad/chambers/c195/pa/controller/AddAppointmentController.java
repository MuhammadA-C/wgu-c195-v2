package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.AppointmentDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.ContactDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.CustomerDAOImpl;
import com.example.muhammad.chambers.c195.pa.helper.DateTimeConversion;
import com.example.muhammad.chambers.c195.pa.helper.FilePath;
import com.example.muhammad.chambers.c195.pa.helper.LoggedIn;
import com.example.muhammad.chambers.c195.pa.helper.ScreenEnum;
import com.example.muhammad.chambers.c195.pa.model.Appointment;
import com.example.muhammad.chambers.c195.pa.model.Contact;
import com.example.muhammad.chambers.c195.pa.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    private FilePath filePath = new FilePath();

    //FXML Fields
    @FXML
    private ComboBox<Contact> contactComboBox;
    @FXML
    private ComboBox<String> startTimeComboBox;
    @FXML
    private ComboBox<String> endTimeComboBox;
    @FXML
    private TextField titleTxtField;
    @FXML
    private TextField descriptionTxtField;
    @FXML
    private TextField locationTxtField;
    @FXML
    private TextField typeTxtField;
    @FXML
    private TextField customerIdTxtField;
    @FXML
    private TextField userIdTxtField;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;


    private void setContactComboBox() throws SQLException {
        contactComboBox.setItems(ContactDAOImpl.getContactsList());
    }

    private Timestamp createTimestampForDateAndTime(DatePicker datePicker, ComboBox<String> timeComboBox) {
        LocalTime endLocalTime = DateTimeConversion.convertFormattedAppointmentStrToLocalTime(timeComboBox.getValue());
        String endDateTimeStr = datePicker.getValue().toString() + " " + endLocalTime + ":00";

        return Timestamp.valueOf(endDateTimeStr);
    }

    public Appointment createAppointmentObject(TextField titleTxtField, TextField descriptionTxtField, TextField locationTxtField, ComboBox<Contact> contactComboBox, TextField userIdTxtField, TextField customerIdTxtField, TextField typeTxtField, Timestamp startTimestamp, Timestamp endTimestamp) {
        String title = titleTxtField.getText();
        String description = descriptionTxtField.getText();
        String location = locationTxtField.getText();
        int contactID = contactComboBox.getValue().getContactID();
        int userID = Integer.valueOf(userIdTxtField.getText());
        int customerID = Integer.valueOf(customerIdTxtField.getText());
        String type = typeTxtField.getText();

        return new Appointment(title, description, location, type, startTimestamp, endTimestamp, contactID, customerID, userID);
    }

    private boolean areAllInputFieldsFilledOut() {
        if(titleTxtField.getText().isEmpty()) {
            return false;
        } else if(descriptionTxtField.getText().isEmpty()) {
            return false;
        } else if(locationTxtField.getText().isEmpty()) {
            return false;
        } else if(contactComboBox.getValue() == null) {
            return false;
        } else if(typeTxtField.getText().isEmpty()) {
            return false;
        } else if(startDate.getValue() == null) {
            return false;
        } else if(startTimeComboBox.getValue() == null) {
            return false;
        } else if(endDate.getValue() == null) {
            return false;
        } else if(endTimeComboBox.getValue() == null) {
            return false;
        } else if(customerIdTxtField.getText().isEmpty()) {
            return false;
        } else if(userIdTxtField.getText().isEmpty()) {
            return false;
        }
        return true;
    }


    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
    }

    @FXML
    void onActionSave(ActionEvent event) throws SQLException, IOException {
        if(!areAllInputFieldsFilledOut()) {
            System.out.println("You must fill in all input fields prior to clicking save");
            return;
        }

        //Need a check to verify if the contact id is valid
        //Need a check to verify if the customer id is valid
        //Need a check to verify if the time range is correct, within business hours


        //Creating start and end date and time timestamp
        Timestamp startTimestamp = createTimestampForDateAndTime(startDate, startTimeComboBox);
        Timestamp endTimestamp = createTimestampForDateAndTime(endDate, endTimeComboBox);

        Appointment appointment = createAppointmentObject(titleTxtField, descriptionTxtField, locationTxtField, contactComboBox, userIdTxtField, customerIdTxtField, typeTxtField, startTimestamp, endTimestamp);

        String loggedInUsername = LoggedIn.getLoggedInUsername();
        Timestamp currentDateAndTime = Timestamp.valueOf(DateTimeConversion.getCurrentDateTimeFormatted());

        appointment.setCreateDate(currentDateAndTime);
        appointment.setCreatedBy(loggedInUsername);
        appointment.setLastUpdate(currentDateAndTime);
        appointment.setLastUpdatedBy(loggedInUsername);
        /*
            Need to add more code to:
            1. Check if the object already exists prior to adding to the database
            2. Verify that the start and end date times are within the specified work hours
            3. Prevent overlapping appointments
         */

        //Note: I need a time check to ensure the added appointments are within range
        if(!AppointmentDAOImpl.doesCustomerIDHaveAnyAppointments(appointment.getCustomerID())) {
            AppointmentDAOImpl.insert(appointment);
            System.out.println("Added appointment");
            filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
        } else if(!AppointmentDAOImpl.areAppointmentDatesTheSame(appointment.getCustomerID(), appointment.getStart(), appointment.getEnd())) {
            AppointmentDAOImpl.insert(appointment);
            System.out.println("Added appointment");
            filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
        } else {
            System.out.println("Cannot add appointment");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setContactComboBox();
            endTimeComboBox.setItems(DateTimeConversion.getAppointmentsTimesFormatted());
            startTimeComboBox.setItems(DateTimeConversion.getAppointmentsTimesFormatted());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}