package com.example.muhammad.chambers.c195.pa;

import com.example.muhammad.chambers.c195.pa.dao.*;
import com.example.muhammad.chambers.c195.pa.helper.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

/*
     Muhammad Chambers
     Western Governors University
     C195 Software 2
     Term 2
     26 September 2023
 */

/** This class creates the application which allows you to create appointments, customer records, and view reports*/
public class Login extends Application {

    /** This is the start method.
     This method creates the Main Menu Screen when the program starts.
     @param stage the stage to set
     @throws IOException due to scene switching*/
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(ScreenEnum.LOGIN.toString());
        stage.setScene(scene);
        stage.show();
    }

    /** This is the main method.
     This method is where the program starts and executes code.
     @param args the args to use
     @throws SQLException due to the SQL queries*/
    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        Locale.setDefault(new Locale("fr")); //Changes the language for the software
        launch();

        JDBC.closeConnection();
    }
}