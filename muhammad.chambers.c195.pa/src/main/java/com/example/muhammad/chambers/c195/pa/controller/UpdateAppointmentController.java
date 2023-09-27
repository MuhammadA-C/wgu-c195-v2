package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.*;
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

/** This class holds the code for the update appointment controller for the update appointment fxml file*/
public class UpdateAppointmentController implements Initializable {
    /** Holds a reference to the file path class*/
    private FilePath filePath = new FilePath();
    /** Holds the contact combo box*/
    @FXML
    private ComboBox<Contact> contactComboBox;
    /** Holds the start time combo box*/
    @FXML
    private ComboBox<String> startTimeComboBox;
    /** Holds the end time combo box*/
    @FXML
    private ComboBox<String> endTimeComboBox;
    /** Holds the title text field*/
    @FXML
    private TextField titleTxtField;
    /** Holds the description text field*/
    @FXML
    private TextField descriptionTxtField;
    /** Holds the location text field*/
    @FXML
    private TextField locationTxtField;
    /** Holds the type text field*/
    @FXML
    private TextField typeTxtField;
    /** Holds the customer id text field*/
    @FXML
    private TextField customerIdTxtField;
    /** Holds the user id text field*/
    @FXML
    private TextField userIdTxtField;
    /** Holds the start date, date picker*/
    @FXML
    private DatePicker startDate;
    /** Holds the end date, date picker*/
    @FXML
    private DatePicker endDate;
    /** Holds the business hours hint text*/
    @FXML
    private Text businessHoursHint;
    /** Holds the appointment id text field*/
    @FXML
    private TextField appointmentID;


    /** This is the setContactComboBox method.
     This method is used to set the contact combo box values with the list of contacts from the database.*/
    private void setContactComboBox() throws SQLException {
        contactComboBox.setItems(ContactDAOImpl.getContactsList());
    }

    /** This is the createTimestampForDateAndTime method.
     This method is used to convert the data and time values into a timestamp.
     @param datePicker the data picker value
     @param timeComboBox the time combo box value
     @return Returns a timestamp*/
    private Timestamp createTimestampForDateAndTime(DatePicker datePicker, ComboBox<String> timeComboBox) {
        LocalTime endLocalTime = DateTimeConversion.convertFormattedAppointmentStrToLocalTime(timeComboBox.getValue());
        String endDateTimeStr = datePicker.getValue().toString() + " " + endLocalTime + ":00";

        return Timestamp.valueOf(endDateTimeStr);
    }

    /** This is the createAppointmentObject method.
     This method is used to create an appointment object from the forum values.
     @param titleTxtField the title value
     @param descriptionTxtField the description value
     @param locationTxtField the location value
     @param contactComboBox the contact value
     @param userIdTxtField the user id value
     @param customerIdTxtField the customer id value
     @param typeTxtField the type value
     @param startTimestamp the start value
     @param endTimestamp the end value
     @return Returns an appointment object*/
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

    /** This is the areAllInputFieldsFilledOut method.
     This method is used to check if all the text fields have a value, and returns a boolean.
     @return Returns a boolean, true if all fields are filled out; or false otherwise*/
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

    /** This is the isStartDateBeforeEndDateOrTheSame method.
     This method is used to check if the startDate is before the endDate from the data picker fields, and returns a boolean.
     @param startDate the startDate
     @param endDate the endDate
     @return Returns a boolean; true if the start date is before the end date, or false otherwise*/
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

    /** This is the isStartDateAndEndDateTheSame method.
     This method is used to check if the startDate and the endDate from the data picker fields are the same, and returns a boolean.
     @param startDate the startDate
     @param endDate the endDate
     @return Returns a boolean; true if the start date and end date are the same, or false otherwise*/
    private boolean isStartDateAndEndDateTheSame(DatePicker startDate, DatePicker endDate) {
        LocalDate start =  startDate.getValue();
        LocalDate end = endDate.getValue();

        if(start.isEqual(end)) {
            return true;
        }
        return false;
    }

    /** This is the isStartTimeBeforeEndTime method.
     This method is used to check if the startTime is before the endTime from the fields, and returns a boolean.
     @param startTime the startDate
     @param endTime the endDate
     @return Returns a boolean; true if the start time is before the end time, or false otherwise*/
    private boolean isStartTimeBeforeEndTime(Timestamp startTime, Timestamp endTime) {
        LocalTime start = startTime.toLocalDateTime().toLocalTime();
        LocalTime end = endTime.toLocalDateTime().toLocalTime();

        if(start.isBefore(end)) {
            return true;
        }
        return false;
    }

    /** This is the updateAppointmentInDatabase method.
     This method is used to update the selected appointment in the database.
     @param appointment the appointment to update*/
    private void updateAppointmentInDatabase(Appointment appointment) throws SQLException {
        SQLHelper.updateForStrColumn(AppointmentDAOImpl.TABLE_NAME, AppointmentDAOImpl.APPOINTMENT_ID_COLUMN_NAME, appointment.getAppointmentID(), AppointmentDAOImpl.TITLE_COL_NAME, appointment.getTitle());
        SQLHelper.updateForStrColumn(AppointmentDAOImpl.TABLE_NAME, AppointmentDAOImpl.APPOINTMENT_ID_COLUMN_NAME, appointment.getAppointmentID(), AppointmentDAOImpl.DESCRIPTION_COL_NAME, appointment.getDescription());
        SQLHelper.updateForStrColumn(AppointmentDAOImpl.TABLE_NAME, AppointmentDAOImpl.APPOINTMENT_ID_COLUMN_NAME, appointment.getAppointmentID(), AppointmentDAOImpl.LOCATION_COL_NAME, appointment.getLocation());
        SQLHelper.updateForStrColumn(AppointmentDAOImpl.TABLE_NAME, AppointmentDAOImpl.APPOINTMENT_ID_COLUMN_NAME, appointment.getAppointmentID(), AppointmentDAOImpl.TYPE_COL_NAME, appointment.getType());
        SQLHelper.updateForStrColumn(AppointmentDAOImpl.TABLE_NAME, AppointmentDAOImpl.APPOINTMENT_ID_COLUMN_NAME, appointment.getAppointmentID(), AppointmentDAOImpl.LAST_UPDATED_BY_COL_NAME, appointment.getLastUpdatedBy());
        SQLHelper.updateForIntColumn(AppointmentDAOImpl.TABLE_NAME, AppointmentDAOImpl.APPOINTMENT_ID_COLUMN_NAME, appointment.getAppointmentID(), AppointmentDAOImpl.CUSTOMER_ID_COL_NAME, appointment.getCustomerID());
        SQLHelper.updateForIntColumn(AppointmentDAOImpl.TABLE_NAME, AppointmentDAOImpl.APPOINTMENT_ID_COLUMN_NAME, appointment.getAppointmentID(), AppointmentDAOImpl.USER_ID_COL_NAME, appointment.getUserID());
        SQLHelper.updateForIntColumn(AppointmentDAOImpl.TABLE_NAME, AppointmentDAOImpl.APPOINTMENT_ID_COLUMN_NAME, appointment.getAppointmentID(), AppointmentDAOImpl.CONTACT_ID_COL_NAME, appointment.getContactID());
        SQLHelper.updateForTimestampColumn(AppointmentDAOImpl.TABLE_NAME, AppointmentDAOImpl.APPOINTMENT_ID_COLUMN_NAME, appointment.getAppointmentID(), AppointmentDAOImpl.START_COL_NAME, appointment.getStart());
        SQLHelper.updateForTimestampColumn(AppointmentDAOImpl.TABLE_NAME, AppointmentDAOImpl.APPOINTMENT_ID_COLUMN_NAME, appointment.getAppointmentID(), AppointmentDAOImpl.END_COL_NAME, appointment.getEnd());
        SQLHelper.updateForTimestampColumn(AppointmentDAOImpl.TABLE_NAME, AppointmentDAOImpl.APPOINTMENT_ID_COLUMN_NAME, appointment.getAppointmentID(), AppointmentDAOImpl.LAST_UPDATE_COL_NAME, appointment.getLastUpdate());
    }

    /** This is the updateAppointmentInDatabase method.
     This method is used to cancel any changes and return to the main screen.
     @param event the event*/
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedAppointment();
        filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
    }


    /** This is the onActionSave method.
     This method saves any changes made to the appointment object to the database, and returns the user back to the main screen.
     @param event the event*/
    @FXML
    void onActionSave(ActionEvent event) throws SQLException, IOException {
        //Checking if all input fields are filled out
        if(!areAllInputFieldsFilledOut()) {
            //Checks if all input fields are filled out
            DialogBox.errorAlert("Error Dialog", "Error: You must fill in all input fields prior to clicking save");
            return;
        } else if(!CustomerDAOImpl.isCustomerIDInList(Integer.valueOf(customerIdTxtField.getText()))) {
            //Checks if the customer id is valid; is the customer id present in the database?
            DialogBox.errorAlert("Error Dialog", "Error: You entered in an incorrect Customer ID. Enter another Customer ID.");
            return;
        } else if(!UserDAOImpl.isUserIDInList(Integer.valueOf(userIdTxtField.getText()))) {
            //Checks if the user id is valid; is the user id present in the database?
            DialogBox.errorAlert("Error Dialog", "Error: You entered in an incorrect User ID.");
            return;
        } else if(!isStartDateBeforeEndDateOrTheSame(startDate, endDate)) {
            //Checks if the start date is before end date, or the same
            DialogBox.errorAlert("Error Dialog", "Error: Start Date must either come before End Date or be the same as End Date.");
            return;
        }

        //Creating start and end date and time timestamp
        Timestamp startTimestamp = createTimestampForDateAndTime(startDate, startTimeComboBox);
        Timestamp endTimestamp = createTimestampForDateAndTime(endDate, endTimeComboBox);

        //Check to verify if start time is before end time if the start and end dates are the same
        if(isStartDateAndEndDateTheSame(startDate, endDate) && (!isStartTimeBeforeEndTime(startTimestamp, endTimestamp))) {
            DialogBox.errorAlert("Error Dialog", "Error: Start Time must be before End Time.");
            return;
        }

        //Check to verify if the time range is correct, within business hours
        if(!BusinessHour.isStartAndEndTimeWithBusinessHours(startTimestamp.toLocalDateTime().toLocalTime(), endTimestamp.toLocalDateTime().toLocalTime())) {
            DialogBox.errorAlert("Error Dialog", "Start and end Times must be within business hours.");
            return;
        }

        Appointment appointment = createAppointmentObject(titleTxtField, descriptionTxtField, locationTxtField, contactComboBox, userIdTxtField, customerIdTxtField, typeTxtField, startTimestamp, endTimestamp);

        String loggedInUsername = LoggedIn.getLoggedInUsername();
        Timestamp currentDateAndTime = Timestamp.valueOf(DateTimeConversion.getCurrentDateTimeFormatted());

        appointment.setAppointmentID(SelectedItem.getSelectedAppointment().getAppointmentID());
        appointment.setLastUpdate(currentDateAndTime);
        appointment.setLastUpdatedBy(loggedInUsername);

        if(!AppointmentDAOImpl.doesCustomerIDHaveAnyAppointments(appointment.getCustomerID())) {
            //Adds appointment if customer id does NOT have any appointments already
            updateAppointmentInDatabase(appointment);
            SelectedItem.clearSelectedAppointment();
            filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
        } else if(AppointmentOverlap.doesAppointmentHaveTheSameStartAndEndDate(appointment) && AppointmentOverlap.areAppointmentTimesOverlapping(appointment, true) == false) {
            //Adds appointment if start date and end date for appointment to add matches an appointment in the database, but the start and end times do NOT overlap
            updateAppointmentInDatabase(appointment);
            SelectedItem.clearSelectedAppointment();
            filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
        } else if(!AppointmentOverlap.areAppointmentDatesOverlapping(appointment, true)) {
            //Adds appointment if the start and end dates do NOT overlap with any appointments in the database
            updateAppointmentInDatabase(appointment);
            SelectedItem.clearSelectedAppointment();
            filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
        } else if((AppointmentOverlap.doesAppointmentEndDateOverlapWithStartDate(appointment) || AppointmentOverlap.doesAppointmentStartDateOverlapWithEndDate(appointment)) && !AppointmentOverlap.areAppointmentTimesOverlapping(appointment, true)) {
            //Adds appointment if the start date overlaps with an end date in the database, but the times do NOT overlap
            //Or adds an appointment if the end date overlaps with a start date in the database, but the times do NOT overlap
            updateAppointmentInDatabase(appointment);
            SelectedItem.clearSelectedAppointment();
            filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
        } else {
            DialogBox.errorAlert("Error Dialog", "Error: Cannot add appointment because it will overlap with an existing appointment for Customer ID: " + appointment.getCustomerID());
        }
    }

    /** This is the initialize method.
     This method is used to initialize values for the screen soon as it loads such as setting the contact combo box values, start time combo box values,
     the end time combo box values, and all the text fields with the values from the selected row from the appointment table.
     @param url the url
     @param rb the resource bundle*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setContactComboBox();
            endTimeComboBox.setItems(DateTimeConversion.getAppointmentsTimesFormatted());
            startTimeComboBox.setItems(DateTimeConversion.getAppointmentsTimesFormatted());
            contactComboBox.setValue(ContactDAOImpl.getContactInList(SelectedItem.getSelectedAppointment().getContactID()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Sets the note text which converts the EST business hours to the users time zone
        businessHoursHint.setText(BusinessHour.businessHoursHintTxt());
        appointmentID.setText(String.valueOf(SelectedItem.getSelectedAppointment().getAppointmentID()));
        titleTxtField.setText(SelectedItem.getSelectedAppointment().getTitle());
        descriptionTxtField.setText(SelectedItem.getSelectedAppointment().getDescription());
        locationTxtField.setText(SelectedItem.getSelectedAppointment().getLocation());
        typeTxtField.setText(SelectedItem.getSelectedAppointment().getType());
        startDate.setValue(SelectedItem.getSelectedAppointment().getStart().toLocalDateTime().toLocalDate());
        startTimeComboBox.setValue(DateTimeConversion.convert24hrTo12hrTime(SelectedItem.getSelectedAppointment().getStart().toLocalDateTime().toLocalTime()));
        endDate.setValue(SelectedItem.getSelectedAppointment().getEnd().toLocalDateTime().toLocalDate());
        customerIdTxtField.setText(String.valueOf(SelectedItem.getSelectedAppointment().getCustomerID()));
        userIdTxtField.setText(String.valueOf(SelectedItem.getSelectedAppointment().getUserID()));
        endTimeComboBox.setValue(DateTimeConversion.convert24hrTo12hrTime(SelectedItem.getSelectedAppointment().getEnd().toLocalDateTime().toLocalTime()));
    }
}