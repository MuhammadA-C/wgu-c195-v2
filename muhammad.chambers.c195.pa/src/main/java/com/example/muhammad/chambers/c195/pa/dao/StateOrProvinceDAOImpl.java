package com.example.muhammad.chambers.c195.pa.dao;

import com.example.muhammad.chambers.c195.pa.model.StateOrProvince;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class StateOrProvinceDAOImpl {
    //This class will only be used to pull the data from the database and not creating new objets
    private static ObservableList<StateOrProvince> states = FXCollections.observableArrayList();


    //Getter methods
    public static ObservableList<StateOrProvince> getStatesList() throws SQLException {
        /*
            States Observable list is only accessible through a getter method,
            which allows the program to populate the list with values from the database.
        */
        addAllStatesToListFromDatabase();

        return states;
    }


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

    public static StateOrProvince findStateInListByID(int stateID) {
        for(StateOrProvince state: states) {
            if(state.getStateID() == stateID) {
                return state;
            }
        }
        return null;
    }

    public static StateOrProvince findStateInListByName(String stateName) {
        for(StateOrProvince state: states) {
            if(state.getState().equals(stateName)) {
                return state;
            }
        }
        return null;
    }

}
