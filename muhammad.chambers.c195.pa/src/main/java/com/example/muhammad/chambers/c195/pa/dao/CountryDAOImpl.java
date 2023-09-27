package com.example.muhammad.chambers.c195.pa.dao;

import com.example.muhammad.chambers.c195.pa.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/** This class holds code to access the database for the country table.*/
public class CountryDAOImpl {
    /** Holds a list of country objects*/
    private static ObservableList<Country> countries = FXCollections.observableArrayList();


    /** This is the getCountriesList method.
     This method sets the countries list with values from the database prior to returning a list of country objects.
     @return Returns a list of country objects from the database*/
    public static ObservableList<Country> getCountriesList() throws SQLException {
        /*
            Countries Observable list is only accessible through a getter method,
            which allows the program to populate the list with values from the database.
        */
        addAllCountriesToListFromDatabase();

        return countries;
    }

    /** This is the addAllCountriesToListFromDatabase method.
     This method is used to pull the rows from the database from the country table, create country objects, and add them to the countries list.*/
    private static void addAllCountriesToListFromDatabase() throws SQLException {
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        //Continues to loop through all data entries in the database until it reaches the last row
        while(rs.next()) {
            //Creates variables for the country object from the database columns
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            Country country = new Country(countryID, countryName, createDate, createdBy, lastUpdate, lastUpdatedBy);

            //Adds country to list from database ONLY if not already present
            if(!isCountryInList(country)) {
                countries.add(country);
            }
        }
    }

    /** This is the isCountryInList method.
     This method checks to see if a country is in the countries list, and returns a boolean.
     @param country the country object to check to see if it's in the database
     @return Returns true if the customer is found in the list, or false otherwise*/
    private static boolean isCountryInList(Country country) throws SQLException {
        /*
            Checks to see if the Country object is already present
            in the countries list by comparing country names.
         */
        boolean found = false;

        for(Country i: countries) {
            if(i.getCountry().equals(country.getCountry())) {
                found = true;
                break;
            }
        }
        return found;
    }

    /** This is the findCountryInListByName method.
     This method checks to see if a countryName is in the countries list, and returns the object.
     @param countryName the country name to check to see if it's in the database
     @return Returns a country object if found, or NULL otherwise*/
    public static Country findCountryInListByName(String countryName) throws SQLException {
        for(Country country: getCountriesList()) {
            if(country.getCountry().equals(countryName)) {
                return country;
            }
        }
        return null;
    }
}
