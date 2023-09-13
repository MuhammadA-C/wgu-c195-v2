package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.AppointmentDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.JDBC;
import com.example.muhammad.chambers.c195.pa.helper.FilePath;
import com.example.muhammad.chambers.c195.pa.helper.ScreenEnum;
import com.example.muhammad.chambers.c195.pa.helper.SelectedItem;
import com.example.muhammad.chambers.c195.pa.model.Appointment;
import com.example.muhammad.chambers.c195.pa.model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private FilePath filePath = new FilePath();

    @FXML
    private TableView<Appointment> appointmentsTableView;
    @FXML
    private TableColumn<Appointment, Integer> appointmentIdCol;
    @FXML
    private TableColumn<Customer, String> titleCol;
    @FXML
    private TableColumn<Customer, String> descriptionCol;
    @FXML
    private TableColumn<Customer, String> locationCol;
    @FXML
    private TableColumn<Customer, String> contactCol;
    @FXML
    private TableColumn<Customer, String> typeCol;
    @FXML
    private TableColumn<Customer, String> startCol;
    @FXML
    private TableColumn<Customer, String> endCol;
    @FXML
    private TableColumn<Customer, Integer> customerIdCol;
    @FXML
    private TableColumn<Customer, Integer> userIdCol;


    @FXML
    void onClickCustomerRecordBtn(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedAppointment();
        filePath.switchScreen(event, filePath.getCustomerRecordFilePath(), ScreenEnum.CUSTOMER_RECORD.toString());
    }

    @FXML
    void onClickReportBtn(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedAppointment();
        filePath.switchScreen(event, filePath.getReportFilePath(), ScreenEnum.REPORT.toString());
    }

    @FXML
    void onLogOut(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedAppointment();
        filePath.switchScreen(event, filePath.getLoginFilePath(), ScreenEnum.LOGIN.toString());
    }

    @FXML
    void onActionExitApplication(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    @FXML
    void onActionOpenAddAppointment(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedAppointment();
        filePath.switchScreen(event, filePath.getAddAppointmentFilePath(), ScreenEnum.ADD_APPOINTMENT.toString());
    }

    @FXML
    void tableOnClicked(MouseEvent event) {
        SelectedItem.setSelectedAppointment(appointmentsTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActionDelete(ActionEvent event) throws SQLException {
        if(SelectedItem.getSelectedAppointment() == null) {
            System.out.println("You need to select an appointment first prior to clicking the remove button");
            return;
        }

        //Need to add a confirmation check to verify if they want to delete
        AppointmentDAOImpl.delete(SelectedItem.getSelectedAppointment().getAppointmentID());
        SelectedItem.clearSelectedAppointment();
        appointmentsTableView.setItems(AppointmentDAOImpl.getAppointmentsList());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            appointmentsTableView.setItems(AppointmentDAOImpl.getAppointmentsList());

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }






}