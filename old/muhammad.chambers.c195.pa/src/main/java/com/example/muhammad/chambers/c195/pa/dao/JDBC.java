package com.example.muhammad.chambers.c195.pa.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/** This class is used to connect to the database*/
public class JDBC {
    /** Holds the protocol*/
    private static final String protocol = "jdbc";
    /** Holds the vendor*/
    private static final String vendor = ":mysql:";
    /** Holds the location*/
    private static final String location = "//localhost/";
    /** Holds the database name*/
    private static final String databaseName = "client_schedule";
    /** Holds the jdbc url*/
    private static final String jdbUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    /** Holds the driver*/
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    /** Holds the username*/
    private static final String userName = "sqlUser";
    /** Holds the password*/
    private static final String password = "Passw0rd!";
    /** Holds a reference to the connection*/
    public static Connection connection;


    /** This is the openConnection method.
     This method is used to open a connection to the database.*/
    public static void openConnection() {
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbUrl, userName, password);
            System.out.println("Connection Successful");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /** This is the closeConnection method.
     This method is used to close the database connection.*/
    public static void closeConnection() {
        try{
            connection.close();
            System.out.println("Connection Closed");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
