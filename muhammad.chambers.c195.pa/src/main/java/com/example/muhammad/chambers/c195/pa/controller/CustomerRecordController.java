package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.AppointmentDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.CustomerDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.JDBC;
import com.example.muhammad.chambers.c195.pa.helper.FilePath;
import com.example.muhammad.chambers.c195.pa.helper.ScreenEnum;
import com.example.muhammad.chambers.c195.pa.helper.SelectedItem;
import com.example.muhammad.chambers.c195.pa.model.Appointment;
import com.example.muhammad.chambers.c195.pa.model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerRecordController implements Initializable {

    private FilePath filePath = new FilePath();

    //FXML Fields
    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;
    @FXML
    private TableColumn<Customer, String> customerNameColumn;
    @FXML
    private TableColumn<Customer, String> addressColumn;
    @FXML
    private TableColumn<Customer, String> postalCodeColumn;
    @FXML
    private TableColumn<Customer, String> phoneNumberColumn;
    @FXML
    private TableColumn<Customer, String> countryColumn;
    @FXML
    private TableColumn<Customer, String> stateColumn;


    @FXML
    void onClickMainBtn(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedCustomer();
        filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
    }

    @FXML
    void onClickReportBtn(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedCustomer();
        filePath.switchScreen(event, filePath.getReportFilePath(), ScreenEnum.REPORT.toString());
    }

    @FXML
    void onLogOut(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedCustomer();
        filePath.switchScreen(event, filePath.getLoginFilePath(), ScreenEnum.LOGIN.toString());
    }

    @FXML
    void onActionExitApplication(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    @FXML
    void onActionGoToAddCustomer(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedCustomer();
        filePath.switchScreen(event, filePath.getAddCustomerFilePath(), ScreenEnum.ADD_CUSTOMER.toString());
    }

    @FXML
    void onActionUpdate(ActionEvent event) throws IOException {
        if(SelectedItem.getSelectedCustomer() != null) {
            filePath.switchScreen(event, filePath.getUpdateCustomerFilePath(), ScreenEnum.UPDATE_CUSTOMER.toString());
        }
        System.out.println("Error: You must select a row from the Customer records table prior to selecting the Update button.");
    }

    private boolean doesCustomerIDHaveAppointments(int customerID) throws SQLException {
        for(Appointment appointment: AppointmentDAOImpl.getAppointmentsList()) {
            if(appointment.getCustomerID() == customerID) {
                return true;
            }
        }
        return false;
    }

    @FXML
    void onActionDelete(ActionEvent event) throws SQLException {
        if(SelectedItem.getSelectedCustomer() == null) {
            System.out.println("Error: You must select a row from the Customer records table prior to selecting the Remove button.");
        } else if(doesCustomerIDHaveAppointments(SelectedItem.getSelectedCustomer().getCustomerID())) {
            SelectedItem.clearSelectedCustomer();
            System.out.println("Error: You must delete ALL appointments for a customer prior to deleting the customer.");
        } else {
            CustomerDAOImpl.delete(SelectedItem.getSelectedCustomer().getCustomerID());
            //Need to reset this otherwise it will still have reference to an object which is supposed to be deleted
            SelectedItem.clearSelectedCustomer();
            //Need to set the table view to update it
            customerTableView.setItems(CustomerDAOImpl.getCustomersList());
        }
    }

    @FXML
    public void tableOnClicked(MouseEvent mouseEvent) {
        SelectedItem.setSelectedCustomer(customerTableView.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Note: If a column is empty in the database then it will throw an error in the application! Need to fix by adding a check if value is null
        try {
            customerTableView.setItems(CustomerDAOImpl.getCustomersList());

            //Sets the values for each column in the table
            customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
            countryColumn.setCellValueFactory(new PropertyValueFactory<>("countryName"));
            stateColumn.setCellValueFactory(new PropertyValueFactory<>("stateName"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}