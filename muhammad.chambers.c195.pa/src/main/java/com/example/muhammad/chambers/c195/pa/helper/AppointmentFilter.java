package com.example.muhammad.chambers.c195.pa.helper;

import com.example.muhammad.chambers.c195.pa.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class AppointmentFilter {

    public static LocalDate currentDate() {
        return LocalDate.now();
    }

    private static LocalDate firstDayOfCurrentWeek() {
        LocalDate currentDay = currentDate();
        int numberForCurrentDay = currentDate().getDayOfWeek().getValue();

        return currentDay.minusDays((numberForCurrentDay - 1));
    }

    public static ObservableList<LocalDate> daysForCurrentWeekList() {
        ObservableList<LocalDate> daysForCurrentWeek = FXCollections.observableArrayList();
        LocalDate firstDayOfCurrentWeek = firstDayOfCurrentWeek();
        final int maxNumberOfDaysInAWeek = 7;

        for(int i = 0; i < maxNumberOfDaysInAWeek; i++) {
            daysForCurrentWeek.add(firstDayOfCurrentWeek.plusDays(i));
        }
        return daysForCurrentWeek;
    }

    public static ObservableList<Appointment> getAppointmentsForCurrentWeek(ObservableList<Appointment> appointments) {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

        for(LocalDate date : daysForCurrentWeekList()) {
            for(Appointment appointment : appointments) {
                LocalDate appointmentDate = appointment.getStart().toLocalDateTime().toLocalDate();

                if(appointmentDate.isEqual(date)) {
                    appointmentList.add(appointment);
                }
            }
        }
        return appointmentList;
    }

    public static ObservableList<Appointment> getAppointmentsForCurrentMonth(ObservableList<Appointment> appointments) {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        int currentMonth = currentDate().getMonthValue();
        int currentYear = currentDate().getYear();

        for(Appointment appointment : appointments) {
            int appointmentMonth = appointment.getStart().toLocalDateTime().toLocalDate().getMonthValue();
            int appointmentYear = appointment.getStart().toLocalDateTime().toLocalDate().getYear();

            if(appointmentMonth == currentMonth && appointmentYear == currentYear) {
                appointmentList.add(appointment);
            }
        }
        return appointmentList;
    }
}
