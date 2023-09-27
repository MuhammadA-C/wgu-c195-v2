package com.example.muhammad.chambers.c195.pa.dao;

import com.example.muhammad.chambers.c195.pa.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/** This class holds code to access the database for the appointment table.*/
public class AppointmentDAOImpl {
    /** Holds a list of appointment objects*/
    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    /** Holds the name of the appointments table*/
    public final static String TABLE_NAME = "appointments";
    /** Holds the name of the appointment id column*/
    public final static String APPOINTMENT_ID_COLUMN_NAME = "Appointment_ID";
    /** Holds the name of the title column*/
    public final static String TITLE_COL_NAME = "Title";
    /** Holds the name of the description column*/
    public final static String DESCRIPTION_COL_NAME = "Description";
    /** Holds the name of the location column*/
    public final static String LOCATION_COL_NAME = "Location";
    /** Holds the name of the type column*/
    public final static String TYPE_COL_NAME = "Type";
    /** Holds the name of the start column*/
    public final static String START_COL_NAME = "Start";
    /** Holds the name of the end column*/
    public final static String END_COL_NAME = "End";
    /** Holds the name of the last update column*/
    public final static  String LAST_UPDATE_COL_NAME = "Last_Update";
    /** Holds the name of the last updated by column*/
    public final static  String LAST_UPDATED_BY_COL_NAME = "Last_Updated_By";
    /** Holds the name of the customer id column*/
    public final static String CUSTOMER_ID_COL_NAME = "Customer_ID";
    /** Holds the name of the user id column*/
    public final static String USER_ID_COL_NAME = "User_ID";
    /** Holds the name of the contact id column*/
    public final static  String CONTACT_ID_COL_NAME = "Contact_ID";


    /** This is the getAppointmentsList method.
     This method sets the appointments list with values from the database prior to returning a list of appointment objects.
     @return Returns a list of appointment objects from the database*/
    public static ObservableList<Appointment> getAppointmentsList() throws SQLException {
        /*
            Appointments Observable list is only accessible through a getter method,
            which allows the program to populate the list with values from the database.
        */
        appointments.clear();
        addAllAppointmentsToListFromDatabase();

        return appointments;
    }

    /** This is the addAllAppointmentsToListFromDatabase method.
     This method is used to pull the rows from the database from the appointment table, create appointment objects, and add them to the appointments list.*/
    private static void addAllAppointmentsToListFromDatabase() throws SQLException {
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        //Continues to loop through all data entries in the database until it reaches the last row
        while(rs.next()) {
            //Creates variables for the contact object from the database columns
            int appointmentID = rs.getInt(APPOINTMENT_ID_COLUMN_NAME);
            String title = rs.getString(TITLE_COL_NAME);
            String description = rs.getString(DESCRIPTION_COL_NAME);
            String location = rs.getString(LOCATION_COL_NAME);
            String type = rs.getString(TYPE_COL_NAME);
            Timestamp start = rs.getTimestamp(START_COL_NAME);
            Timestamp end = rs.getTimestamp(END_COL_NAME);
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp(LAST_UPDATE_COL_NAME);
            String lastUpdatedBy = rs.getString(LAST_UPDATED_BY_COL_NAME);
            int customerID = rs.getInt(CUSTOMER_ID_COL_NAME);
            int userID = rs.getInt(USER_ID_COL_NAME);
            int contactID = rs.getInt(CONTACT_ID_COL_NAME);

            Appointment appointment = new Appointment(title, description, location, type, start, end, contactID, customerID, userID);

            appointment.setAppointmentID(appointmentID);
            appointment.setCreateDate(createDate);
            appointment.setCreatedBy(createdBy);
            appointment.setLastUpdate(lastUpdate);
            appointment.setLastUpdatedBy(lastUpdatedBy);

            //Adds appointment to list from database ONLY if not already present
            if(!isAppointmentInList(appointment)) {
                appointments.add(appointment);
            }
        }
    }

    /** This is the isAppointmentInList method.
     This method checks to see if an appointment is in the appointments list, and returns a boolean.
     @param appointment the appointment object to check to see if it's in the database
     @return Returns true if the appointment is found in the list, or false otherwise*/
    private static boolean isAppointmentInList(Appointment appointment) {
        /*
            Checks to see if the Appointment object is already present
            in the appointments list by comparing the customer id, start and end times, and location
         */
        boolean found = false;

        for(Appointment i: appointments) {
            if(i.getCustomerID() == appointment.getCustomerID() && i.getStart().equals(appointment.getStart()) && i.getEnd().equals(appointment.getEnd()) && i.getLocation().equals(appointment.getLocation())) {
                found = true;
                break;
            }
        }
        return found;
    }

    /** This is the doesCustomerIDHaveAnyAppointments method.
     This method is used to check if a customer id has any appointments in the database.
     @param customerID the customerID to check if it has any appointments associated to it
     @return  Returns true if the customerID has any appointments in the list, or false otherwise*/
    public static boolean doesCustomerIDHaveAnyAppointments(int customerID) throws SQLException {
        ObservableList<Appointment> appointments = getAppointmentsList();

        for(int i = 0; i < appointments.size(); i++) {
            if(appointments.get(i).getCustomerID() == customerID) {
                return true;
            }
        }
        return false;
    }

    /** This is the insert method.
     This method is used to add an appointment object as a row to the database.
     @param appointment the appointment object to add to the database
     @return Returns an integer for the number of rows affected*/
    public static int insert(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setInt(1, appointment.getAppointmentID());
        ps.setString(2, appointment.getTitle());
        ps.setString(3, appointment.getDescription());
        ps.setString(4, appointment.getLocation());
        ps.setString(5, appointment.getType());
        ps.setTimestamp(6, appointment.getStart());
        ps.setTimestamp(7, appointment.getEnd());
        ps.setTimestamp(8, appointment.getCreateDate());
        ps.setString(9, appointment.getCreatedBy());
        ps.setTimestamp(10, appointment.getLastUpdate());
        ps.setString(11, appointment.getLastUpdatedBy());
        ps.setInt(12, appointment.getCustomerID());
        ps.setInt(13, appointment.getUserID());
        ps.setInt(14, appointment.getContactID());

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }
}
