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
        ZonedDateTime businessZDT = ZonedDateTime.of(getDate(), businessStartTime, DateTimeConversion.getESTZoneID());
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
        ZonedDateTime businessZDT = ZonedDateTime.of(getDate(), businessEndTime, DateTimeConversion.getESTZoneID());
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
}
