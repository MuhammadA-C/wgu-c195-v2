package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.AppointmentDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.ContactDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.CustomerDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.UserDAOImpl;
import com.example.muhammad.chambers.c195.pa.helper.*;
import com.example.muhammad.chambers.c195.pa.model.Appointment;
import com.example.muhammad.chambers.c195.pa.model.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @FXML
    private Text businessHoursHint;


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

    private boolean isStartDateBeforeEndDateOrTheSame(DatePicker startDate, DatePicker endDate) {
        LocalDate start =  startDate.getValue();
        LocalDate end = endDate.getValue();

        if(isStartDateAndEndDateTheSame(startDate, endDate)) {
            return true;
        } else if(start.isBefore(end)) {
            return true;
        }
        return false;
    }

    private boolean isStartDateAndEndDateTheSame(DatePicker startDate, DatePicker endDate) {
        LocalDate start =  startDate.getValue();
        LocalDate end = endDate.getValue();

        if(start.isEqual(end)) {
            return true;
        }
        return false;
    }

    private boolean isStartTimeBeforeEndTime(Timestamp startTime, Timestamp endTime) {
        LocalTime start = startTime.toLocalDateTime().toLocalTime();
        LocalTime end = endTime.toLocalDateTime().toLocalTime();

        if(start.isBefore(end)) {
            return true;
        }
        System.out.println("Start time is before end time");
        return false;
    }


    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
    }

    @FXML
    void onActionSave(ActionEvent event) throws SQLException, IOException {
        if(!areAllInputFieldsFilledOut()) {
            //Checks if all input fields are filled out
            System.out.println("You must fill in all input fields prior to clicking save");
            return;
        } else if(!CustomerDAOImpl.isCustomerIDInList(Integer.valueOf(customerIdTxtField.getText()))) {
            //Checks if the customer id is valid; is the customer id present in the database?
            System.out.println("Error: You entered in an incorrect Customer ID. Enter another Customer ID.");
            return;
        } else if(!UserDAOImpl.isUserIDInList(Integer.valueOf(userIdTxtField.getText()))) {
            //Checks if the user id is valid; is the user id present in the database?
            System.out.println("Error: You entered in an incorrect User ID.");
            return;
        } else if(!isStartDateBeforeEndDateOrTheSame(startDate, endDate)) {
            //Checks if the start date is before end date, or the same
            System.out.println("Error: Start Date must either come before End Date or be the same as End Date.");
            return;
        }

        //Creating start and end date and time timestamp
        Timestamp startTimestamp = createTimestampForDateAndTime(startDate, startTimeComboBox);
        Timestamp endTimestamp = createTimestampForDateAndTime(endDate, endTimeComboBox);

        //Check to verify if start time is before end time if the start and end dates are the same
        if(isStartDateAndEndDateTheSame(startDate, endDate) && (!isStartTimeBeforeEndTime(startTimestamp, endTimestamp))) {
            System.out.println("Error: Start Time must be before End Time.");
            return;
        }

        //Check to verify if the time range is correct, within business hours
        if(!BusinessHour.isStartAndEndTimeWithBusinessHours(startTimestamp.toLocalDateTime().toLocalTime(), endTimestamp.toLocalDateTime().toLocalTime())) {
            System.out.println("Start and end Times must be within business hours.");
            return;
        }

        Appointment appointment = createAppointmentObject(titleTxtField, descriptionTxtField, locationTxtField, contactComboBox, userIdTxtField, customerIdTxtField, typeTxtField, startTimestamp, endTimestamp);

        String loggedInUsername = LoggedIn.getLoggedInUsername();
        Timestamp currentDateAndTime = Timestamp.valueOf(DateTimeConversion.getCurrentDateTimeFormatted());

        appointment.setCreateDate(currentDateAndTime);
        appointment.setCreatedBy(loggedInUsername);
        appointment.setLastUpdate(currentDateAndTime);
        appointment.setLastUpdatedBy(loggedInUsername);

        if(!AppointmentDAOImpl.doesCustomerIDHaveAnyAppointments(appointment.getCustomerID())) {
            //Adds appointment if customer id does NOT have any appointments already
            AppointmentDAOImpl.insert(appointment);
            System.out.println("Added appointment");
            filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
        } else if(AppointmentOverlap.doesAppointmentHaveTheSameStartAndEndDate(appointment) && AppointmentOverlap.areAppointmentTimesOverlapping(appointment) == false) {
            //Adds appointment if start date and end date for appointment to add matches an appointment in the database, but the start and end times do NOT overlap
            AppointmentDAOImpl.insert(appointment);
            System.out.println("Added appointment");
            filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
        } else if(!AppointmentOverlap.areAppointmentDatesOverlapping(appointment)) {
            //Adds appointment if the start and end dates do NOT overlap with any appointments in the database
            AppointmentDAOImpl.insert(appointment);
            System.out.println("Added appointment");
            filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
        } else if((AppointmentOverlap.doesAppointmentEndDateOverlapWithStartDate(appointment) || AppointmentOverlap.doesAppointmentStartDateOverlapWithEndDate(appointment)) && !AppointmentOverlap.areAppointmentTimesOverlapping(appointment)) {
            //Adds appointment if the start date overlaps with an end date in the database, but the times do NOT overlap
            //Or adds an appointment if the end date overlaps with a start date in the database, but the times do NOT overlap
            AppointmentDAOImpl.insert(appointment);
            System.out.println("Added appointment");
            filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
        } else {
            System.out.println("Error: Cannot add appointment because it will overlap with an existing appointment for Customer ID: " + appointment.getCustomerID());
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
        //Sets the note text which converts the EST business hours to the users time zone
        businessHoursHint.setText(BusinessHour.businessHoursHintTxt());
    }
}