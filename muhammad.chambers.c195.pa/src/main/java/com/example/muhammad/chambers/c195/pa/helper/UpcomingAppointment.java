package com.example.muhammad.chambers.c195.pa.helper;

import com.example.muhammad.chambers.c195.pa.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class UpcomingAppointment {

    public static ObservableList<String> getUpcomingAppointments(ObservableList<Appointment> appointments) throws SQLException {
        final int minutes_15 = 15;
        ObservableList<String> upcomingAppointments = FXCollections.observableArrayList();

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
                    upcomingAppointments.add(appointment.getAppointmentID() + ", " + appointment.getStart().toLocalDateTime().toLocalDate() + ", " + appointment.getStart().toLocalDateTime().toLocalTime());
                }
            }
        }
        return upcomingAppointments;
    }

    public static String upcomingAppointmentsStr( ObservableList<String> upcomingAppointments) {
        String appointments = "";

        if(upcomingAppointments.size() == 0) {
            return "There are no upcoming appointments.";
        }

        for(int i = 0; i < upcomingAppointments.size(); i++) {
            if(i == 0) {
                appointments.trim();
                appointments = upcomingAppointments.get(i);
            } else {
                appointments += " | " + upcomingAppointments.get(i);
            }
        }
        return appointments;
    }

}
