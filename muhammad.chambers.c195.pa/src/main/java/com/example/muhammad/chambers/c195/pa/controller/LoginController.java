package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.JDBC;
import com.example.muhammad.chambers.c195.pa.dao.UserDAOImpl;
import com.example.muhammad.chambers.c195.pa.helper.*;
import com.example.muhammad.chambers.c195.pa.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/** This class holds the code for the login fxml file*/
public class LoginController implements Initializable {
    /** Holds a reference to the file path class*/
    private FilePath filePath = new FilePath();

    /** Holds the login text*/
    @FXML
    private Text loginTxt;
    /** Holds the password text*/
    @FXML
    private Text passwordTxt;
    /** Holds the username text*/
    @FXML
    private Text usernameTxt;
    /** Holds the top text one*/
    @FXML
    private Text topTxtOne;
    /** Holds the top text two*/
    @FXML
    private Text topTxtTwo;
    /** Holds the forgot password text button*/
    @FXML
    private Button forgotPasswordTxt;
    /** Holds the login hint text*/
    @FXML
    private Text loginHint;
    /** Holds the username text field*/
    @FXML
    private TextField usernameField;
    /** Holds the password text field*/
    @FXML
    private TextField passwordField;
    /** Holds the exit button*/
    @FXML
    private Button exitTxt;
    /** Holds the zone id text field*/
    @FXML
    private TextField zoneIDTxtField;


    /** This is the clearLoginFields method.
     This method is used to clear the text fields.*/
    private void clearLoginFields() {
        usernameField.clear();
        passwordField.clear();
    }

    /** This is the setTextToLocaleForText method.
     This method is used to localize the text for text objects.
     @param textObject the text object
     @param text the text to localize*/
    private void setTextToLocaleForText(Text textObject, String text) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("LoginScreen", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {
            textObject.setText(resourceBundle.getString(text));
        }
    }

    /** This is the setTextToLocaleForButton method.
     This method is used to localize the text for button objects.
     @param buttonObject the button object
     @param text the text to localize*/
    private void setTextToLocaleForButton(Button buttonObject, String text) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("LoginScreen", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {
            buttonObject.setText(resourceBundle.getString(text));
        }
    }

    /** This is the getTextToLocale method.
     This method is used to return a string of that is localized.
     @param text the text to localize
     @return Returns a string*/
    private String getTextToLocale(String text) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("LoginScreen", Locale.getDefault());
        String translatedStr = null;

        if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {
            translatedStr = resourceBundle.getString(text);
        }
        return translatedStr;
    }


    /** This is the translateAllTxt method.
     This method localizes all the text.*/
    private void translateAllTxt() {
        setTextToLocaleForText(topTxtOne, "topTxtOne");
        setTextToLocaleForText(topTxtTwo, "topTxtTwo");
        setTextToLocaleForText(passwordTxt, "passwordTxt");
        setTextToLocaleForText(usernameTxt, "usernameTxt");
        setTextToLocaleForText(loginTxt, "loginTxt");
        setTextToLocaleForText(loginHint, "loginHint");
        setTextToLocaleForButton(forgotPasswordTxt, "forgotPasswordTxt");
        setTextToLocaleForButton(exitTxt, "exitTxt");
    }

    /** This is the returnUserObjectIfUsernameExists method.
     This method is used to return a user object based on username.
     @param username the username to look up a user object for
     @return Returns a user object*/
    private User returnUserObjectIfUsernameExists(String username) throws SQLException {
        /*
            Takes the entered username and compares the usernames in the database with
            the username that was entered.

            If there's a match then the user object for said username will be returned.
            Otherwise, NULL will be returned.
         */
        for(User userObject: UserDAOImpl.getUsersList()) {
            if(userObject.getUserName().equals(username)) {
                return userObject;
            }
        }
        return null;
    }

    /** This is the logLoginAttemptToTxtFile method.
     This method is used to log all login attempts.
     @param username the username
     @param loginAttemptSuccess specify if the login was successful or not by using true for successful and false otherwise*/
    private void logLoginAttemptToTxtFile(String username, boolean loginAttemptSuccess) throws IOException {
        FileWriter fwVariable = new FileWriter("login_activity.txt", true);
        PrintWriter pwVariable = new PrintWriter(fwVariable);

        if(loginAttemptSuccess) {
            pwVariable.println("User " + username + " successfully logged in at " + DateTimeConversion.getCurrentDateTimeFormatted().toLocalDate() + " " + DateTimeConversion.getCurrentDateTimeFormatted().toLocalTime());
        } else {
            pwVariable.println("User " + username + " gave invalid log-in at " + DateTimeConversion.getCurrentDateTimeFormatted().toLocalDate() + " " + DateTimeConversion.getCurrentDateTimeFormatted().toLocalTime());
        }

        pwVariable.close();
    }


    /** This is the onLogin method.
     This method is used to check if the entered password and username is correct, log login attempts,
     and take the user to the mains screen if logged in successfully.
     @param event the event*/
    @FXML
    private void onLogin(ActionEvent event) throws IOException, SQLException {
        User userAccount = returnUserObjectIfUsernameExists(usernameField.getText());

        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            DialogBox.errorAlert(getTextToLocale("errorDialog"), getTextToLocale("emptyFields"));
        } else if(userAccount == null) {
            DialogBox.errorAlert(getTextToLocale("errorDialog"), getTextToLocale("accountExists"));
            logLoginAttemptToTxtFile(usernameField.getText(), false);
            clearLoginFields();
        } else if(userAccount.getPassword().equals(passwordField.getText())) {
            LoggedIn.setLoggedInUsername(usernameField.getText());
            logLoginAttemptToTxtFile(usernameField.getText(), true);
            clearLoginFields();
            filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
        } else {
            DialogBox.errorAlert(getTextToLocale("errorDialog"), getTextToLocale("incorrectPassword"));
            logLoginAttemptToTxtFile(usernameField.getText(), false);
            clearLoginFields();
        }
    }

    /** This is the onActionExitApplication method.
     This method is used to exit out of the application.
     @param event the event*/
    @FXML
    void onActionExitApplication(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    /** This is the onClickShowHint method.
     This method is used to show the login information.
     @param event the event*/
    @FXML
    private void onClickShowHint (ActionEvent event) {
        final double notVisible = 0.0;
        final double visible = 1;

        if(loginHint.getOpacity() == notVisible) {
            loginHint.setOpacity(visible);
        } else {
            loginHint.setOpacity(notVisible);
        }
    }

    /** This is the onMouseEnter method.
     This method is used to change the color to blue when moused over.
     @param event the event*/
    @FXML
    private void onMouseEnter (MouseEvent event) {
        forgotPasswordTxt.setTextFill(Color.BLUE);
    }

    /** This is the onMouseExit method.
     This method is used to change the color to back to normal when mouse is no longer over the text.
     @param event the event*/
    @FXML
    private void onMouseExit (MouseEvent event) {
        forgotPasswordTxt.setTextFill(Color.BLACK);
    }

    /** This is the initialize method.
     This method is used to initialize any values once the screen loads.
     @param url the url
     @param rb the resource bundle*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        translateAllTxt();
        zoneIDTxtField.setText("Time Zone: " + DateTimeConversion.getSystemZoneID());

    }
}