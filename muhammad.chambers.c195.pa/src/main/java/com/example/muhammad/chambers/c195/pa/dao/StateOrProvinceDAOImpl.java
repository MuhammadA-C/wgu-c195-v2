package com.example.muhammad.chambers.c195.pa.dao;

import com.example.muhammad.chambers.c195.pa.model.StateOrProvince;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/** This class holds code to access the database for the First Level Division table.*/
public class StateOrProvinceDAOImpl {
    /** Holds a list of all the first level division objects from the database*/
    private static ObservableList<StateOrProvince> states = FXCollections.observableArrayList();


    /** This is the getStatesList method.
     This method sets the states list with values from the database prior to returning a list of first level division objects.
     @return Returns a list of first level division objects from the database
     @throws SQLException due to the SQL queries*/
    public static ObservableList<StateOrProvince> getStatesList() throws SQLException {
        /*
            States Observable list is only accessible through a getter method,
            which allows the program to populate the list with values from the database.
        */
        addAllStatesToListFromDatabase();

        return states;
    }

    /** This is the addAllStatesToListFromDatabase method.
     This method is used to pull the rows from the database from the first level division table, create first level division objects, and add them to the states list.
     @throws SQLException due to the SQL queries*/
    private static void addAllStatesToListFromDatabase() throws SQLException {
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        //Continues to loop through all data entries in the database until it reaches the last row
        while(rs.next()) {
            //Creates variables for the object from the database columns
            int divisionID = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int countryID = rs.getInt("Country_ID");

            StateOrProvince state = new StateOrProvince(divisionID, divisionName, createDate, createdBy, lastUpdate, lastUpdatedBy, countryID);

            //Adds state to list from database ONLY if not already present
            if(!isStateInList(state)) {
                states.add(state);
            }
        }
    }

    /** This is the isStateInList method.
     This method checks to see if a state is in the states list, and returns a boolean.
     @param state the first level division object to check to see if it's in the database
     @return Returns true if the first level division is found in the list, or false otherwise*/
    private static boolean isStateInList(StateOrProvince state) {
        /*
            Checks to see if the State object is already present
            in the states list by comparing state names.
         */
        boolean found = false;

        for(StateOrProvince i: states) {
            if(i.getState().equals(state.getState())) {
                found = true;
                break;
            }
        }
        return found;
    }

    /** This is the findStateInListByID method.
     This method checks to see if a stateID is in the states list, and returns the object.
     @param stateID the first level division id to check to see if it's in the database
     @return Returns a first level division object if found, or NULL otherwise*/
    public static StateOrProvince findStateInListByID(int stateID) {
        for(StateOrProvince state: states) {
            if(state.getStateID() == stateID) {
                return state;
            }
        }
        return null;
    }

    /** This is the findStateInListByName method.
     This method checks to see if a stateName is in the states list, and returns the object.
     @param stateName the first level division name to check to see if it's in the database
     @return Returns a first level division object if found, or NULL otherwise */
    public static StateOrProvince findStateInListByName(String stateName) {
        for(StateOrProvince state: states) {
            if(state.getState().equals(stateName)) {
                return state;
            }
        }
        return null;
    }
}
