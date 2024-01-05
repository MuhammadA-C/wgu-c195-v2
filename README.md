# WGU-C195-Appointment Scheduling Application

WGU C195 Software 2 - Scheduling Application

---

* University: Western Governors University (WGU)
* Degree: Bachelor of Science in Software Development
* Class: C195 Software 2
* Date: 09/28/2023
* Project Type: Individual Project

---

# Overview

This project was created as a class assignment for WGU, C195. This CRUD Appointment Scheduling Application was meant to be created for a hypothetical company that needs to be able to have customers schedule appointments.

**Project Prompt:** You work at a software company that has been contracted to develop a GUI desktop scheduling application. The contract is with a global consulting organization that conducts
business in multiple languages and main offices across the world. 

The consulting organization provided a MySQL database that the application must pull data from. The database cannot be modified.

*Note: This project prompt isn't based on a specific real company, but a realistic scenario*

**Note**
* A database ERD diagram was provided
* The database structure was provided

---

**Important to note**

The project files in this GitHub repo will not run the application correctly due to the databse information missing. 
The databse information was also not provided due to the database used for this project was created by WGU.

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
  * *Note: It is not required to translate the text for the user location*
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

Below are links to YouTube Videos demonstrating the application:

* [Add Appointment Form Demonstration](https://youtu.be/NGsEcFL4RSk)
* [Deleting Appointment Functionality Demonstration](https://youtu.be/EXKoiARgfsE)

---

## Photos of the Appointment Scheduling Application GUI

**Login Form**

![Screenshot is of the login form](/C195-Photos/c195-login-form.png)

**Login Form in French**

*Note: The text specifying the user location was not required to be translated based on the users system language. 
Also, I used code for this screenshot to change the language instead of changing my system settings which is why the location did not change*

![Screenshot is of the login form in French](/C195-Photos/c195-login-form-french.png)


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

## Extra Features

One of the extra features that I added for my project was for the requirement to stop the user from deleting a customer record that has appointments 
associated with it, and to notify them that all appointments associated with the customer need to be deleted prior to deleting the customer record.

I implemented the functionality to notify the user that the customer record has appointments associated with it if there are any, and to confirm whether 
the user wants to delete said customer record and all of the appointments along with it.

**Note**
* If the user clicks yes
* All associated appointments will be deleted first from the database, and the user will be notified for each of the deleted appointments
* The customer record will be deleted afterwards, and the user will be notified of the deletion of the customer record


**Confirmation message to delete the customer record and all of the associated appointments Pt1**

![Screenshot of the confirmation message to delete a customer record and all of its associated appointments pt1](/C195-Photos/c195-delete-customer-record-message-pt1.png)

**Confirmation message to delete the customer record and all of the associated appointments Pt2**

![Screenshot of the confirmation message to delete a customer record and all of its associated appointments pt2](/C195-Photos/c195-delete-customer-record-message-pt2.png)

**Confirmation message to delete the customer record and all of the associated appointments Pt3**

![Screenshot of the confirmation message to delete a customer record and all of its associated appointments pt3](/C195-Photos/c195-delete-customer-record-message-pt3.png)

**Note**

Due to the order that I have the code for deleting the appointment & customer record and displaying the notification messages and the code running synchronously, the notification message will be displayed prior to the actual deletion code being run.

*Note: This can easily be fixed by re-ordering the deletion code to be run before the notification message code, or allowing other code to still be run when the notification message code is triggered & notification pops up*

---

## Lessons Learnt / Future Improvements

In this section I will be covering a few of the lessons that I learnt after completing this project, and what I would do differently/improve in the future if I had to do this again.

[NEED TO UPDATE]

---

**Future Improvements**
* Redesign the GUI
* Dynamic UI- UI scales based on device screen size
* Reports print out
* User accounts can be created

---
## Tools/Skills Used:

* Programming Language: Java
* IDE: IntelliJ IDEA
* GUI/UI: JavaFX, FXML, Scene Builder, Figma
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
