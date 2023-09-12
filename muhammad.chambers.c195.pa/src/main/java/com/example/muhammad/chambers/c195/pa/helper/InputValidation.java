package com.example.muhammad.chambers.c195.pa.helper;

public class InputValidation {
    public final static int MAX_LENGTH_10 = 10;
    public final static int MAX_LENGTH_50 = 50;
    public final static int MAX_LENGTH_100 = 100;

    /*
        Since the database auto increments IDs all IDs would be 1 or higher.
        So, returning -1 implies that the item doesn't exist in the database.
     */
    public final static int DOES_NOT_EXIST_IN_DATABASE = -1;
    public final static String NOT_FOUND = "Not Found";

    public static boolean isInputLengthValidStr(String inputValue, int inputLength) {
        if(inputValue.length() <= inputLength) {
            return true;
        }
        return false;
    }

    public static boolean isInputLengthValidInt(int inputValue, int inputLength) {
        if(String.valueOf(inputValue).length() <= inputLength) {
            return true;
        }
        return false;
    }

    public static void characterLength(String field, int length) {
        System.out.println(field + " has a max length of " + length + " characters");
    }
}
