package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.dao.AppointmentDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ReportThree {
    private int customerID;
    private String month;
    private int totalNumber;
    private static ObservableList<ReportThree> reports = FXCollections.observableArrayList();


    //Constructor
    public ReportThree(int customerID, String month, int totalNumber) {
        this.setMonth(month);
        this.setCustomerID(customerID);
        this.setTotalNumber(totalNumber);
    }


    //Getter methods
    public String getMonth() {
        return this.month;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public int getTotalNumber() {
        return this.totalNumber;
    }

    public static ObservableList<ReportThree> getReportsList() throws SQLException {
        reports.clear();
        populateReportsList();

        return reports;
    }


    //Setter methods
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setMonth(String month) {
        this.month = month.toLowerCase();
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

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


    //Methods
    private static void populateReportsList() throws SQLException {
        for(Appointment appointment : AppointmentDAOImpl.getAppointmentsList()) {
            String month = appointment.getStart().toLocalDateTime().toLocalDate().getMonth().toString();
            ReportThree report = new ReportThree(appointment.getCustomerID(), month, 1);

            ReportThree.addReportToReportsList(report);
        }
    }
}
