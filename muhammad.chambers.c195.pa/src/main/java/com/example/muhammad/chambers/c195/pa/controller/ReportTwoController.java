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

public class ReportTwoController implements Initializable {
    private FilePath filePath = new FilePath();
    private ObservableList<ReportTwo> reportsList = FXCollections.observableArrayList();

    @FXML
    private TableView<ReportTwo> reportTwoTableView;
    @FXML
    private TableColumn<ReportTwo, String> descriptionCol;
    @FXML
    private TableColumn<ReportTwo, String> titleCol;
    @FXML
    private TableColumn<ReportTwo, String> typeCol;
    @FXML
    private TableColumn<ReportTwo, Timestamp> startCol;
    @FXML
    private TableColumn<ReportTwo, Timestamp> endCol;
    @FXML
    private TableColumn<ReportTwo, Integer> customerIdCol;
    @FXML
    private TableColumn<ReportTwo, Integer> appointmentIdCol;
    @FXML
    private ComboBox<Contact> contactComboBox;


    private void setContactComboBox() throws SQLException {
        contactComboBox.setItems(ContactDAOImpl.getContactsList());
    }

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
            DialogBox.errorAlert("Notification", "The Contact that you selected does not have any appointments associated to them.");
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

    @FXML
    void onClickReports(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getReportFilePath(), ScreenEnum.REPORT.toString());
    }

    @FXML
    void onClickReportOne(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getReportOneFilePath(), ScreenEnum.REPORT_ONE.toString());
    }

    @FXML
    void onClickReportThree(ActionEvent event) throws IOException {
        filePath.switchScreen(event, filePath.getReportThreeFilePath(), ScreenEnum.REPORT_THREE.toString());
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
            setContactComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}