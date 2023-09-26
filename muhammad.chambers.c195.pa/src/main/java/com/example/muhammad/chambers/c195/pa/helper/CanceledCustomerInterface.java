package com.example.muhammad.chambers.c195.pa.helper;

/** This interface class is used for one of the Lambda Expressions. This specific one formats the string when an appointment is deleted prior to deleting a customer record.*/
public interface CanceledCustomerInterface {

    String canceledAppointmentAndDeletedCustomer(int appointmentID, String appointmentType );

    /*
        Note: The Lambda Expression associated to this interface can be found in controller -> CustomerRecordController -> Method onActionDelete -> Lines 122 to 132
     */
}
