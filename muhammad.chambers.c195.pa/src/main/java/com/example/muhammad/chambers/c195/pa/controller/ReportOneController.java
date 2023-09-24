package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.AppointmentDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.JDBC;
import com.example.muhammad.chambers.c195.pa.helper.FilePath;
import com.example.muhammad.chambers.c195.pa.model.Appointment;
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

public class ReportOneController implements Initializable {

    private FilePath filePath = new FilePath();

    @FXML
    private TableView<ReportOne> reportOneTableView;
    @FXML
    private TableColumn<ReportOne, String> reportTypeCol;
    @FXML
    private TableColumn<ReportOne, String> reportMonthCol;
    @FXML
    private TableColumn<ReportOne, Integer> totalNumberCol;


    @FXML
    void onClickReports(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getReportFilePath(), ScreenEnum.REPORT.toString());
    }

    @FXML
    void onClickReportTwo(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getReportTwoFilePath(), ScreenEnum.REPORT_TWO.toString());
    }

    @FXML
    void onClickCustomerRecordBtn(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getCustomerRecordFilePath(), ScreenEnum.CUSTOMER_RECORD.toString());
    }

    @FXML
    void onClickMainBtn(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
    }

    @FXML
    void onLogOut(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getLoginFilePath(), ScreenEnum.LOGIN.toString());
    }

    @FXML
    void onActionExitApplication(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

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