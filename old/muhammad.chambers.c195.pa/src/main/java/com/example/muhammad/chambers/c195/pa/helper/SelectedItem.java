package com.example.muhammad.chambers.c195.pa.helper;

import com.example.muhammad.chambers.c195.pa.model.Appointment;
import com.example.muhammad.chambers.c195.pa.model.Customer;

/** This class is used to contain selected appointment and customer for the table views, which is used to pass to other screens.*/
public class SelectedItem {
    //This class will hold the selected customer record and selected appointment
    /** Holds a reference to the selected customer from the customers table*/
    private static Customer selectedCustomer;
    /** Holds a reference to the selected appointment from the appointments table*/
    private static Appointment selectedAppointment;

    /** This is the getSelectedCustomer method.
     This method returns the selected customer field.
     @return Returns the selectedCustomer*/
    public static Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    /** This is the getSelectedAppointment method.
     This method returns the selected appointment field.
     @return Returns the selectedAppointment*/
    public static Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    /** This is the setSelectedCustomer method.
     This method sets the selectedCustomer field.
     @param customer the customer to set*/
    public static void setSelectedCustomer(Customer customer) {
        selectedCustomer = customer;
    }

    /** This is the setSelectedAppointment method.
     This method sets the selectedAppointment field.
     @param appointment the appointment to set*/
    public static void setSelectedAppointment(Appointment appointment) {
        selectedAppointment = appointment;
    }

    /** This is the clearSelectedCustomer method.
     This method clears the selectedCustomer field by setting it to NULL*/
    public static void clearSelectedCustomer() {
        setSelectedCustomer(null);
    }

    /** This is the clearSelectedAppointment method.
     This method clears the selectedAppointment field by setting it to NULL*/
    public static void clearSelectedAppointment() {
        setSelectedAppointment(null);
    }

}
