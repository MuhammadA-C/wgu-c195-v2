package com.example.muhammad.chambers.c195.pa.helper;

public interface CanceledCustomerInterface {

    String canceledAppointmentAndDeletedCustomer(int appointmentID, String appointmentType );

    /*
        Note: The Lambda Expression associated to this interface can be found in controller -> CustomerRecordController -> Method onActionDelete -> Lines 122 to 132
     */
}
