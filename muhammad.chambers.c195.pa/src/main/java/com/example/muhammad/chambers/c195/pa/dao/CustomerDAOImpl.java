package com.example.muhammad.chambers.c195.pa.dao;

import com.example.muhammad.chambers.c195.pa.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CustomerDAOImpl {
    private static ObservableList<Customer> customers = FXCollections.observableArrayList();
    public final static String TABLE_NAME = "customers";
    public final static String CUSTOMER_ID_COLUMN_NAME = "Customer_ID";
    public final static String CUSTOMER_NAME_COL_NAME = "Customer_Name";
    public final static String ADDRESS_COL_NAME = "Address";
    public final static String POSTAL_CODE_COL_NAME = "Postal_Code";
    public final static String PHONE_COL_NAME = "Phone";
    public final static String LAST_UPDATE_COL_NAME = "Last_Update";
    public final static String LAST_UPDATED_BY_COL_NAME = "Last_Updated_By";
    public final static String DIVISION_ID_COL_NAME = "Division_ID";

    //Getter methods
    public static ObservableList<Customer> getCustomersList() throws SQLException {
        /*
            Customers Observable list is only accessible through a getter method,
            which allows the program to populate the list with values from the database.
        */
        customers.clear();
        addAllCustomersToListFromDatabase();

        return customers;
    }

    public static int insert(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setTimestamp(5, customer.getCreateDate());
        ps.setString(6, customer.getCreatedBy());
        ps.setTimestamp(7, customer.getLastUpdate());
        ps.setString(8, customer.getLastUpdatedBy());
        ps.setInt(9, customer.getStateID());

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    private static void addAllCustomersToListFromDatabase() throws SQLException {
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            int customerID = rs.getInt(CUSTOMER_ID_COLUMN_NAME);
            String customerName = rs.getString(CUSTOMER_NAME_COL_NAME);
            String address = rs.getString(ADDRESS_COL_NAME);
            String postalCode = rs.getString(POSTAL_CODE_COL_NAME);
            String phoneNumber = rs.getString(PHONE_COL_NAME);
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp(LAST_UPDATE_COL_NAME);
            String lastUpdatedBy = rs.getString(LAST_UPDATED_BY_COL_NAME);
            int stateID = rs.getInt(DIVISION_ID_COL_NAME);

            Customer customer = new Customer(customerName, address, postalCode, phoneNumber, stateID);

            customer.setCustomerID(customerID);
            customer.setCreatedBy(createdBy);
            customer.setLastUpdate(lastUpdate);
            customer.setLastUpdatedBy(lastUpdatedBy);
            customer.setCreateDate(createDate);

            //Adds customer to list from database ONLY if not already present
            if(!isCustomerInList(customer)) {
                customers.add(customer);
            }
        }
    }

    private static boolean isCustomerInList(Customer customer1) {
        /*
            Checks to see if the Customer object is already present
            in the customers list by comparing customer name, address, and postal code.
         */
        for(Customer customer2: customers) {
            if(Customer.areSame(customer1, customer2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCustomerIDInList(int customerID) throws SQLException {

        for(Customer customer: getCustomersList()) {
            if(customer.getCustomerID() == customerID) {
                return true;
            }
        }
        return false;
    }
}
