package com.example.muhammad.chambers.c195.pa.helper;

import java.time.ZoneId;

/** This interface class is used for one of the Lambda Expressions. This specific one obtains a zone id from string*/
public interface GetTimeZoneIdInterface {
    /** This is the getTimeZoneID method.
     This is a lambda expression to get the zone id.
     @param timeZone the time zone
     @return Returns a ZoneID*/
    ZoneId getTimeZoneID(String timeZone);

    /*
        Note: The Lambda Expression associated to this interface can be found in helper -> DateTimeConversion -> Line 25
        The usage of this Lambda can be found in helper -> BusinessHour -> Lines 18 and 31
     */
}
