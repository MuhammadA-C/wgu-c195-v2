package com.example.muhammad.chambers.c195.pa.dao;

import com.example.muhammad.chambers.c195.pa.helper.InputValidation;
import com.example.muhammad.chambers.c195.pa.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/** This class holds code to access the database for the contact table.*/
public class ContactDAOImpl {
    /** Holds a list of contact objects*/
    private static ObservableList<Contact> contacts = FXCollections.observableArrayList();


    /** This is the getContactsList method.
     This method sets the contacts list with values from the database prior to returning a list of contact objects.
     @return Returns a list of contact objects from the database
     @throws SQLException due to the SQL queries*/
    public static ObservableList<Contact> getContactsList() throws SQLException {
        /*
            Contacts Observable list is only accessible through a getter method,
            which allows the program to populate the list with values from the database.
        */
        addAllContactsToListFromDatabase();

        return contacts;
    }

    /** This is the addAllContactsToListFromDatabase method.
     This method is used to pull the rows from the database from the contact table, create contact objects, and add them to the contacts list.
     @throws SQLException due to the SQL queries*/
    private static void addAllContactsToListFromDatabase() throws SQLException {
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        //Continues to loop through all data entries in the database until it reaches the last row
        while(rs.next()) {
            //Creates variables for the contact object from the database columns
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            Contact contact = new Contact(contactID, contactName, email);

            //Adds contact to list from database ONLY if not already present
            if(!isContactInList(contact)) {
                contacts.add(contact);
            }
        }
    }

    /** This is the isContactInList method.
     This method checks to see if a contact is in the contacts list, and returns a boolean.
     @param contact the contact object to check to see if it's in the database
     @return Returns true if the contact is found in the list, or false otherwise*/
    private static boolean isContactInList(Contact contact) {
        /*
            Checks to see if the Contact object is already present
            in the contacts list by comparing contact names.
         */
        boolean found = false;

        for(Contact i: contacts) {
            if(i.getContactName().equals(contact.getContactName())) {
                found = true;
                break;
            }
        }
        return found;
    }

    /** This is the findContactInListByID method.
     This method checks to see if a contactID is in the contacts list, and returns the contacts name.
     @param contactID the country id to check to see if it's in the database
     @return Returns the name of the contact if found, or Not Found
     @throws SQLException due to the SQL queries*/
    public static String findContactInListByID(int contactID) throws SQLException {
        String name = InputValidation.NOT_FOUND;

        for(Contact contact: getContactsList()) {
            if(contact.getContactID() == contactID) {
                name = contact.getContactName();
            }
        }
        return name;
    }

    /** This is the getContactInList method.
     This method is used to retrieve a contact object from the contacts list by the contactID.
     @param contactID the contactID of the contact object you want to retrieve
     @return Returns a contact object if found, or NULL otherwise
     @throws SQLException due to the SQL queries*/
    public static Contact getContactInList(int contactID) throws SQLException {
        for(Contact contact: getContactsList()) {
            if(contact.getContactID() == contactID) {
                return contact;
            }
        }
        return null;
    }
}
