package com.example.muhammad.chambers.c195.pa.helper;

import com.example.muhammad.chambers.c195.pa.dao.AppointmentDAOImpl;
import com.example.muhammad.chambers.c195.pa.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

/** This class is used to check if an appointment being added or updated overlaps with an appointment in the database for a specific customerID.*/
public class AppointmentOverlap {
    /** This is the getAppointmentsInDatabaseForCustomerID method.
     This method is used to retrieve all appointments for a specific customerID.
     @param appointment the appointment related to the customerID that you want to get appointments for
     @return Returns a list of appointments for a specific customer id*/
    private static ObservableList<Appointment> getAppointmentsInDatabaseForCustomerID(Appointment appointment) throws SQLException {
        ObservableList<Appointment> appointmentsForCustomerID = FXCollections.observableArrayList();

        for(Appointment appointmentInDatabase : AppointmentDAOImpl.getAppointmentsList()) {
            if(appointmentInDatabase.getCustomerID() == appointment.getCustomerID()) {
                appointmentsForCustomerID.add(appointmentInDatabase);
            }
        }
        return appointmentsForCustomerID;
    }

    /** This is the numberOfAppointmentDatesBefore method.
     This method compares the appointment to add to the database with the appointments in the database by their date,
     and returns the number of appointments in the database that have a date before the appointment to add to the database.
     @param appointment the appointment to add to the database
     @return Returns the number of appointments before the appointment to add*/
    private static int numberOfAppointmentDatesBefore(Appointment appointment) throws SQLException {
        //Appointment to add to database
        LocalDate startDate = appointment.getStart().toLocalDateTime().toLocalDate();
        LocalDate endDate = appointment.getEnd().toLocalDateTime().toLocalDate();
        int count = 0;

        for(Appointment appointmentInDatabase : getAppointmentsInDatabaseForCustomerID(appointment)) {
            //Appointment to compare in database
            LocalDate compareToStartDate = appointmentInDatabase.getStart().toLocalDateTime().toLocalDate();
            LocalDate compareToEndDate = appointmentInDatabase.getEnd().toLocalDateTime().toLocalDate();

            if(startDate.isBefore(compareToStartDate) && startDate.isBefore(compareToEndDate) && endDate.isBefore(compareToStartDate) && endDate.isBefore(compareToEndDate) && appointment.getAppointmentID() != appointmentInDatabase.getAppointmentID()) {
                count++;
            }
        }
        return count;
    }

    /** This is the numberOfAppointmentDatesAfter method.
     This method compares the appointment to add to the database with the appointments in the database by their date,
     and returns the number of appointments in the database that have a date after the appointment to add to the database.
     @param appointment the appointment to add to the database
     @return Returns the number of appointments after the appointment to add*/
    private static int numberOfAppointmentDatesAfter(Appointment appointment) throws SQLException {
        //Appointment to add to database
        LocalDate startDate = appointment.getStart().toLocalDateTime().toLocalDate();
        LocalDate endDate = appointment.getEnd().toLocalDateTime().toLocalDate();
        int count = 0;

        for(Appointment appointmentInDatabase : getAppointmentsInDatabaseForCustomerID(appointment)) {
            //Appointment to compare in database
            LocalDate compareToStartDate = appointmentInDatabase.getStart().toLocalDateTime().toLocalDate();
            LocalDate compareToEndDate = appointmentInDatabase.getEnd().toLocalDateTime().toLocalDate();

            if(startDate.isAfter(compareToStartDate) && startDate.isAfter(compareToEndDate) && endDate.isAfter(compareToStartDate) && endDate.isAfter(compareToEndDate) && appointment.getAppointmentID() != appointmentInDatabase.getAppointmentID()) {
                count++;
            }
        }
        return count;
    }

    /** This is the areAppointmentDatesOverlapping method.
     This method checks to see if the appointment you want to add to the database overlaps with any appointments already in the database for the customerID.
     @param appointment the appointment to add
     @param isUpdateAppointment check to see if the update appointment screen is calling this method
     @return Returns a boolean, true if the appointment you want to add overlaps; or false otherwise
     @throws SQLException due to using SQL for database queries*/
    public static boolean areAppointmentDatesOverlapping(Appointment appointment, boolean isUpdateAppointment) throws SQLException {
        int appointmentsBefore = numberOfAppointmentDatesBefore(appointment);
        int appointmentsAfter = numberOfAppointmentDatesAfter(appointment);
        int totalAppointments = appointmentsBefore + appointmentsAfter;

        if(isUpdateAppointment == false && totalAppointments == getAppointmentsInDatabaseForCustomerID(appointment).size()) {
            return false;
        } else if(isUpdateAppointment == true && (totalAppointments + 1) == getAppointmentsInDatabaseForCustomerID(appointment).size()) {
            /*
                This check is for the Update Appointment form. +1 is being added because for the update appointment form
                the search for overlapping appointments will ignore the appointment with the same appointment id as
                the appointment being updated. So, the +1 is taking into account that skipped appointment
             */
            return false;
        }
        return true;
    }

    /** This is the numberOfAppointmentTimesAfter method.
     This method compares the appointment to add to the database with the appointments in the database by their times,
     and returns the number of appointments in the database that have a time after the appointment to add to the database.
     @param appointment the appointment to add to the database
     @return Returns the number of appointments after the appointment to add
     @throws SQLException due to using SQL for database queries*/
    private static int numberOfAppointmentTimesAfter(Appointment appointment) throws SQLException {
        //Appointment to add to database
        LocalTime startTime = appointment.getStart().toLocalDateTime().toLocalTime();
        LocalTime endTime = appointment.getEnd().toLocalDateTime().toLocalTime();
        int count = 0;

        for(Appointment appointmentInDatabase : getAppointmentsInDatabaseForCustomerID(appointment)) {
            //Appointment to compare in database
            LocalTime compareToStartTime = appointmentInDatabase.getStart().toLocalDateTime().toLocalTime();
            LocalTime compareToEndTime = appointmentInDatabase.getEnd().toLocalDateTime().toLocalTime();

            if(startTime.isAfter(compareToStartTime) && startTime.isAfter(compareToEndTime) && endTime.isAfter(compareToStartTime) && endTime.isAfter(compareToEndTime) && appointment.getAppointmentID() != appointmentInDatabase.getAppointmentID()) {
                count++;
            }
        }
        return count;
    }

    /** This is the numberOfAppointmentTimesBefore method.
     This method compares the appointment to add to the database with the appointments in the database by their time,
     and returns the number of appointments in the database that have a time before the appointment to add to the database.
     @param appointment the appointment to add to the database
     @return Returns the number of appointments before the appointment to add
     @throws SQLException due to using SQL for database queries*/
    private static int numberOfAppointmentTimesBefore(Appointment appointment) throws SQLException {
        //Appointment to add to database
        LocalTime startTime = appointment.getStart().toLocalDateTime().toLocalTime();
        LocalTime endTime = appointment.getEnd().toLocalDateTime().toLocalTime();
        int count = 0;

        for(Appointment appointmentInDatabase : getAppointmentsInDatabaseForCustomerID(appointment)) {
            //Appointment to compare in database
            LocalTime compareToStartTime = appointmentInDatabase.getStart().toLocalDateTime().toLocalTime();
            LocalTime compareToEndTime = appointmentInDatabase.getEnd().toLocalDateTime().toLocalTime();

            if(startTime.isBefore(compareToStartTime) && startTime.isBefore(compareToEndTime) && endTime.isBefore(compareToStartTime) && endTime.isBefore(compareToEndTime) && appointment.getAppointmentID() != appointmentInDatabase.getAppointmentID()) {
                count++;
            }
        }
        return count;
    }

    /** This is the areAppointmentTimesOverlapping method.
     This method checks to see if the appointment you want to add to the database overlaps with any appointments already in the database for the customerID.
     @param appointment the appointment to add
     @param isUpdateAppointment check to see if the update appointment screen is calling this method
     @return Returns a boolean, true if the appointment you want to add overlaps; or false otherwise
     @throws SQLException due to using SQL for database queries*/
    public static boolean areAppointmentTimesOverlapping(Appointment appointment, boolean isUpdateAppointment) throws SQLException {
        int appointmentsBefore = numberOfAppointmentTimesBefore(appointment);
        int appointmentsAfter = numberOfAppointmentTimesAfter(appointment);
        int totalAppointments = appointmentsBefore + appointmentsAfter;

        if(isUpdateAppointment == false && totalAppointments == getAppointmentsInDatabaseForCustomerID(appointment).size()) {
            return false;
        } else if(isUpdateAppointment == true && (totalAppointments + 1) == getAppointmentsInDatabaseForCustomerID(appointment).size()) {
            /*
                This check is for the Update Appointment form. +1 is being added because for the update appointment form
                the search for overlapping appointments will ignore the appointment with the same appointment id as
                the appointment being updated. So, the +1 is taking into account that skipped appointment
             */
            return false;
        }
        return true;
    }

    /** This is the doesAppointmentHaveTheSameStartAndEndDate method.
     This method is used to check if the appointment you want to add has the same start and end dates as an appointment already in thr database for a customerID.
     @param appointment the appointment to add
     @return Returns a boolean; true if there is a math, or false otherwise
     @throws SQLException due to using SQL for database queries*/
    public static boolean doesAppointmentHaveTheSameStartAndEndDate(Appointment appointment) throws SQLException {
        //Appointment to add to database
        LocalDate startDate = appointment.getStart().toLocalDateTime().toLocalDate();
        LocalDate endDate = appointment.getEnd().toLocalDateTime().toLocalDate();

        for(Appointment appointmentInDatabase : getAppointmentsInDatabaseForCustomerID(appointment)) {
            //Appointment to compare in database
            LocalDate compareToStartDate = appointmentInDatabase.getStart().toLocalDateTime().toLocalDate();
            LocalDate compareToEndDate = appointmentInDatabase.getEnd().toLocalDateTime().toLocalDate();

            if(startDate.isEqual(compareToStartDate) && endDate.isEqual(compareToEndDate) && appointment.getAppointmentID() != appointmentInDatabase.getAppointmentID()) {
                return true;
            }
        }
        return false;
    }

    /** This is the doesAppointmentStartDateOverlapWithEndDate method.
     This method is used to check if the appointment you want to add has a start date that overlaps with an appointments end date.
     @param appointment the appointment to add
     @return Returns a boolean; true if there is a math, or false otherwise
     @throws SQLException due to using SQL for database queries*/
    public static boolean doesAppointmentStartDateOverlapWithEndDate(Appointment appointment) throws SQLException {
        //Appointment to add to database
        LocalDate startDate = appointment.getStart().toLocalDateTime().toLocalDate();

        for(Appointment appointmentInDatabase: getAppointmentsInDatabaseForCustomerID(appointment)) {
            LocalDate compareToEndDate = appointmentInDatabase.getEnd().toLocalDateTime().toLocalDate();

            if(startDate.isEqual(compareToEndDate) && appointment.getAppointmentID() != appointmentInDatabase.getAppointmentID()) {
                return true;
            }
        }
        return false;
    }

    /** This is the doesAppointmentEndDateOverlapWithStartDate method
     This method is used to check if the appointment you want to add has an end date that overlaps with an appointments start date.
     @param appointment the appointment to add
     @return Returns a boolean; true if there is a math, or false otherwise
     @throws SQLException due to using SQL for database queries*/
    public static boolean doesAppointmentEndDateOverlapWithStartDate(Appointment appointment) throws SQLException {
        //Appointment to add to database
        LocalDate endDate = appointment.getEnd().toLocalDateTime().toLocalDate();

        for(Appointment appointmentInDatabase: getAppointmentsInDatabaseForCustomerID(appointment)) {
            LocalDate compareToStartDate = appointmentInDatabase.getStart().toLocalDateTime().toLocalDate();

            if(endDate.isEqual(compareToStartDate) && appointment.getAppointmentID() != appointmentInDatabase.getAppointmentID()) {
                return true;
            }
        }
        return false;
    }
}
