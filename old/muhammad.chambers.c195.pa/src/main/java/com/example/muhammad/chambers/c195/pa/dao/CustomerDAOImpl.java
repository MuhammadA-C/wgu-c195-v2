package com.example.muhammad.chambers.c195.pa.dao;

import com.example.muhammad.chambers.c195.pa.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**  This class holds code to access the database for the customer table.*/
public class CustomerDAOImpl {
    /** Holds a list of customer objects*/
    private static ObservableList<Customer> customers = FXCollections.observableArrayList();
    /** Holds the table name in the database*/
    public final static String TABLE_NAME = "customers";
    /** Holds the customer id column name in the database*/
    public final static String CUSTOMER_ID_COLUMN_NAME = "Customer_ID";
    /** Holds the customer name column name in the database*/
    public final static String CUSTOMER_NAME_COL_NAME = "Customer_Name";
    /** Holds the address column name in the database*/
    public final static String ADDRESS_COL_NAME = "Address";
    /** Holds the postal code column name in the database*/
    public final static String POSTAL_CODE_COL_NAME = "Postal_Code";
    /** Holds the phone column name in the database*/
    public final static String PHONE_COL_NAME = "Phone";
    /** Holds the last update column name in the database*/
    public final static String LAST_UPDATE_COL_NAME = "Last_Update";
    /** Holds the last updated by column name in the database*/
    public final static String LAST_UPDATED_BY_COL_NAME = "Last_Updated_By";
    /** Holds the division id column name in the database*/
    public final static String DIVISION_ID_COL_NAME = "Division_ID";


    /** This is the getCustomersList method.
     This method sets the customers list with values from the database prior to returning a list of customer objects.
     @return Returns a list of customer objects from the database
     @throws SQLException due to the SQL queries*/
    public static ObservableList<Customer> getCustomersList() throws SQLException {
        /*
            Customers Observable list is only accessible through a getter method,
            which allows the program to populate the list with values from the database.
        */
        customers.clear();
        addAllCustomersToListFromDatabase();

        return customers;
    }

    /** This is the insert method.
     This method is used to add a customer object as a row to the database.
     @param customer the customer object to add as a row
     @return Returns an integer for the number of rows affected
     @throws SQLException due to the SQL queries*/
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

    /** This is the addAllCustomersToListFromDatabase method.
     This method is used to pull the rows from the database from the customer table, create user objects, and add them to the customers list.
     @throws SQLException due to the SQL queries*/
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

    /** This is the isCustomerInList method.
     This method checks to see if a customer is in the customers list, and returns a boolean.
     @param customer1 the customer object to check to see if it's in the database
     @return Returns true if the customer is found in the list, or false otherwise*/
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

    /** This is the isCustomerIDInList method.
     This method checks to see if a customerID is in the states list, and returns a boolean.
     @param customerID the customer id to check to see if it's in the database
     @return Returns true if the customer is found in the list, or false otherwise
     @throws SQLException due to the SQL queries*/
    public static boolean isCustomerIDInList(int customerID) throws SQLException {

        for(Customer customer: getCustomersList()) {
            if(customer.getCustomerID() == customerID) {
                return true;
            }
        }
        return false;
    }
}
