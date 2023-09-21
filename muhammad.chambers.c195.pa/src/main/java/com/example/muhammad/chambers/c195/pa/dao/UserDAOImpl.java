package com.example.muhammad.chambers.c195.pa.dao;

import com.example.muhammad.chambers.c195.pa.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class UserDAOImpl {
    //This class will only be used to pull the data from the database and not creating new objets
    private static ObservableList<User> users = FXCollections.observableArrayList();


    //Getter methods
    public static ObservableList<User> getUsersList() throws SQLException {
        /*
            Users Observable list is only accessible through a getter method,
            which allows the program to populate the list with values from the database.
        */
        addAllUsersToListFromDatabase();

        return users;
    }

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

    public static boolean isUserIDInList(int userID) throws SQLException {

        for(User user: getUsersList()) {
            if(user.getUserID() == userID) {
                return true;
            }
        }
        return false;
    }
}
