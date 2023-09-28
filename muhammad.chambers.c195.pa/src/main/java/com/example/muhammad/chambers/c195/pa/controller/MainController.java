package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.AppointmentDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.JDBC;
import com.example.muhammad.chambers.c195.pa.dao.SQLHelper;
import com.example.muhammad.chambers.c195.pa.helper.*;
import com.example.muhammad.chambers.c195.pa.model.Appointment;
import com.example.muhammad.chambers.c195.pa.model.Customer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class holds the code for the main fxml file*/
public class MainController implements Initializable {
    /** Holds a reference to the file path class*/
    private FilePath filePath = new FilePath();
    /** Holds the appointments table view*/
    @FXML
    private TableView<Appointment> appointmentsTableView;
    /** Holds the appointment id column*/
    @FXML
    private TableColumn<Appointment, Integer> appointmentIdCol;
    /** Holds the title column*/
    @FXML
    private TableColumn<Customer, String> titleCol;
    /** Holds the description column*/
    @FXML
    private TableColumn<Customer, String> descriptionCol;
    /** Holds the location column*/
    @FXML
    private TableColumn<Customer, String> locationCol;
    /** Holds the contact column*/
    @FXML
    private TableColumn<Customer, String> contactCol;
    /** Holds the type column*/
    @FXML
    private TableColumn<Customer, String> typeCol;
    /** Holds the start column*/
    @FXML
    private TableColumn<Customer, String> startCol;
    /** Holds the end column*/
    @FXML
    private TableColumn<Customer, String> endCol;
    /** Holds the customer id column*/
    @FXML
    private TableColumn<Customer, Integer> customerIdCol;
    /** Holds the user id column*/
    @FXML
    private TableColumn<Customer, Integer> userIdCol;
    /** Holds the sort by week radio button*/
    @FXML
    private RadioButton sortByWeek;
    /** Holds the sort by month radio button*/
    @FXML
    private RadioButton sortByMonth;
    /** Holds the view all radio button*/
    @FXML
    private RadioButton viewAll;
    /** Holds the upcoming appointment alert text*/
    @FXML
    private Text upcomingAppointmentAlert;


    /** This is the setAppointmentsTableView method.
     This method sets the different columns for the appointment table view.
     @param appointments appointments list*/
    private void setAppointmentsTableView(ObservableList<Appointment> appointments) {
        appointmentsTableView.setItems(appointments);
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startStr"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endStr"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }

    /** This is the onClickCustomerRecordBtn method.
     This method is used to take the user to the customer record screen.
     @param event the event*/
    @FXML
    void onClickCustomerRecordBtn(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedAppointment();
        filePath.switchScreen(event, filePath.getCustomerRecordFilePath(), ScreenEnum.CUSTOMER_RECORD.toString());
    }

    /** This is the onClickReportBtn method.
     This method is used to take the user to the report screen.
     @param event the event*/
    @FXML
    void onClickReportBtn(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedAppointment();
        filePath.switchScreen(event, filePath.getReportFilePath(), ScreenEnum.REPORT.toString());
    }

    /** This is the onLogOut method.
     This method is used to take the user to the login screen.
     @param event the event*/
    @FXML
    void onLogOut(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedAppointment();
        filePath.switchScreen(event, filePath.getLoginFilePath(), ScreenEnum.LOGIN.toString());
    }

    /** This is the onActionExitApplication method.
     This method is used to exit out of the application.
     @param event the event*/
    @FXML
    void onActionExitApplication(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    /** This is the onActionOpenAddAppointment method.
     This method takes the user to the add appointment screen.
     @param event the event*/
    @FXML
    void onActionOpenAddAppointment(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedAppointment();
        filePath.switchScreen(event, filePath.getAddAppointmentFilePath(), ScreenEnum.ADD_APPOINTMENT.toString());
    }

    /** This is the tableOnClicked method.
     This method is used to set the selected appointment once a row from the table view is clicked.
     @param event the event*/
    @FXML
    void tableOnClicked(MouseEvent event) {
        SelectedItem.setSelectedAppointment(appointmentsTableView.getSelectionModel().getSelectedItem());
    }

    /** This is the onClickUpdate method.
     This method is used to take the user to the update appointment screen.
     @param event the event*/
    @FXML
    void onClickUpdate(ActionEvent event) throws IOException {
        if(SelectedItem.getSelectedAppointment() != null) {
            filePath.switchScreen(event, filePath.getUpdateAppointmentFilePath(), ScreenEnum.UPDATE_APPOINTMENT.toString());
        } else {
            DialogBox.errorAlert("Error Dialog", "Error: You must select a row from the Appointments \ntable prior to selecting the Update button.");
        }
    }

    /** This is the onActionDelete method.
     This method is used to delete an appointment from the database.
     @param event the event*/
    @FXML
    void onActionDelete(ActionEvent event) throws SQLException {
        if(SelectedItem.getSelectedAppointment() == null) {
            DialogBox.errorAlert("Error Dialog", "Error: You need to select an appointment first \nprior to clicking the remove button");
            return;
        }

        Optional<ButtonType> result = DialogBox.confirmationAlert("Confirmation", "Are you sure you \nwant to cancel the appointment?");

        if(result.isPresent() && result.get() == ButtonType.CANCEL) {
            SelectedItem.clearSelectedAppointment();
            return;
        }

        String canceled = String.format("Appointment ID: %d  Type: %s was canceled", SelectedItem.getSelectedAppointment().getAppointmentID(), SelectedItem.getSelectedAppointment().getType());

        DialogBox.notificationAlert("Notification", canceled);

        SQLHelper.delete(AppointmentDAOImpl.TABLE_NAME, AppointmentDAOImpl.APPOINTMENT_ID_COLUMN_NAME, SelectedItem.getSelectedAppointment().getAppointmentID());
        SelectedItem.clearSelectedAppointment();

        //Check below is used to stop the bug when deleting an appointment and the table view being reset to view all
        if(sortByWeek.isSelected()) {
            setAppointmentsTableView(AppointmentFilter.getAppointmentsForCurrentWeek(AppointmentDAOImpl.getAppointmentsList()));
        } else if(sortByMonth.isSelected()) {
            setAppointmentsTableView(AppointmentFilter.getAppointmentsForCurrentMonth(AppointmentDAOImpl.getAppointmentsList()));
        } else {
            setAppointmentsTableView(AppointmentDAOImpl.getAppointmentsList());
        }
    }

    /** This is the onClickViewAll method.
     This method is used to view all appointments from the database in the table view.
     @param event the event*/
    @FXML
    void onClickViewAll(ActionEvent event) throws SQLException {
        if(!viewAll.isSelected()) {
            viewAll.setSelected(true);
        }
        //Prevents the other filter buttons to be activated at the same time
        sortByWeek.setSelected(false);
        sortByMonth.setSelected(false);
        setAppointmentsTableView(AppointmentDAOImpl.getAppointmentsList());
    }

    /** This is the onClickSortByWeek method.
     This method is used to filter the table view to only show appointments for the current week.
     Note: The current week is based on the time of the user's system.
     @param event the event*/
    @FXML
    void onClickSortByWeek(ActionEvent event) throws SQLException {
        if(!sortByWeek.isSelected()) {
            sortByWeek.setSelected(true);
        }
        //Prevents the other filter buttons to be activated at the same time
        sortByMonth.setSelected(false);
        viewAll.setSelected(false);
        setAppointmentsTableView(AppointmentFilter.getAppointmentsForCurrentWeek(AppointmentDAOImpl.getAppointmentsList()));
    }

    /** This is the onClickSortByMonth method.
     This method is used to filter the table view to only show appointments for the current month.
     Note: The current month is based on the time of the user's system.
     @param event the event*/
    @FXML
    void onClickSortByMonth(ActionEvent event) throws SQLException {
        if(!sortByMonth.isSelected()) {
            sortByMonth.setSelected(true);
        }
        //Prevents the other filter buttons to be activated at the same time
        sortByWeek.setSelected(false);
        viewAll.setSelected(false);
        setAppointmentsTableView(AppointmentFilter.getAppointmentsForCurrentMonth(AppointmentDAOImpl.getAppointmentsList()));
    }

    /** This is the initialize method.
     This method is used to initialize any values as soon as this screen loads, such as populating the table view with values from the appointments table,
     and alerting the user of any upcoming appointments within 15 minutes of them logging in.
     @param url the url
     @param rb the resource bundle*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setAppointmentsTableView(AppointmentDAOImpl.getAppointmentsList());
            upcomingAppointmentAlert.setText("Upcoming Appointment(s) within 15 minutes: " + UpcomingAppointment.upcomingAppointmentsStr(UpcomingAppointment.getUpcomingAppointments(AppointmentDAOImpl.getAppointmentsList())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}