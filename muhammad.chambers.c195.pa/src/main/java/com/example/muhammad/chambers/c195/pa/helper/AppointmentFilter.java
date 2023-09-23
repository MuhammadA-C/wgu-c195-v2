package com.example.muhammad.chambers.c195.pa.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class AppointmentFilter {

    private static LocalDate currentDate() {
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
}
