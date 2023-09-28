package com.example.muhammad.chambers.c195.pa.helper;

import com.example.muhammad.chambers.c195.pa.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

/** This class is used to filter appointments on the appointment table.*/
public class AppointmentFilter {
    /** This is the currentDate method.
     This method gets the current date based on the user's system.
     @return Returns a LocalDate object*/
    public static LocalDate currentDate() {
        return LocalDate.now();
    }

    /** This is the firstDayOfCurrentWeek method.
     This method is used to get the first day of the current week.
     @return Returns a LocalDate object*/
    private static LocalDate firstDayOfCurrentWeek() {
        LocalDate currentDay = currentDate();
        int numberForCurrentDay = currentDate().getDayOfWeek().getValue();

        return currentDay.minusDays((numberForCurrentDay - 1));
    }

    /** This is the daysForCurrentWeekList method.
     This method returns a list of LocalDates which are all for the current week.
     @return Returns a list of LocalDates*/
    public static ObservableList<LocalDate> daysForCurrentWeekList() {
        ObservableList<LocalDate> daysForCurrentWeek = FXCollections.observableArrayList();
        LocalDate firstDayOfCurrentWeek = firstDayOfCurrentWeek();
        final int maxNumberOfDaysInAWeek = 7;

        for(int i = 0; i < maxNumberOfDaysInAWeek; i++) {
            daysForCurrentWeek.add(firstDayOfCurrentWeek.plusDays(i));
        }
        return daysForCurrentWeek;
    }

    /** This is the getAppointmentsForCurrentWeek method.
     This method is used to get all the appointments for the current week.
     @param appointments list of appointments
     @return Returns a list of appointments that have start dates for the current week*/
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

    /** This is the getAppointmentsForCurrentMonth method.
     This method returns a list for all appointments with start dates for the current month.
     @param appointments list of appointments
     @return Returns a list of appointments that have start dates for the current month*/
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
