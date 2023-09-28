package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.AppointmentDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.ContactDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.JDBC;
import com.example.muhammad.chambers.c195.pa.helper.DialogBox;
import com.example.muhammad.chambers.c195.pa.helper.FilePath;
import com.example.muhammad.chambers.c195.pa.helper.ScreenEnum;
import com.example.muhammad.chambers.c195.pa.model.Appointment;
import com.example.muhammad.chambers.c195.pa.model.Contact;
import com.example.muhammad.chambers.c195.pa.model.ReportTwo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/** This class holds the code for the report two fxml file*/
public class ReportTwoController implements Initializable {
    /** Holds a reference to the file path class*/
    private FilePath filePath = new FilePath();
    /** Holds the reportsList*/
    private ObservableList<ReportTwo> reportsList = FXCollections.observableArrayList();
    /** Holds the report two table view*/
    @FXML
    private TableView<ReportTwo> reportTwoTableView;
    /** Holds the description column*/
    @FXML
    private TableColumn<ReportTwo, String> descriptionCol;
    /** Holds the title column*/
    @FXML
    private TableColumn<ReportTwo, String> titleCol;
    /** Holds the type column*/
    @FXML
    private TableColumn<ReportTwo, String> typeCol;
    /** Holds the start column*/
    @FXML
    private TableColumn<ReportTwo, Timestamp> startCol;
    /** Holds the end column*/
    @FXML
    private TableColumn<ReportTwo, Timestamp> endCol;
    /** Holds the customer id column*/
    @FXML
    private TableColumn<ReportTwo, Integer> customerIdCol;
    /** Holds the appointment id column*/
    @FXML
    private TableColumn<ReportTwo, Integer> appointmentIdCol;
    /** Holds the contact combo box*/
    @FXML
    private ComboBox<Contact> contactComboBox;


    /** This is the setContactComboBox method.
     This method is used to set the contact combo box values with the list of contacts from the database.*/
    private void setContactComboBox() throws SQLException {
        contactComboBox.setItems(ContactDAOImpl.getContactsList());
    }

    /** This is the contactSelected method.
     This method is used to set the table view based on the contact selected.
     @param event the event*/
    @FXML
    void contactSelected(ActionEvent event) throws SQLException {
        if(contactComboBox.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        reportsList.clear();

        for(Appointment appointment : AppointmentDAOImpl.getAppointmentsList()) {
            if(appointment.getContactName().equals(contactComboBox.getSelectionModel().getSelectedItem().getContactName())) {
                ReportTwo report = new ReportTwo(appointment.getAppointmentID(), appointment.getCustomerID(), appointment.getType(), appointment.getTitle(), appointment.getContactName(), appointment.getDescription(), appointment.getStart(), appointment.getEnd());

                reportsList.add(report);
            }
        }

        if(reportsList.size() == 0) {
            DialogBox.notificationAlert("Notification", "The Contact that you selected does not have any appointments associated to them.");
            return;
        }

        reportTwoTableView.setItems(reportsList);
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
    }

    /** This is the onClickReports method.
     This method is used to take the user to the reports screen.
     @param event the event*/
    @FXML
    void onClickReports(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getReportFilePath(), ScreenEnum.REPORT.toString());
    }

    /** This is the onClickReportOne method.
     This method is used to take the user to the report one screen.
     @param event the event*/
    @FXML
    void onClickReportOne(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getReportOneFilePath(), ScreenEnum.REPORT_ONE.toString());
    }

    /** This is the onClickReportThree method.
     This method is used to take the user to the report three screen.
     @param event the event*/
    @FXML
    void onClickReportThree(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getReportThreeFilePath(), ScreenEnum.REPORT_THREE.toString());
    }

    /** This is the onClickCustomerRecordBtn method.
     This method is used to take the user to the customer record screen.
     @param event the event*/
    @FXML
    void onClickCustomerRecordBtn(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getCustomerRecordFilePath(), ScreenEnum.CUSTOMER_RECORD.toString());
    }

    /** This is the onClickMainBtn method.
     This method is used to take the user to the main screen.
     @param event the event*/
    @FXML
    void onClickMainBtn(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
    }

    /** This is the onLogOut method.
     This method is used to take the user to the login screen.
     @param event the event*/
    @FXML
    void onLogOut(ActionEvent event) throws IOException {
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

    /** This is the initialize method.
     This method is used to initialize values as soon as the screen is loaded such as setting the contact combo box values.
     @param url the url
     @param rb the resource bundle*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setContactComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}