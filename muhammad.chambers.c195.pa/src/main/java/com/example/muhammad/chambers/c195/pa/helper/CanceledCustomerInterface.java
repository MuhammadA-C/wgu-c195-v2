package com.example.muhammad.chambers.c195.pa.helper;

/** This interface class is used for one of the Lambda Expressions. This specific one formats the string when an appointment is deleted prior to deleting a customer record.*/
public interface CanceledCustomerInterface {

    /** This is the canceledAppointmentAndDeletedCustomer method
     This method is used for the lambda expression.
     @param appointmentID the appointmentID
     @param appointmentType the appointment type
     @return Returns a string*/
    String canceledAppointmentAndDeletedCustomer(int appointmentID, String appointmentType );

    /*
        Note: The Lambda Expression associated to this interface can be found in controller -> CustomerRecordController -> Method onActionDelete -> Lines 153 to 160
     */
}
