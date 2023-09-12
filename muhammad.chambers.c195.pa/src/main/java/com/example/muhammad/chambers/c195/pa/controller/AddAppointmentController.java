package com.example.muhammad.chambers.c195.pa.controller;

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


    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
    }

    @FXML
    void onActionSave(ActionEvent event) {
        if(titleTxtField.getText().isEmpty()) {
            return;
        } else if(descriptionTxtField.getText().isEmpty()) {
            return;
        } else if(locationTxtField.getText().isEmpty()) {
            return;
        } else if(contactComboBox.getValue() == null) {
            return;
        } else if(typeTxtField.getText().isEmpty()) {
            return;
        } else if(startDate.getValue() == null) {
            return;
        } else if(startTimeComboBox.getValue() == null) {
            return;
        } else if(endDate.getValue() == null) {
            return;
        } else if(endTimeComboBox.getValue() == null) {
            return;
        } else if(customerIdTxtField.getText().isEmpty()) {
            return;
        } else if(userIdTxtField.getText().isEmpty()) {
            return;
        }

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