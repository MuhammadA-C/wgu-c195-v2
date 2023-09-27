package com.example.muhammad.chambers.c195.pa.dao;

import com.example.muhammad.chambers.c195.pa.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/** This class holds code to access the database for the User table.*/
public class UserDAOImpl {
    /** Holds a list of all the user objects from the database*/
    private static ObservableList<User> users = FXCollections.observableArrayList();


    /** This is the getUsersList method.
     This method sets the user list with values from the database prior to returning a list of user objects.
     @return Returns a list of user objects from the database*/
    public static ObservableList<User> getUsersList() throws SQLException {
        /*
            Users Observable list is only accessible through a getter method,
            which allows the program to populate the list with values from the database.
        */
        addAllUsersToListFromDatabase();

        return users;
    }

    /** This is the addAllUsersToListFromDatabase method.
     This method is used to pull the rows from the database from the user table, create user objects, and add them to the users list.*/
    private static void addAllUsersToListFromDatabase() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        //Continues to loop through all data entries in the database until it reaches the last row
        while(rs.next()) {
            //Creates variables for the user object from the database columns
            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            User user = new User(userID, userName, password, createDate, createdBy, lastUpdate, lastUpdatedBy);

            //Adds user to list from database ONLY if the username is not already present in the list
            if(!isUserInList(user)) {
                users.add(user);
            }
        }
    }

    /** This is the isUserInList method.
     This method checks to see if a user is in the users list, and returns a boolean.
     @param user the user object to check to see if it's in the database
     @return Returns true if the user is found in the list, or false otherwise*/
    private static boolean isUserInList(User user) {
        /*
            Checks to see if the User object is already present
            in the users list by comparing usernames.
         */
        boolean found = false;

        for(User i: users) {
            if(i.getUserName().equals(user.getUserName())) {
                found = true;
                break;
            }
        }
        return found;
    }

    /** This is the isUserIDInList method.
     This method checks to see if a user is in the users list by userID, and returns a boolean.
     @param userID the userID to check to see if it's in the database
     @return Returns true if the user is found in the list, or false otherwise*/
    public static boolean isUserIDInList(int userID) throws SQLException {

        for(User user: getUsersList()) {
            if(user.getUserID() == userID) {
                return true;
            }
        }
        return false;
    }
}
