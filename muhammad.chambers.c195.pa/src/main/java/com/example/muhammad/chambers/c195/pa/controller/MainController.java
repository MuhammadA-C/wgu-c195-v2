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
    private RadioButton sortByWeek;
    @FXML
    private RadioButton sortByMonth;
    @FXML
    private RadioButton viewAll;
    @FXML
    private Text upcomingAppointmentAlert;


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
    void onClickUpdate(ActionEvent event) throws IOException {
        if(SelectedItem.getSelectedAppointment() != null) {
            filePath.switchScreen(event, filePath.getUpdateAppointmentFilePath(), ScreenEnum.UPDATE_APPOINTMENT.toString());
        } else {
            DialogBox.errorAlert("Error Dialog", "Error: You must select a row from the Appointments table prior to selecting the Update button.");
        }
    }

    /*
        Note: Need to add a message when an appointment is deleted with Appointment ID & type of appointment
     */
    @FXML
    void onActionDelete(ActionEvent event) throws SQLException {
        if(SelectedItem.getSelectedAppointment() == null) {
            DialogBox.errorAlert("Error Dialog", "Error: You need to select an appointment first prior to clicking the remove button");
            return;
        }

        Optional<ButtonType> result = DialogBox.confirmationAlert("Confirmation", "Are you sure you want to cancel the appointment?");

        if(result.isPresent() && result.get() == ButtonType.CANCEL) {
            SelectedItem.clearSelectedAppointment();
            return;
        }

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