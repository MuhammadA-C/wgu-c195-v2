package com.example.muhammad.chambers.c195.pa.helper;

import com.example.muhammad.chambers.c195.pa.dao.AppointmentDAOImpl;
import com.example.muhammad.chambers.c195.pa.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentOverlap {
    private static ObservableList<Appointment> getAppointmentsInDatabaseForCustomerID(Appointment appointment) throws SQLException {
        ObservableList<Appointment> appointmentsForCustomerID = FXCollections.observableArrayList();

        for(Appointment appointmentInDatabase : AppointmentDAOImpl.getAppointmentsList()) {
            if(appointmentInDatabase.getCustomerID() == appointment.getCustomerID()) {
                appointmentsForCustomerID.add(appointmentInDatabase);
            }
        }
        return appointmentsForCustomerID;
    }

    private static int numberOfAppointmentDatesBefore(Appointment appointment) throws SQLException {
        //Appointment to add to database
        LocalDate startDate = appointment.getStart().toLocalDateTime().toLocalDate();
        LocalDate endDate = appointment.getEnd().toLocalDateTime().toLocalDate();
        int count = 0;

        for(Appointment appointmentInDatabase : getAppointmentsInDatabaseForCustomerID(appointment)) {
            //Compare appointment currently in database
            LocalDate compareToStartDate = appointmentInDatabase.getStart().toLocalDateTime().toLocalDate();
            LocalDate compareToEndDate = appointmentInDatabase.getEnd().toLocalDateTime().toLocalDate();

            if(startDate.isBefore(compareToStartDate) && startDate.isBefore(compareToEndDate) && endDate.isBefore(compareToStartDate) && endDate.isBefore(compareToEndDate)) {
                count++;
            }
        }
        return count;
    }

    private static int numberOfAppointmentDatesAfter(Appointment appointment) throws SQLException {
        //Appointment to add to database
        LocalDate startDate = appointment.getStart().toLocalDateTime().toLocalDate();
        LocalDate endDate = appointment.getEnd().toLocalDateTime().toLocalDate();
        int count = 0;

        for(Appointment appointmentInDatabase : getAppointmentsInDatabaseForCustomerID(appointment)) {
            //Compare appointment currently in database
            LocalDate compareToStartDate = appointmentInDatabase.getStart().toLocalDateTime().toLocalDate();
            LocalDate compareToEndDate = appointmentInDatabase.getEnd().toLocalDateTime().toLocalDate();

            if(startDate.isAfter(compareToStartDate) && startDate.isAfter(compareToEndDate) && endDate.isAfter(compareToStartDate) && endDate.isAfter(compareToEndDate)) {
                count++;
            }
        }
        return count;
    }

    public static boolean areAppointmentDatesOverlapping(Appointment appointment) throws SQLException {
        int appointmentsBefore = numberOfAppointmentDatesBefore(appointment);
        int appointmentsAfter = numberOfAppointmentDatesAfter(appointment);
        int totalAppointments = appointmentsBefore + appointmentsAfter;

        if(totalAppointments == getAppointmentsInDatabaseForCustomerID(appointment).size()) {
            return false;
        }
        return true;
    }

    private static int numberOfAppointmentTimesAfter(Appointment appointment) throws SQLException {
        //Appointment to add to database
        LocalTime startTime = appointment.getStart().toLocalDateTime().toLocalTime();
        LocalTime endTime = appointment.getEnd().toLocalDateTime().toLocalTime();
        int count = 0;

        for(Appointment appointmentInDatabase : getAppointmentsInDatabaseForCustomerID(appointment)) {
            //Compare appointment currently in database
            LocalTime compareToStartTime = appointmentInDatabase.getStart().toLocalDateTime().toLocalTime();
            LocalTime compareToEndTime = appointmentInDatabase.getEnd().toLocalDateTime().toLocalTime();

            if(startTime.isAfter(compareToStartTime) && startTime.isAfter(compareToEndTime) && endTime.isAfter(compareToStartTime) && endTime.isAfter(compareToEndTime)) {
                count++;
            }
        }
        return count;
    }

    private static int numberOfAppointmentTimesBefore(Appointment appointment) throws SQLException {
        //Appointment to add to database
        LocalTime startTime = appointment.getStart().toLocalDateTime().toLocalTime();
        LocalTime endTime = appointment.getEnd().toLocalDateTime().toLocalTime();
        int count = 0;

        for(Appointment appointmentInDatabase : getAppointmentsInDatabaseForCustomerID(appointment)) {
            //Compare appointment currently in database
            LocalTime compareToStartTime = appointmentInDatabase.getStart().toLocalDateTime().toLocalTime();
            LocalTime compareToEndTime = appointmentInDatabase.getEnd().toLocalDateTime().toLocalTime();

            if(startTime.isBefore(compareToStartTime) && startTime.isBefore(compareToEndTime) && endTime.isBefore(compareToStartTime) && endTime.isBefore(compareToEndTime)) {
                count++;
            }
        }
        return count;
    }

    public static boolean areAppointmentTimesOverlapping(Appointment appointment) throws SQLException {
        int appointmentsBefore = numberOfAppointmentTimesBefore(appointment);
        int appointmentsAfter = numberOfAppointmentTimesAfter(appointment);
        int totalAppointments = appointmentsBefore + appointmentsAfter;

        if(totalAppointments == getAppointmentsInDatabaseForCustomerID(appointment).size()) {
            return false;
        }
        return true;
    }

    public static boolean doesAppointmentHaveTheSameStartAndEndDate(Appointment appointment) throws SQLException {
        //Appointment to add to database
        LocalDate startDate = appointment.getStart().toLocalDateTime().toLocalDate();
        LocalDate endDate = appointment.getEnd().toLocalDateTime().toLocalDate();

        for(Appointment appointmentInDatabase : getAppointmentsInDatabaseForCustomerID(appointment)) {
            //Compare appointment currently in database
            LocalDate compareToStartDate = appointmentInDatabase.getStart().toLocalDateTime().toLocalDate();
            LocalDate compareToEndDate = appointmentInDatabase.getEnd().toLocalDateTime().toLocalDate();

            if(startDate.isEqual(compareToStartDate) && endDate.isEqual(compareToEndDate)) {
                return true;
            }
        }
        return false;
    }

    public static boolean doesAppointmentStartDateOverlapWithEndDate(Appointment appointment) throws SQLException {
        //Appointment to add to database
        LocalDate startDate = appointment.getStart().toLocalDateTime().toLocalDate();

        for(Appointment appointmentInDatabase: getAppointmentsInDatabaseForCustomerID(appointment)) {
            LocalDate compareToEndDate = appointmentInDatabase.getEnd().toLocalDateTime().toLocalDate();

            if(startDate.isEqual(compareToEndDate)) {
                return true;
            }
        }
        return false;
    }

    public static boolean doesAppointmentEndDateOverlapWithStartDate(Appointment appointment) throws SQLException {
        //Appointment to add to database
        LocalDate endDate = appointment.getEnd().toLocalDateTime().toLocalDate();

        for(Appointment appointmentInDatabase: getAppointmentsInDatabaseForCustomerID(appointment)) {
            LocalDate compareToStartDate = appointmentInDatabase.getStart().toLocalDateTime().toLocalDate();

            if(endDate.isEqual(compareToStartDate)) {
                return true;
            }
        }
        return false;
    }
}
