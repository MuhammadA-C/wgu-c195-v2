package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.dao.AppointmentDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/** This class is used to create a ReportThree object.*/
public class ReportThree {
    /** Holds the customerID*/
    private int customerID;
    /** Holds the month*/
    private String month;
    /** Holds the total number of appointments*/
    private int totalNumber;
    /** List that holds all report objects*/
    private static ObservableList<ReportThree> reports = FXCollections.observableArrayList();


    /** This is the Constructor for the ReportThree class.
     This Constructor sets all the fields for the ReportThree object.
     @param customerID the customerID to set
     @param month the month to set
     @param totalNumber the totalNumber to set*/
    public ReportThree(int customerID, String month, int totalNumber) {
        this.setMonth(month);
        this.setCustomerID(customerID);
        this.setTotalNumber(totalNumber);
    }


    /** This is the getMonth method.
     This method returns the month field.
     @return Returns the month*/
    public String getMonth() {
        return this.month;
    }

    /** This is the getCustomerID method.
     This method returns the customerID field.
     @return Returns the customerID*/
    public int getCustomerID() {
        return this.customerID;
    }

    /** This is the getTotalNumber method.
     This method returns the totalNumber field.
     @return Returns the totalNumber*/
    public int getTotalNumber() {
        return this.totalNumber;
    }

    /** This is the getReportList method.
     This method returns the reports list field.
     @return Returns the reports
     @throws SQLException due to the SQL queries*/
    public static ObservableList<ReportThree> getReportsList() throws SQLException {
        reports.clear();
        populateReportsList();

        return reports;
    }

    /** This is the setCustomerID method.
     This method sets the customerID field.
     @param customerID the customerID to set*/
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /** This is the setMonth method.
     This method sets the month field.
     @param month the month to set*/
    public void setMonth(String month) {
        this.month = month.toLowerCase();
    }

    /** This is the setTotalNumber method.
     This method sets the totalNumber field.
     @param totalNumber the totalNumber to set*/
    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    /** This is the addReportToReportsList method.
     This method adds a reportThree object to the reports list.
     @param reportThree the reportThree to add*/
    private static void addReportToReportsList(ReportThree reportThree) {
        if(reports.size() == 0) {
            reports.add(reportThree);
            return;
        }

        /*
            Searches the reports list for any reports that match. If a match is found then the
            report won't be added to the list, and instead the total number count will increase by +1
         */
        for(ReportThree report : reports) {
            if (reportThree.getCustomerID() == report.getCustomerID() && reportThree.getMonth().equals(report.getMonth())) {
                int num = report.getTotalNumber() + 1;
                report.setTotalNumber(num);
                return;
            }
        }
        //Report is added to list if it doesn't match an existing report in list by type and month
        reports.add(reportThree);
    }

    /** This is the populateReportsList method.
     This method pulls the appointment data from the database, created report three objects, and adds the objects to the reports list.*/
    private static void populateReportsList() throws SQLException {
        for(Appointment appointment : AppointmentDAOImpl.getAppointmentsList()) {
            String month = appointment.getStart().toLocalDateTime().toLocalDate().getMonth().toString();
            ReportThree report = new ReportThree(appointment.getCustomerID(), month, 1);

            ReportThree.addReportToReportsList(report);
        }
    }
}
