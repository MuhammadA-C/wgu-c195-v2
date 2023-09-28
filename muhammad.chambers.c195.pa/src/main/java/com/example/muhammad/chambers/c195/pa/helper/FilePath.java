package com.example.muhammad.chambers.c195.pa.helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/** This class is used to contain the file paths for the different screens, and methods pertaining to switching screens.*/
public class FilePath {
    /** Holds the stage*/
    private Stage stage;
    /** Holds the scene*/
    private Parent scene;
    /** Holds the main menu screen file path*/
    private static final String MAIN_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/main-view.fxml";
    /** Holds the login screen file path*/
    private static final String LOGIN_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/login-view.fxml";
    /** Holds the customer record screen file path*/
    private static final String CUSTOMER_RECORD_FILE_PATH= "/com/example/muhammad/chambers/c195/pa/customer-record-view.fxml";
    /** Holds the report screen file path*/
    private static final String REPORT_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/report-view.fxml";
    /** Holds the add appointment screen file path*/
    private static final String ADD_APPOINTMENT_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/add-appointment-view.fxml";
    /** Holds the add customer screen file path*/
    private static final String ADD_CUSTOMER_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/add-customer-view.fxml";
    /** Holds the update customer screen file path*/
    private static final String UPDATE_CUSTOMER_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/update-customer-view.fxml";
    /** Holds the update appointment screen file path*/
    private static final String UPDATE_APPOINTMENT_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/update-appointment-view.fxml";
    /** Holds the report one screen file path*/
    private static final String REPORT_ONE_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/report-one-view.fxml";
    /** Holds the report two screen file path*/
    private static final String REPORT_TWO_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/report-two-view.fxml";
    /** Holds the report three screen file path*/
    private static final String REPORT_THREE_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/report-three-view.fxml";


    /** This is the getMainFilePath method.
     This method returns the main menu file path field.
     @return Returns the main menu file path*/
    public String getMainFilePath() {
        return MAIN_FILE_PATH;
    }

    /** This is the getLoginFilePath method.
     This method returns the login file path field.
     @return Returns the login file path*/
    public String getLoginFilePath() {
        return LOGIN_FILE_PATH;
    }

    /** This is the getCustomerRecordFilePath method.
     This method returns the customer record file path field.
     @return Returns the customer record file path*/
    public String getCustomerRecordFilePath() {
        return CUSTOMER_RECORD_FILE_PATH;
    }

    /** This is the getReportFilePath method.
     This method returns the report file path field.
     @return Returns the report file path*/
    public String getReportFilePath() {
        return REPORT_FILE_PATH;
    }

    /** This is the getAddAppointmentFilePath method.
     This method returns the appointment file path field.
     @return Returns the appointment file path*/
    public String getAddAppointmentFilePath() {
        return ADD_APPOINTMENT_FILE_PATH;
    }

    /** This is the getAddCustomerFilePath method.
     This method returns the add customer file path field.
     @return Returns the add customer file path*/
    public String getAddCustomerFilePath() {
        return ADD_CUSTOMER_FILE_PATH;
    }

    /** This is the getUpdateCustomerFilePath method.
     This method returns the update customer file path field.
     @return Returns the add update customer file path*/
    public String getUpdateCustomerFilePath() {
        return UPDATE_CUSTOMER_FILE_PATH;
    }

    /** This is the getUpdateAppointmentFilePath method.
     This method returns the update appointment file path field.
     @return Returns the add update appointment file path*/
    public String getUpdateAppointmentFilePath() {
        return UPDATE_APPOINTMENT_FILE_PATH;
    }

    /** This is the getReportOneFilePath method.
     This method returns the report one file path field.
     @return Returns the add report one file path*/
    public String getReportOneFilePath() {
        return REPORT_ONE_FILE_PATH;
    }

    /** This is the getReportTwoFilePath method.
     This method returns the report two file path field.
     @return Returns the add report two file path*/
    public String getReportTwoFilePath() {
        return REPORT_TWO_FILE_PATH;
    }

    /** This is the getReportThreeFilePath method.
     This method returns the report three file path field.
     @return Returns the add report three file path*/
    public String getReportThreeFilePath() {
        return REPORT_THREE_FILE_PATH;
    }

    /** This is the switchScreen method.
     This method switches to another screen.
     @param event the event
     @param filePath filepath for the screen
     @param screenName name of the screen
     @throws IOException due to scene switching*/
    public void switchScreen(ActionEvent event, String filePath, String screenName) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(filePath));
        stage.setTitle(screenName);
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
