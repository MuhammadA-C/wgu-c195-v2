package com.example.muhammad.chambers.c195.pa.helper;

import com.example.muhammad.chambers.c195.pa.dao.AppointmentDAOImpl;
import com.example.muhammad.chambers.c195.pa.model.Appointment;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public class UpcomingAppointment {

    public static String getUpcomingAppointments(ObservableList<Appointment> appointments) throws SQLException {
        String upcomingAppointment = "There are no upcoming appointments.";
        final int minutes_15 = 15;

        /*
            Loop is used to check for upcoming appointments by increasing the minute by +1 each time
            and comparing the time to the appointments in the database start time.
         */
        for(int i = 0; i <= minutes_15; i++) {
            LocalTime upcomingTime = LocalTime.now().plusMinutes(i);
            int upcomingHour = upcomingTime.getHour();
            int upcomingMinute = upcomingTime.getMinute();

            for(Appointment appointment : appointments) {
                int appointmentHour = appointment.getStart().toLocalDateTime().toLocalTime().getHour();
                int appointmentMinute = appointment.getStart().toLocalDateTime().toLocalTime().getMinute();

                //Checks to see if start date is the same day as the system date, if not then skip
                if(!appointment.getStart().toLocalDateTime().toLocalDate().isEqual(LocalDate.now())) {
                    continue;
                }

                if(appointmentHour == upcomingHour && appointmentMinute == upcomingMinute) {
                    //Formats the string for upcoming appointments
                    if(i == 0) {
                        upcomingAppointment = appointment.getAppointmentID() + ", " + appointment.getStart().toLocalDateTime().toLocalDate() + ", " + appointment.getStart().toLocalDateTime().toLocalTime();
                    } else {
                        upcomingAppointment += " | " + appointment.getAppointmentID() + ", " + appointment.getStart().toLocalDateTime().toLocalDate() + ", " + appointment.getStart().toLocalDateTime().toLocalTime();
                    }
                }
            }
        }
        return upcomingAppointment;
    }
}
