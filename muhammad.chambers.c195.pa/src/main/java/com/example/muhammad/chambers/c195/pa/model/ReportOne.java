package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.dao.AppointmentDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/** This class is used to create a ReportOne object.*/
public class ReportOne {
    private String type;
    private String month;
    private int totalNumber;
    private static ObservableList<ReportOne> reports = FXCollections.observableArrayList();


    //Constructor
    public ReportOne(String type, String month, int totalNumber) {
        this.setType(type);
        this.setMonth(month);
        this.setTotalNumber(totalNumber);
    }


    //Getter methods
    public String getType() {
        return this.type;
    }

    public String getMonth() {
        return this.month;
    }

    public int getTotalNumber() {
        return this.totalNumber;
    }

    public static ObservableList<ReportOne> getReportsList() throws SQLException {
        reports.clear();
        populateReportsList();

        return reports;
    }


    //Setter methods
    public void setType(String type) {
        this.type = type.toLowerCase();
    }

    public void setMonth(String month) {
        this.month = month.toLowerCase();
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

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


    //Methods
    private static void populateReportsList() throws SQLException {
        for(Appointment appointment : AppointmentDAOImpl.getAppointmentsList()) {
            String month = appointment.getStart().toLocalDateTime().toLocalDate().getMonth().toString();
            ReportOne report = new ReportOne(appointment.getType(), month, 1);

            ReportOne.addReportToReportsList(report);
        }
    }
}
