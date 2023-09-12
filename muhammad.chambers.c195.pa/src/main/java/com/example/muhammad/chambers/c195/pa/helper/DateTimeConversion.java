package com.example.muhammad.chambers.c195.pa.helper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConversion {
    public final static DateTimeFormatter dateTimeFormatterPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /*
        Code below converts LocalDateTime object to LocalDate, LocalDate to Date.
        This ONLY grabs the date from the LocalDateTime object and not the time!!

                ZoneId zoneId1 = ZoneId.of("America/Los_Angeles");
                ZoneId zoneId2 = ZoneId.of("Europe/Paris");

                LocalDateTime now = LocalDateTime.now();
                ZonedDateTime zonedDateTime = now.atZone(zoneId1);
                ZonedDateTime test = zonedDateTime.withZoneSameInstant(zoneId2);

                LocalDateTime t2 = test.toLocalDateTime();
                Date dt = Date.valueOf(t2.toLocalDate());
     */

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

    public static String convert24hrTo12hrTime(LocalTime time) {
        final int time_12hour = 12;

        if(time.getSecond() <= time_12hour) {
            return time.getHour() + ":" + time.getMinute() + " AM";
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

}
