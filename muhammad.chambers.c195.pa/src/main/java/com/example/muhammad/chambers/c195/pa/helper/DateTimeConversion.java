package com.example.muhammad.chambers.c195.pa.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeConversion {
    public final static DateTimeFormatter dateTimeFormatterPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static LocalDateTime getCurrentDateTimeFormatted() {
        LocalDateTime now = LocalDateTime.now();

        return LocalDateTime.parse(dateTimeFormatterPattern.format(now), dateTimeFormatterPattern);
    }

    public static ZoneId getSystemZoneID() {
        return ZoneId.systemDefault();
    }

    public static ZoneId getUTCZoneID() {
        return ZoneId.of("UTC");
    }

    public static ZoneId getESTZoneID() {
        return ZoneId.of("US/Eastern");
    }

    public static String convert24hrTo12hrTime(LocalTime time) {
        final int time_10hour = 10;
        final int time_12hour = 12;
        final int time_13hour = 13;
        final int time_22hour = 22;

        if(time.getHour() < time_10hour) {
            return String.format("0%d:%d AM", time.getHour(), time.getMinute());
        } else if(time.getHour() >= time_10hour && time.getHour() <= time_12hour) {
            return String.format("%d:%d AM", time.getHour(), time.getMinute());
        } else if (time.getHour() >= time_13hour && time.getHour() < time_22hour) {
            return "0" + (time.getHour() - time_12hour) + ":" + time.getMinute() + " PM";
        }
        return (time.getHour() - time_12hour) + ":" + time.getMinute() + " PM";
    }

    public static Timestamp convertTimeZone(Timestamp timestamp ,String currentTimeZone, String convertToTimeZone) {
        ZoneId currentZoneId = ZoneId.of(currentTimeZone);
        ZoneId convertToZoneId = ZoneId.of(convertToTimeZone);
        LocalDateTime ldt = timestamp.toLocalDateTime();
        ZonedDateTime currentZDT = ldt.atZone(currentZoneId);
        ZonedDateTime convertToZDT = currentZDT.withZoneSameInstant(convertToZoneId);

        return Timestamp.valueOf(convertToZDT.toLocalDateTime());
    }

    public static ObservableList<LocalTime> getTimesInMilitaryTime() {
        final int MILITARY_TIME_24HRS = 24;
        ObservableList<LocalTime> times = FXCollections.observableArrayList();

        for(int i = 0; i < MILITARY_TIME_24HRS; i++) {
            if(i < 10) {
                times.add(LocalTime.parse(String.format("0%d:00:00", i)));
                times.add(LocalTime.parse(String.format("0%d:30:00", i)));
            } else {
                times.add(LocalTime.parse(String.format("%d:00:00", i)));
                times.add(LocalTime.parse(String.format("%d:30:00", i)));
            }
        }
        return times;
    }

    public static ObservableList<String> getAppointmentsTimesFormatted() {
        final int HOURS_12 = 12;
        ObservableList<String> times = FXCollections.observableArrayList();

        for(int i = 0; i < HOURS_12; i++) {
            if(i == 0) {
                times.add("12:00 AM");
                times.add("12:30 AM");
            } else if(i > 0 && i <= 9) {
                times.add(String.format("0%d:00 AM", i));
                times.add(String.format("0%d:30 AM", i));
            } else {
                times.add(String.format("%d:00 AM", i));
                times.add(String.format("%d:30 AM", i));
            }
        }

        for(int i = 0; i < HOURS_12; i++) {
            if(i == 0) {
                times.add("12:00 PM");
                times.add("12:30 PM");
            } else if(i > 0 && i <= 9) {
                times.add(String.format("0%d:00 PM", i));
                times.add(String.format("0%d:30 PM", i));
            } else {
                times.add(String.format("%d:00 PM", i));
                times.add(String.format("%d:30 PM", i));
            }
        }

        return times;
    }

    public static LocalTime convertFormattedAppointmentStrToLocalTime(String time) {
        int index = -1;
        LocalTime localTime = null;
        ObservableList<LocalTime> localMilitaryTimes = getTimesInMilitaryTime();
        ObservableList<String> formattedAppointmentTimesList = getAppointmentsTimesFormatted();

        for(int i = 0; i < formattedAppointmentTimesList.size(); i++) {
            if(formattedAppointmentTimesList.get(i).equals(time)) {
                index = i;
            }
        }

        for(int i = 0; i < localMilitaryTimes.size(); i++) {
            if(i == index) {
                localTime = localMilitaryTimes.get(i);
            }
        }
        return localTime;
    }

}
