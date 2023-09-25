package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.JDBC;
import com.example.muhammad.chambers.c195.pa.helper.FilePath;
import com.example.muhammad.chambers.c195.pa.helper.ScreenEnum;
import com.example.muhammad.chambers.c195.pa.model.ReportThree;
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

public class ReportThreeController implements Initializable {

    private FilePath filePath = new FilePath();

    @FXML
    private TableView<ReportThree> reportThreeTableView;
    @FXML
    private TableColumn<ReportThree, Integer> customerIdCol;
    @FXML
    private TableColumn<ReportThree, String> monthCol;
    @FXML
    private TableColumn<ReportThree, Integer> totalNumberCol;


    @FXML
    void onClickReports(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getReportFilePath(), ScreenEnum.REPORT.toString());
    }

    @FXML
    void onClickReportTwo(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getReportTwoFilePath(), ScreenEnum.REPORT_TWO.toString());
    }

    @FXML
    void onClickReportOne(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getReportOneFilePath(), ScreenEnum.REPORT_ONE.toString());
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
            reportThreeTableView.setItems(ReportThree.getReportsList());
            customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
            totalNumberCol.setCellValueFactory(new PropertyValueFactory<>("totalNumber"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}