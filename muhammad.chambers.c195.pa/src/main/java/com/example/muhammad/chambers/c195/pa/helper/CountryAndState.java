package com.example.muhammad.chambers.c195.pa.helper;

import com.example.muhammad.chambers.c195.pa.dao.CountryDAOImpl;
import com.example.muhammad.chambers.c195.pa.dao.StateOrProvinceDAOImpl;
import com.example.muhammad.chambers.c195.pa.model.Country;
import com.example.muhammad.chambers.c195.pa.model.StateOrProvince;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CountryAndState {
    private static ObservableList<StateOrProvince> statesForCountry = FXCollections.observableArrayList();


    public static ObservableList<StateOrProvince> getAllStatesForCountry(String countryName) throws SQLException {
        /*
            Have to reset the list each time, otherwise the previous values will still
            which will still be present if the user selects a different country.

            Note: There's code to prevent duplicates, but this use-case is different from duplicates
         */
        statesForCountry.clear();

        int countryID = wasCountryIDFoundInCountriesList(countryName);

        if(countryID <= InputValidation.DOES_NOT_EXIST_IN_DATABASE) {
            return null;
        }

        /*
            Loops through the states list from the FirstLevelDivisionDAOImpl class
            and adds all states that have matching country id to the list.

            Note: States already added to the list won't be added
         */
        for(StateOrProvince i : StateOrProvinceDAOImpl.getStatesList()) {
            if(i.getCountryID() == countryID) {
                if(!isStateInList(i)) {
                    statesForCountry.add(i);
                }
            }
        }
        return statesForCountry;
    }

    public static int wasCountryIDFoundInCountriesList(String countryName) throws SQLException {
        for(Country i : CountryDAOImpl.getCountriesList()) {
            if(i.getCountry().equals(countryName)) {
                return i.getCountryID();
            }
        }
        return InputValidation.DOES_NOT_EXIST_IN_DATABASE;
    }

    private static boolean isStateInList(StateOrProvince state) {
        /*
            Checks to see if the State object is already present
            in the states list by comparing state names.
         */
        boolean found = false;

        for(StateOrProvince i: statesForCountry) {
            if(i.getState().equals(state.getState())) {
                found = true;
                break;
            }
        }
        return found;
    }

    public static String getCountryNameForState(int stateID) throws SQLException {
        int countryID = InputValidation.DOES_NOT_EXIST_IN_DATABASE;

        for(StateOrProvince i: StateOrProvinceDAOImpl.getStatesList()) {
            if(i.getStateID() == stateID) {
                countryID = i.getCountryID();
            }
        }

        if(countryID == InputValidation.DOES_NOT_EXIST_IN_DATABASE) {
            return null;
        }

        for(Country i: CountryDAOImpl.getCountriesList()) {
            if(i.getCountryID() == countryID) {
                return i.getCountry();
            }
        }
        return InputValidation.NOT_FOUND;
    }
}
