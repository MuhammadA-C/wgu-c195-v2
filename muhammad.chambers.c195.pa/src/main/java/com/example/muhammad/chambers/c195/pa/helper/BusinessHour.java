package com.example.muhammad.chambers.c195.pa.helper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

/** This class handles the input checking to see if an appointment is within business hours.*/
public class BusinessHour {
    /** Holds the business start time*/
    private static LocalTime businessStartTime;
    /** Holds the business end time*/
    private static LocalTime businessEndTime;
    /** Holds the date*/
    private static LocalDate date;


    /** This is the getBusinessStartTime method
     This method is used to get the business start time. A lambda expression was used in this method to retrieve the ZoneID for the Eastern time zone.
     @return Returns the business start time*/
    public static LocalTime getBusinessStartTime() {
        //Sets the business hours start time to 08:00 am with no specified time zone
        setBusinessStartTime();
        //Sets the time zone for 08:00 am to EST
        ZonedDateTime businessZDT = ZonedDateTime.of(getDate(), businessStartTime, DateTimeConversion.timeZoneIdFromStr.getTimeZoneID("US/Eastern"));
        //Converts the time zone from 08:00 am EST to the users system time zone
        ZonedDateTime convertedToSystemZDT = businessZDT.withZoneSameInstant(DateTimeConversion.getSystemZoneID());
        //Sets the business hours start time to the users system time equivalent for 08:00 am EST
        businessStartTime = convertedToSystemZDT.toLocalTime();

        return businessStartTime;
    }

    /** This is the getBusinessEndTime method
     This method is used to get the business end time. A lambda expression was used in this method to retrieve the ZoneID for the Eastern time zone.
     @return Returns the business end time*/
    public static LocalTime getBusinessEndTime() {
        //Sets the business hours end time to 10:00 pm with no specified time zone
        setBusinessEndTime();
        //Sets the time zone for 10:00 pm to EST
        ZonedDateTime businessZDT = ZonedDateTime.of(getDate(), businessEndTime, DateTimeConversion.timeZoneIdFromStr.getTimeZoneID("US/Eastern"));
        //Converts the time zone from 10:00 pm EST to the users system time zone
        ZonedDateTime convertedToSystemZDT = businessZDT.withZoneSameInstant(DateTimeConversion.getSystemZoneID());
        //Sets the business hours end time to the users system time equivalent for 10:00 pm EST
        businessEndTime = convertedToSystemZDT.toLocalTime();

        return businessEndTime;
    }

    /** This is the getDate method
     This method is used to get the date.
     @return Returns the date*/
    private static LocalDate getDate() {
        setDate();
        return date;
    }

    /** This is the setBusinessStartTime method.
     This method sets the business start time.*/
    private static void setBusinessStartTime() {
        businessStartTime = LocalTime.of(8, 00, 00);
    }

    /** This is the setBusinessEndTime method.
     This method sets the business end time.*/
    private static void setBusinessEndTime() {
        businessEndTime = LocalTime.of(22, 00, 00);
    }

    /** This is the setDate method.
     This method sets the date.*/
    private static void setDate() {
        /*
            In this case, it doesn't matter what the specific date is being set to because I only need the time.
            So, just set it to the current date.
         */
        date = LocalDate.now();
    }


    private static boolean isStartTimeWithinBusinessStartTime(LocalTime startTime) {
        //Selected start time cannot be less than business hours start time
        LocalTime businessStartTime = getBusinessStartTime();

        if(startTime.equals(businessStartTime)) {
            return true;
        } else if(startTime.isAfter(businessStartTime)) {
            return true;
        }
        return false;
    }

    private static boolean isEndTimeWithinBusinessEndTime(LocalTime endTime) {
        //Selected end time cannot be greater than business hours end time
        LocalTime businessEndTime = getBusinessEndTime();

        if(endTime.equals(businessEndTime)) {
            return true;
        } else if(endTime.isBefore(businessEndTime)) {
            return true;
        }
        return false;
    }

    public static boolean isStartAndEndTimeWithBusinessHours(LocalTime startTime, LocalTime endTime) {
        if(isStartTimeWithinBusinessStartTime(startTime) && isEndTimeWithinBusinessEndTime(endTime)) {
            return true;
        }
        return false;
    }

    public static String businessHoursHintTxt() {
        String start = DateTimeConversion.convert24hrTo12hrTime(getBusinessStartTime());
        String end =   DateTimeConversion.convert24hrTo12hrTime(getBusinessEndTime());

        return String.format("Note: 8:00 AM to 10:00 PM EST converted to your time zone is %s to %s", start, end);
    }
}
