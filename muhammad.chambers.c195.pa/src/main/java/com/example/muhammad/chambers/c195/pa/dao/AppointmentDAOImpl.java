package com.example.muhammad.chambers.c195.pa.dao;

import com.example.muhammad.chambers.c195.pa.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AppointmentDAOImpl {
    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();


    public static ObservableList<Appointment> getAppointmentsList() throws SQLException {
        /*
            Appointments Observable list is only accessible through a getter method,
            which allows the program to populate the list with values from the database.
        */
        appointments.clear();
        addAllAppointmentsToListFromDatabase();

        return appointments;
    }

    private static void addAllAppointmentsToListFromDatabase() throws SQLException {
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        //Continues to loop through all data entries in the database until it reaches the last row
        while(rs.next()) {
            //Creates variables for the contact object from the database columns
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

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

    private static boolean isAppointmentInList(Appointment appointment) throws SQLException {
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

}
