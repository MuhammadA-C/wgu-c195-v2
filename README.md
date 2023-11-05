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
* Use two different lambda expressions

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

[NEED TO UPDATE]

---

## Photos of the Appointment Scheduling Application GUI

**Login Form**

![Screenshot is of the login form](/C195-Photos/c195-login-form.png)

**Login Form in French**

![Screenshot is of the login form in French](/C195-Photos/c195-login-form-french.png)

*Note: The text specifying the user location was not required to be translated based on the users system language. 
Also, I used code for this screenshot to change the language instead of changing my system settings which is why the location did not change*

**Main Form**

![Screenshot is of the main form](/C195-Photos/c195-main-form.png)

**Add Appointment Form**

![Screenshot is of the add appointment form](/C195-Photos/c195-add-appointment-form.png)

**Customer Records Form**

![Screenshot is of the customer records form](/C195-Photos/c195-customer-records-form.png)

**State combo box auto populate based on Country combo box selection Pt1 - Country: US selected**

![Screenshot is of the state combo box auto populate based on the Country combo box selection for when US is selected](/C195-Photos/c195-country-state-combo-box-pt1.png)


**State combo box auto populate based on Country combo box selection Pt2 - Country: UK selected**

![Screenshot is of the state combo box auto populate based on the Country combo box selection for when UK is selected](/C195-Photos/c195-country-state-combo-box-pt2.png)

---

## Below is one of the Figma concept designs for the Login Screen

![Screenshot is of one of the Figma concept designs for the login screen](/C195-Photos/c195-figma-login-design.png)

**Note**

Originally, I had started this project by concepting designs for the GUI layout of the application in Figma. 
However, I ended up stopping this due to my skill level at the time with JavaFX and FXML not being on par with my CSS skills
to be able to create the UI as close to my concept designs. 

*Note: If using JavaFX and FXML in the future, I will need to improve my skills to be able to create the UI closer to my designs*

---

***Note: You can refer to the folder "C195-Photos" for more photos of the inventory management application***

---

## Lessons Learnt / Future Improvements

In this section I will be covering a few of the lessons that I learnt after completing this project, and what I would do differently/improve in the future if I had to do this again.

---

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
