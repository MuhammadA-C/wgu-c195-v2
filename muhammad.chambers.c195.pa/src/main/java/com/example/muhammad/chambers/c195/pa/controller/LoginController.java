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
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private FilePath filePath = new FilePath();

    //FXML Fields
    @FXML
    private Text loginTxt;
    @FXML
    private Text passwordTxt;
    @FXML
    private Text usernameTxt;
    @FXML
    private Text topTxtOne;
    @FXML
    private Text topTxtTwo;
    @FXML
    private Button forgotPasswordTxt;
    @FXML
    private Text loginHint;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button exitTxt;
    @FXML
    private TextField zoneIDTxtField;


    private void clearLoginFields() {
        usernameField.clear();
        passwordField.clear();
    }

    private void setTextToLocaleForText(Text textObject, String text) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("LoginScreen", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {
            textObject.setText(resourceBundle.getString(text));
        }
    }

    private void setTextToLocaleForButton(Button buttonObject, String text) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("LoginScreen", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {
            buttonObject.setText(resourceBundle.getString(text));
        }
    }

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


    @FXML
    private void onLogin (ActionEvent event) throws IOException, SQLException {
        User userAccount = returnUserObjectIfUsernameExists(usernameField.getText());

        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            DialogBox.errorAlert("Error Dialog", "Error: You have empty fields. You must fill out all fields prior to clicking log-in");
        } else if(userAccount == null) {
            DialogBox.errorAlert("Error Dialog", "Login Failed. Account does NOT exist");
            logLoginAttemptToTxtFile(usernameField.getText(), false);
            clearLoginFields();
        } else if(userAccount.getPassword().equals(passwordField.getText())) {
            LoggedIn.setLoggedInUsername(usernameField.getText());
            logLoginAttemptToTxtFile(usernameField.getText(), true);
            clearLoginFields();
            filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
        } else {
            DialogBox.errorAlert("Error Dialog", "Login Failed. Your password was incorrect");
            logLoginAttemptToTxtFile(usernameField.getText(), false);
            clearLoginFields();
        }
    }

    @FXML
    void onActionExitApplication(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

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

    @FXML
    private void onMouseEnter (MouseEvent event) {
        forgotPasswordTxt.setTextFill(Color.BLUE);
    }

    @FXML
    private void onMouseExit (MouseEvent event) {
        forgotPasswordTxt.setTextFill(Color.BLACK);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        translateAllTxt();
        zoneIDTxtField.setText("Time Zone: " + DateTimeConversion.getSystemZoneID());

    }
}