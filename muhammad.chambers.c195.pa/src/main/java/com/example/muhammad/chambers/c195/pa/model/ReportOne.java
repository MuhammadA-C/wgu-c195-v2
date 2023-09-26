package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.dao.AppointmentDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/** This class is used to create a ReportOne object.*/
public class ReportOne {
    /** Holds the type of appointment*/
    private String type;
    /** Holds the month*/
    private String month;
    /** Holds the total number of appointments*/
    private int totalNumber;
    /** List that holds all report objects*/
    private static ObservableList<ReportOne> reports = FXCollections.observableArrayList();


    /** This is the Constructor for the ReportOne class.
     This Constructor sets all the fields for the ReportOne object.
     @param type the type to set
     @param month the month to set
     @param totalNumber the totalNumber to set*/
    public ReportOne(String type, String month, int totalNumber) {
        this.setType(type);
        this.setMonth(month);
        this.setTotalNumber(totalNumber);
    }


    /** This is the getType method.
     This method returns the type field.
     @return Returns the type*/
    public String getType() {
        return this.type;
    }

    /** This is the getMonth method.
     This method returns the month field.
     @return Returns the month*/
    public String getMonth() {
        return this.month;
    }

    /** This is the getTotalNumber method.
     This method returns the totalNumber field.
     @return Returns the totalNumber*/
    public int getTotalNumber() {
        return this.totalNumber;
    }

    /** This is the getReportList method.
     This method returns the reports list field.
     @return Returns the reports*/
    public static ObservableList<ReportOne> getReportsList() throws SQLException {
        reports.clear();
        populateReportsList();

        return reports;
    }

    /** This is the setType method.
     This method sets the type field.
     @param type the type to set*/
    public void setType(String type) {
        this.type = type.toLowerCase();
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
     This method adds a reportOne object to the reports list.
     @param reportOne the reportOne to add*/
    private static void addReportToReportsList(ReportOne reportOne) {
        if(reports.size() == 0) {
            reports.add(reportOne);
            return;
        }

        /*
            Searches the reports list for any reports that match. If a match is found then the
            report won't be added to the list, and instead the total number count will increase by +1
         */
        for(ReportOne report : reports) {
            if (reportOne.getType().equals(report.getType()) && reportOne.getMonth().equals(report.getMonth())) {
                int num = report.getTotalNumber() + 1;
                report.setTotalNumber(num);
                return;
            }
        }
        //Report is added to list if it doesn't match an existing report in list by type and month
        reports.add(reportOne);
    }

    /** This is the populateReportsList method.
     This method pulls the appointment data from the database, created report one objects, and adds the objects to the reports list.*/
    private static void populateReportsList() throws SQLException {
        for(Appointment appointment : AppointmentDAOImpl.getAppointmentsList()) {
            String month = appointment.getStart().toLocalDateTime().toLocalDate().getMonth().toString();
            ReportOne report = new ReportOne(appointment.getType(), month, 1);

            ReportOne.addReportToReportsList(report);
        }
    }
}
