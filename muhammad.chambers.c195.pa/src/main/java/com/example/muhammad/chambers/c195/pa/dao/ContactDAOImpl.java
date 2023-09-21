package com.example.muhammad.chambers.c195.pa.dao;

import com.example.muhammad.chambers.c195.pa.helper.InputValidation;
import com.example.muhammad.chambers.c195.pa.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ContactDAOImpl {
    //This class will only be used to pull the data from the database and not creating new objets
    private static ObservableList<Contact> contacts = FXCollections.observableArrayList();


    //Getter methods
    public static ObservableList<Contact> getContactsList() throws SQLException {
        /*
            Contacts Observable list is only accessible through a getter method,
            which allows the program to populate the list with values from the database.
        */
        addAllContactsToListFromDatabase();

        return contacts;
    }

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

    private static boolean isContactInList(Contact contact) throws SQLException {
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

    public static String findContactInListByID(int contactID) throws SQLException {
        String name = InputValidation.NOT_FOUND;

        for(Contact contact: getContactsList()) {
            if(contact.getContactID() == contactID) {
                name = contact.getContactName();
            }
        }
        return name;
    }

}
