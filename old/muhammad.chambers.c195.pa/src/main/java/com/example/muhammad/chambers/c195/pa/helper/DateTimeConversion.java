package com.example.muhammad.chambers.c195.pa.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

/** This class holds code for date time conversion*/
public class DateTimeConversion {
    /** Holds a date time formatter pattern*/
    public final static DateTimeFormatter dateTimeFormatterPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** This is the getCurrentDateTimeFormatted method.
     This method returns the current date and time based on the users system time.
     @return Returns a LocalDateTime object*/
    public static LocalDateTime getCurrentDateTimeFormatted() {
        LocalDateTime now = LocalDateTime.now();

        return LocalDateTime.parse(dateTimeFormatterPattern.format(now), dateTimeFormatterPattern);
    }

    /** This is the getSystemZoneID method.
     This method returns users system zone id.
     @return Returns a ZoneID object*/
    public static ZoneId getSystemZoneID() {
        return ZoneId.systemDefault();
    }

    //Lambda Expression #2
    /** This is the timeZoneIdFromStr Lambda Express.
     This method takes a string to look up its ZoneID object and return it.*/
    public static GetTimeZoneIdInterface timeZoneIdFromStr = (timeZone) -> ZoneId.of(timeZone);

    /** This is the convert24hrTo12hrTime method.
     This method converts a 24hr time to 12hrs and returns it as a string.
     @param time the time to convert from 24hrs to 12hrs
     @return Returns the time as a string*/
    public static String convert24hrTo12hrTime(LocalTime time) {
        final int time_10hour = 10;
        final int time_12hour = 12;
        final int time_13hour = 13;
        final int time_22hour = 22;
        final int minutes_30 = 30;

        if(time.getHour() < time_10hour) {
            if(time.getMinute() == minutes_30) {
                return String.format("0%d:%d AM", time.getHour(), time.getMinute());
            }
            return String.format("0%d:%d0 AM", time.getHour(), time.getMinute());

        } else if(time.getHour() >= time_10hour && time.getHour() < time_12hour) {
            if(time.getMinute() == minutes_30) {
                return String.format("%d:%d AM", time.getHour(), time.getMinute());
            }
            return String.format("%d:%d0 AM", time.getHour(), time.getMinute());

        } else if(time.getHour() == time_12hour) {
            if(time.getMinute() == minutes_30) {
                return String.format("%d:%d PM", time.getHour(), time.getMinute());
            }
            return String.format("%d:%d0 PM", time.getHour(), time.getMinute());

        } else if (time.getHour() >= time_13hour && time.getHour() < time_22hour) {
            if(time.getMinute() == minutes_30) {
                return "0" + (time.getHour() - time_12hour) + ":" + time.getMinute() + " PM";
            }
            return "0" + (time.getHour() - time_12hour) + ":0" + time.getMinute() + " PM";

        }

        if(time.getMinute() == minutes_30) {
            return (time.getHour() - time_12hour) + ":" + time.getMinute() + " PM";
        }
        return (time.getHour() - time_12hour) + ":0" + time.getMinute() + " PM";
    }

    /** This is the convertTimeZone method.
     This method converts a time in one time zone to another time zone.
     @param timestamp time to convert
     @param currentTimeZone the current time zone the time is in
     @param convertToTimeZone the time zone to convert the time to
     @return Returns the converted time as a timestamp*/
    public static Timestamp convertTimeZone(Timestamp timestamp , String currentTimeZone, String convertToTimeZone) {
        ZoneId currentZoneId = ZoneId.of(currentTimeZone);
        ZoneId convertToZoneId = ZoneId.of(convertToTimeZone);
        LocalDateTime ldt = timestamp.toLocalDateTime();
        ZonedDateTime currentZDT = ldt.atZone(currentZoneId);
        ZonedDateTime convertToZDT = currentZDT.withZoneSameInstant(convertToZoneId);

        return Timestamp.valueOf(convertToZDT.toLocalDateTime());
    }

    /** This is the getTimesInMilitaryTime method.
     This method returns a list of times in military time.
     @return Returns a list of military times*/
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

    /** This is the getAppointmentsTimesFormatted method.
     This method formats the times for the day with AM or PM.
     @return Returns a list of times formatted with AM or PM*/
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

    /** This is the convertFormattedAppointmentStrToLocalTime method.
     This method is used to convert a time string to local time.
     @param time time to convert
     @return Returns a LocalTime object*/
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
