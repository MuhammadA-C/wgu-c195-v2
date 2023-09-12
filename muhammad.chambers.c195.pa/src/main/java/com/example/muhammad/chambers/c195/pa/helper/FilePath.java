package com.example.muhammad.chambers.c195.pa.helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FilePath {
    private Stage stage;
    private Parent scene;


    //File Paths
    private static final String MAIN_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/main-view.fxml";
    private static final String LOGIN_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/login-view.fxml";
    private static final String CUSTOMER_RECORD_FILE_PATH= "/com/example/muhammad/chambers/c195/pa/customer-record-view.fxml";
    private static final String REPORT_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/report-view.fxml";
    private static final String ADD_APPOINTMENT_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/add-appointment-view.fxml";
    private static final String ADD_CUSTOMER_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/add-customer-view.fxml";
    private static final String UPDATE_CUSTOMER_FILE_PATH = "/com/example/muhammad/chambers/c195/pa/update-customer-view.fxml";


    //Getter methods
    public String getMainFilePath() {
        return MAIN_FILE_PATH;
    }

    public String getLoginFilePath() {
        return LOGIN_FILE_PATH;
    }

    public String getCustomerRecordFilePath() {
        return CUSTOMER_RECORD_FILE_PATH;
    }

    public String getReportFilePath() {
        return REPORT_FILE_PATH;
    }

    public String getAddAppointmentFilePath() {
        return ADD_APPOINTMENT_FILE_PATH;
    }

    public String getAddCustomerFilePath() {
        return ADD_CUSTOMER_FILE_PATH;
    }

    public String getUpdateCustomerFilePath() {
        return  UPDATE_CUSTOMER_FILE_PATH;
    }

    public void switchScreen(ActionEvent event, String filePath, String screenName) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(filePath));
        stage.setTitle(screenName);
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
