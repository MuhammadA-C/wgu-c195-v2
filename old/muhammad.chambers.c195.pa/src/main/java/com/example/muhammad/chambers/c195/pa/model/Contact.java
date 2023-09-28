package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.helper.InputValidation;

/** This class is used to create a Contact object.*/
public class Contact {
    /** Holds the contact id*/
    private int contactID;
    /** Holds the contact name*/
    private String contactName;
    /** Holds the email for the contact*/
    private String email;


    /** This is the Constructor for the Contact class.
     This Constructor sets all the fields for the Contact object.
     @param contactID the contactID to set
     @param contactName the contactName to set
     @param email the email to set*/
    public Contact(int contactID, String contactName, String email) {
        this.setContactID(contactID);
        this.setContactName(contactName);
        this.setEmail(email);
    }


    /** This is the getContactName method.
     This method returns the contactName field.
     @return Returns the contactName*/
    public String getContactName() {
        return this.contactName;
    }

    /** This is the getContactID method.
     This method returns the contactID field.
     @return Returns the contactID*/
    public int getContactID() {
        return this.contactID;
    }

    /** This is the getEmail method.
     This method returns the email field.
     @return Returns the email*/
    public String getEmail() {
        return this.email;
    }

    /** This is the setEmail method.
     This method sets the email field.
     @param email the email to set*/
    public void setEmail(String email) {
        if(InputValidation.isInputLengthValidStr(email, InputValidation.MAX_LENGTH_50)) {
            this.email = email;
        }
    }

    /** This is the setContactName method.
     This method sets the contactName field.
     @param contactName the contactName to set*/
    public void setContactName(String contactName) {
        if(InputValidation.isInputLengthValidStr(contactName, InputValidation.MAX_LENGTH_50)) {
            this.contactName = contactName;
        }
    }

    /** This is the setContactID method.
     This method sets the contactID field.
     @param contactID the contactID to set*/
    public void setContactID(int contactID) {
        if(InputValidation.isInputLengthValidInt(contactID, InputValidation.MAX_LENGTH_10)) {
            this.contactID = contactID;
        }
    }

    /** This is the toString method.
     This method formats how this object will be printed.
     @return Returns the contact as a string*/
    @Override
    public String toString() {
        /*
            Had to override the toString method to print out the object how I want to,
            in this case print out the objects name
         */
        return this.getContactName();
    }
}
