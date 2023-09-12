package com.example.muhammad.chambers.c195.pa.controller;

import com.example.muhammad.chambers.c195.pa.dao.JDBC;
import com.example.muhammad.chambers.c195.pa.dao.UserDAOImpl;
import com.example.muhammad.chambers.c195.pa.helper.DateTimeConversion;
import com.example.muhammad.chambers.c195.pa.helper.FilePath;
import com.example.muhammad.chambers.c195.pa.helper.LoggedIn;
import com.example.muhammad.chambers.c195.pa.helper.ScreenEnum;
import com.example.muhammad.chambers.c195.pa.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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


    @FXML
    private void onLogin (ActionEvent event) throws IOException, SQLException {
        /*
            Note:
                1. Supposed to log ALL login attempts to a text file that'll be stored
                in the root folder
                2. Need to swap out the println methods with alerts
         */
        User userAccount = returnUserObjectIfUsernameExists(usernameField.getText());

        /*
            If the user entered the correct username and password then the program will
            log them in and switch screens to the Main Screen.
         */
        if(userAccount == null) {
            clearLoginFields();
            System.out.println("Login Failed. Account does NOT exist");
        } else if(userAccount.getPassword().equals(passwordField.getText())) {
            //Have to store the username prior to clearing the text fields
            LoggedIn.setLoggedInUsername(usernameField.getText());
            System.out.println("Successful Login!");
            clearLoginFields();
            filePath.switchScreen(event, filePath.getMainFilePath(), ScreenEnum.MAIN.toString());
        } else {
            clearLoginFields();
            System.out.println("Login Failed. Your Username and/or Password was incorrect");
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