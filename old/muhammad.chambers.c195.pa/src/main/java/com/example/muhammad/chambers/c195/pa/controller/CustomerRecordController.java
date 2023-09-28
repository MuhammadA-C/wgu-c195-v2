package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.AppointmentDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.CustomerDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.JDBC;
import com.example.muhammad.chambers.c195.pa.dao.SQLHelper;
import com.example.muhammad.chambers.c195.pa.helper.*;
import com.example.muhammad.chambers.c195.pa.model.Appointment;
import com.example.muhammad.chambers.c195.pa.model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class holds the code for the customer record fxml file*/
public class CustomerRecordController implements Initializable {
    /** Holds a reference to the file path class*/
    private FilePath filePath = new FilePath();

    /** Holds the customer table view*/
    @FXML
    private TableView<Customer> customerTableView;
    /** Holds the customer id column*/
    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;
    /** Holds the customer name column*/
    @FXML
    private TableColumn<Customer, String> customerNameColumn;
    /** Holds the address column*/
    @FXML
    private TableColumn<Customer, String> addressColumn;
    /** Holds the postal code column*/
    @FXML
    private TableColumn<Customer, String> postalCodeColumn;
    /** Holds the phone number column*/
    @FXML
    private TableColumn<Customer, String> phoneNumberColumn;
    /** Holds the country column*/
    @FXML
    private TableColumn<Customer, String> countryColumn;
    /** Holds the state column*/
    @FXML
    private TableColumn<Customer, String> stateColumn;


    /** This is the onClickMainBtn method.
     This method is used to take the user to the main screen.
     @param event the event
     @throws */
    @FXML
    void onClickMainBtn(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedCustomer();
        filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
    }

    /** This is the onClickReportBtn method.
     This method is used to take the user to the report screen.
     @param event the event
     @throws */
    @FXML
    void onClickReportBtn(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedCustomer();
        filePath.switchScreen(event, filePath.getReportFilePath(), ScreenEnum.REPORT.toString());
    }

    /** This is the onLogOut method.
     This method is used to take the user to the login screen.
     @param event the event
     @throws */
    @FXML
    void onLogOut(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedCustomer();
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

    /** This is the onActionGoToAddCustomer method.
     This method is used to take the user to the add customer screen.
     @param event the event
     @throws */
    @FXML
    void onActionGoToAddCustomer(ActionEvent event) throws IOException {
        SelectedItem.clearSelectedCustomer();
        filePath.switchScreen(event, filePath.getAddCustomerFilePath(), ScreenEnum.ADD_CUSTOMER.toString());
    }

    /** This is the onActionUpdate method.
     This method is used to take the user to the update customer screen.
     @param event the event
     @throws */
    @FXML
    void onActionUpdate(ActionEvent event) throws IOException {
        if(SelectedItem.getSelectedCustomer() != null) {
            filePath.switchScreen(event, filePath.getUpdateCustomerFilePath(), ScreenEnum.UPDATE_CUSTOMER.toString());
        } else {
            DialogBox.errorAlert("Error Dialog", "Error: You must select a row from the Customer records table prior to selecting the Update button.");
        }
    }


    /** This is the onActionDelete method.
     This method is used to delete a customer record. Note: A customer record is only deleted if the customer id
     does not have any appointments. So, if a customer id has appointments the user will be asked if they want to delete all appointments associated
     to the customer id. Lambda expression was used for the canceled appointment output message to reduce repeating code.
     @param event the event
     @throws */
    @FXML
    void onActionDelete(ActionEvent event) throws SQLException {
        if(SelectedItem.getSelectedCustomer() == null) {
            DialogBox.errorAlert("Error Dialog", "Error: You must select a row from the Customer records table prior to selecting the Remove button.");
            return;
        }

        //Deletes customer record if it does not have any appointments
        if(!AppointmentDAOImpl.doesCustomerIDHaveAnyAppointments(SelectedItem.getSelectedCustomer().getCustomerID())) {
            String deletedCustomer = String.format("Customer ID: %d, Customer Name: %s, customer record was deleted", SelectedItem.getSelectedCustomer().getCustomerID(), SelectedItem.getSelectedCustomer().getCustomerName());
            DialogBox.notificationAlert("Notification", deletedCustomer);

            SQLHelper.delete(CustomerDAOImpl.TABLE_NAME, CustomerDAOImpl.CUSTOMER_ID_COLUMN_NAME, SelectedItem.getSelectedCustomer().getCustomerID());
            //Need to reset this otherwise it will still have reference to an object which is supposed to be deleted
            SelectedItem.clearSelectedCustomer();
            //Need to set the table view to update it
            customerTableView.setItems(CustomerDAOImpl.getCustomersList());
            return;
        }

        Optional<ButtonType> result = DialogBox.confirmationAlert("Confirmation", "Customer ID: " + SelectedItem.getSelectedCustomer().getCustomerID() + " has appointments.\nConfirm if you want to delete ALL of the appointments belonging to Customer ID: " + SelectedItem.getSelectedCustomer().getCustomerID() + "\nalong with deleting the customer");

        if(result.isPresent() && result.get() == ButtonType.CANCEL) {
            SelectedItem.clearSelectedCustomer();
            return;
        }

        String deletedAppointments = "";
        int count = 0;

        for(Appointment appointment : AppointmentDAOImpl.getAppointmentsList()) {
            if(appointment.getCustomerID() == SelectedItem.getSelectedCustomer().getCustomerID()) {
                SQLHelper.delete(AppointmentDAOImpl.TABLE_NAME, AppointmentDAOImpl.APPOINTMENT_ID_COLUMN_NAME, appointment.getAppointmentID());

                //Lambda Expression#1
                CanceledCustomerInterface canceledMessage = (id , type) -> "Appointment ID: " + id + " Type: " + type + " was canceled\n";
                
                if(count == 0) {
                    deletedAppointments.trim();
                    deletedAppointments = canceledMessage.canceledAppointmentAndDeletedCustomer(appointment.getAppointmentID(), appointment.getType());
                } else {
                    deletedAppointments += canceledMessage.canceledAppointmentAndDeletedCustomer(appointment.getAppointmentID(), appointment.getType());
                }
                count++;
            }
        }

        if(!deletedAppointments.isEmpty()) {
            DialogBox.notificationAlert("Notification", deletedAppointments);
        }

        String deletedCustomer = String.format("Customer ID: %d, Customer Name: %s, customer record was deleted", SelectedItem.getSelectedCustomer().getCustomerID(), SelectedItem.getSelectedCustomer().getCustomerName());
        DialogBox.notificationAlert("Notification", deletedCustomer);

        SQLHelper.delete(CustomerDAOImpl.TABLE_NAME, CustomerDAOImpl.CUSTOMER_ID_COLUMN_NAME, SelectedItem.getSelectedCustomer().getCustomerID());
        //Need to reset this otherwise it will still have reference to an object which is supposed to be deleted
        SelectedItem.clearSelectedCustomer();
        //Need to set the table view to update it
        customerTableView.setItems(CustomerDAOImpl.getCustomersList());
    }

    /** This is the tableOnClicked method.
     This method is used to set the selected customer variable with a reference to the row selected on the customer table view.
     @param mouseEvent the mouse event*/
    @FXML
    public void tableOnClicked(MouseEvent mouseEvent) {
        SelectedItem.setSelectedCustomer(customerTableView.getSelectionModel().getSelectedItem());
    }

    /** This is the initialize method.
     This method is used to initialize any values once the screen loads.In this case, the table is populated with customer data from the database.
     @param url the url
     @param rb the resource bundle*/
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