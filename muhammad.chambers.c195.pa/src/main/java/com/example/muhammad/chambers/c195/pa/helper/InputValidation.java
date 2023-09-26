package com.example.muhammad.chambers.c195.pa.helper;

/** This class is used to contain the code for input validation.*/
public class InputValidation {
    /** Holds the max length 10, which is used to limit the length of a string*/
    public final static int MAX_LENGTH_10 = 10;
    /** Holds the max length 50, which is used to limit the length of a string*/
    public final static int MAX_LENGTH_50 = 50;
    /** Holds the max length 100, which is used to limit the length of a string*/
    public final static int MAX_LENGTH_100 = 100;

    /** Holds -1, which is used since the database auto increments IDs all IDs would be 1 or higher.
     So, returning -1 implies that the item doesn't exist in the database.*/
    public final static int DOES_NOT_EXIST_IN_DATABASE = -1;
    /** Holds the not found string*/
    public final static String NOT_FOUND = "Not Found";

    /** This is the isInputLengthValidStr method.
     This method is used to compare a string against a length to verify if it is within the length range.
     @param inputValue the string to check
     @param inputLength the length of the string to check by
     @return Returns a boolean; true if the input is valid and false otherwise*/
    public static boolean isInputLengthValidStr(String inputValue, int inputLength) {
        if(inputValue.length() <= inputLength) {
            return true;
        }
        return false;
    }

    /** This is the isInputLengthValidInt method.
     This method is used to compare an integer against a length to verify if it is within the length range.
     @param inputValue the string to check
     @param inputLength the length of the string to check by
     @return Returns a boolean; true if the input is valid and false otherwise*/
    public static boolean isInputLengthValidInt(int inputValue, int inputLength) {
        if(String.valueOf(inputValue).length() <= inputLength) {
            return true;
        }
        return false;
    }
}
