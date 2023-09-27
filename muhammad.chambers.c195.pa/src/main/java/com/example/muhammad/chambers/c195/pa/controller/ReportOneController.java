package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.JDBC;
import com.example.muhammad.chambers.c195.pa.helper.FilePath;
import com.example.muhammad.chambers.c195.pa.model.ReportOne;
import com.example.muhammad.chambers.c195.pa.helper.ScreenEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** This class holds the code for the report one fxml file*/
public class ReportOneController implements Initializable {
    /** Holds a reference to the file path class*/
    private FilePath filePath = new FilePath();
    /** Holds the report one table view*/
    @FXML
    private TableView<ReportOne> reportOneTableView;
    /** Holds the report type column*/
    @FXML
    private TableColumn<ReportOne, String> reportTypeCol;
    /** Holds the report month column*/
    @FXML
    private TableColumn<ReportOne, String> reportMonthCol;
    /** Holds the total number column*/
    @FXML
    private TableColumn<ReportOne, Integer> totalNumberCol;


    /** This is the onClickReports method.
     This method is used to take the user to the reports screen.
     @param event the event*/
    @FXML
    void onClickReports(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getReportFilePath(), ScreenEnum.REPORT.toString());
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
     This method is used to initialize values as soon as the screen is loaded such as setting the table view.
     @param url the url
     @param rb the resource bundle*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            reportOneTableView.setItems(ReportOne.getReportsList());
            reportTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            reportMonthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
            totalNumberCol.setCellValueFactory(new PropertyValueFactory<>("totalNumber"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}