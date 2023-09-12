package com.example.muhammad.chambers.c195.pa.helper;

import com.example.muhammad.chambers.c195.pa.model.Appointment;
import com.example.muhammad.chambers.c195.pa.model.Customer;

public class SelectedItem {
    //This class will hold the selected customer record and selected appointment
    private static Customer selectedCustomer;
    private static Appointment selectedAppointment;

    //Getter methods
    public static Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public static Appointment getSelectedAppointment() {
        return selectedAppointment;
    }


    //Setter methods
    public static void setSelectedCustomer(Customer customer) {
        selectedCustomer = customer;
    }

    public static void setSelectedAppointment(Appointment appointment) {
        selectedAppointment = appointment;
    }


    //Methods
    public static void clearSelectedCustomer() {
        setSelectedCustomer(null);
    }

    public static void clearSelectedAppointment() {
        setSelectedAppointment(null);
    }

}
