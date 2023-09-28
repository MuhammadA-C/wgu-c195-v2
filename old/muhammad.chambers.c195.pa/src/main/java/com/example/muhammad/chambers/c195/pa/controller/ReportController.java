package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.JDBC;
import com.example.muhammad.chambers.c195.pa.helper.FilePath;
import com.example.muhammad.chambers.c195.pa.helper.ScreenEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class holds the code for the reports fxml file*/
public class ReportController implements Initializable {
    /** Holds a reference to the file path class*/
    private FilePath filePath = new FilePath();

    /** This is the onClickReportOne method.
     This method is used to take the user to the report one screen.
     @param event the event*/
    @FXML
    void onClickReportOne(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getReportOneFilePath(), ScreenEnum.REPORT_ONE.toString());
    }

    /** This is the onClickReportTwo method.
     This method is used to take the user to the report two screen.
     @param event the event*/
    @FXML
    void onClickReportTwo(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getReportTwoFilePath(), ScreenEnum.REPORT_TWO.toString());
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
     This method is used to initialize any values as soon as this screen loads.
     @param url the url
     @param rb the resource bundle*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Nothing to initialize
    }
}