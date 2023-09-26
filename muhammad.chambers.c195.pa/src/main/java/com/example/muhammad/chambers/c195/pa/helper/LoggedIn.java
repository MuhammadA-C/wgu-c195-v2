package com.example.muhammad.chambers.c195.pa.helper;

/** This class is used to contain create a LoggedIn object which is used to track who is logged in.*/
public class LoggedIn {
    /** Holds the username for the account that is logged in*/
    private static String loggedInUsername;


    /** This is the getLoggedInUsername method.
     This method returns the loggedInUsername field.
     @return Returns the loggedInUsername*/
    public static String getLoggedInUsername() {
        return loggedInUsername;
    }

    /** This is the setLoggedInUsername method.
     This method sets the loggedInUsername field.
     @param username the username to set*/
    public static void setLoggedInUsername(String username) {
        loggedInUsername = username;
    }
}
