package com.example.muhammad.chambers.c195.pa.helper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class BusinessHour {
    private static LocalTime businessStartTime;
    private static LocalTime businessEndTime;
    private static LocalDate date;


    //Getter methods
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

    private static LocalDate getDate() {
        setDate();
        return date;
    }


    //Setter methods
    private static void setBusinessStartTime() {
        businessStartTime = LocalTime.of(8, 00, 00);
    }

    private static void setBusinessEndTime() {
        businessEndTime = LocalTime.of(22, 00, 00);
    }

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
