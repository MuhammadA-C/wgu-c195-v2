package com.example.muhammad.chambers.c195.pa.model;

import com.example.muhammad.chambers.c195.pa.helper.InputValidation;

public class Contact {
    private int contactID;
    private String contactName;
    private String email;


    //Constructor
    public Contact(int contactID, String contactName, String email) {
        this.setContactID(contactID);
        this.setContactName(contactName);
        this.setEmail(email);
    }


    //Getter Methods
    public String getContactName() {
        return this.contactName;
    }

    public int getContactID() {
        return this.contactID;
    }

    public String getEmail() {
        return this.email;
    }


    //Setter Methods
    public void setEmail(String email) {
        if(InputValidation.isInputLengthValidStr(email, InputValidation.MAX_LENGTH_50)) {
            this.email = email;
        }
    }

    public void setContactName(String contactName) {
        if(InputValidation.isInputLengthValidStr(contactName, InputValidation.MAX_LENGTH_50)) {
            this.contactName = contactName;
        }
    }

    public void setContactID(int contactID) {
        if(InputValidation.isInputLengthValidInt(contactID, InputValidation.MAX_LENGTH_10)) {
            this.contactID = contactID;
        }
    }

    @Override
    public String toString() {
        /*
            Had to override the toString method to print out the object how I want to,
            in this case print out the objects name
         */
        return this.getContactName();
    }
}
