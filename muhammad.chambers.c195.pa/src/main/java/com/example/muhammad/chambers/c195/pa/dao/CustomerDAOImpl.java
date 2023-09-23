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

    public static int updateForStrColumn(int customerID, String columnName, String valueToUpdate) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = ? WHERE %s = ?", TABLE_NAME, columnName, CUSTOMER_ID_COLUMN_NAME);
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setString(1, valueToUpdate);
        ps.setInt(2, customerID);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static int updateForIntColumn(int customerID, String columnName, int valueToUpdate) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = ? WHERE %s = ?", TABLE_NAME, columnName, CUSTOMER_ID_COLUMN_NAME);
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setInt(1, valueToUpdate);
        ps.setInt(2, customerID);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static int updateForTimestampColumn(int customerID, String columnName, Timestamp valueToUpdate) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = ? WHERE %s = ?", TABLE_NAME, columnName, CUSTOMER_ID_COLUMN_NAME);
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setTimestamp(1, valueToUpdate);
        ps.setInt(2, customerID);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    private static void addAllCustomersToListFromDatabase() throws SQLException {
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phoneNumber = rs.getString("Phone");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int stateID = rs.getInt("Division_ID");

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
