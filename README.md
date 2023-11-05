# wgu-c195-v2
WGU C195 Software 2 Project v2

---

* University: Western Governors University (WGU)
* Degree: Bachelor of Science in Software Development
* Class: C195 Software 2
* Date: 09/28/2023
* Project Type: Individual Project

---

# Overview

This project was created as a class assignment for WGU, C195. This CRUD application was meant to be created for a hypothetical company that needs to be able to have customers schedule appointments.

**Project Prompt:** You work at a software company that has been contracted to develop a GUI desktop scheduling application. The contract is with a global consulting organization that conducts
business in multiple languages and main offices across the world. 

The consulting organization provided a MySQL database that the application must pull data from. The database cannot be modified.

*Note: This project prompt isn't based on a specific real company, but a realistic scenario*

**Note**
* A database ERD diagram was provided
* The database structure was provided

---

## Project Requirements

* Appointments/Customer Records can be added, updated, and deleted
* Appointments/Customer Records should pre populate with input field data when updated
* Application data is saved to the MySQL database
* Data is pulled from the MySQL database and populate in the appropriate UI screens
* Javadoc comments for code
* Record all user login attempts in a text file with the date, timestamp, and whether the login was successful or not

**Input Validation & Error Handling**
* Customer cannot schedule an appointment outside of business hours
  * *Note: Business hours are 8:00 am to 10:00 pm EST*
* Customer cannot schedule overlapping appointments
* Notification for when an incorrect username and password are entered

**User Interface**
* Login Form
* Custom Report Form
* Customer Records Form
* Appointment Scheduling Form

**Login Form**
* User has to enter a username and password to login
* Displays the users location
* Translates text to English or French based on the users system language

**Custom Report Form**
* Generate 3 reports:
  * A report for the total number of customer appointments by type and month
  * A report for each contact that includes appointments for said contact
  * A custom report
  * *Note: reports do not need to be saved, printed, or provided a screenshot*
* Alert for when an appointment is within 15 minutes of when the user logs in; *and include the appointment information*
* Use separate combo boxes for the country and state selection
  * State combo box dynamically populates based on the country combo box selection

**Appointment Scheduling Form**
* Appointments can be filtered by day or month

---

## Video Demenstration of the Appointment Scheduling Application


---

## Photos of the Appointment Scheduling Application GUI


---

## Lessons Learnt / Future Improvements


**Future Improvements**
* Redesign the GUI
* Dynamic UI- UI scalates based on device screen size
* Reports print out
* User accounts can be created

---
## Tools Used:

* Programming Language: Java
* IDE: IntelliJ IDEA
* UI: JavaFX, FXML, Scene Builder, Figma
* Database: JDBC, SQL, MySQL, MySQL Workbench
* Version Control: Git & GitHub
* Object-Oriented Programming (OOP)
* Model-View-Controller (MVC) Pattern 
* Data-Access-Object (DAO) Pattern 

---

## Software Packages

*Note: Below are the versions of the software that were used*

* Java JDK v17
* JavaFX SDK v19
* MySQL Connector Driver v8.1.0
* IntelliJ IDEA Community v2022 
