package com.example.muhammad.chambers.c195.pa.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/** This class is used to create dialog boxes.*/
public class DialogBox {
    /** Holds the error alert*/
    private static Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    /** Holds the confirmation alert*/
    private static Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    /** Holds the notification alert*/
    private static Alert notificationAlert = new Alert(Alert.AlertType.INFORMATION);

    /** This is the errorAlert method.
     This method creates an error alert.
     @param typeOfAlert is the text that says the type of alert, i.e. error, confirmation, notification, etc...
     @param context is the text that will be shown in the alert box*/
    public static void errorAlert(String typeOfAlert, String context) {
        errorAlert.setTitle(typeOfAlert);
        errorAlert.setContentText(context);
        errorAlert.showAndWait();
    }

    /** This is the notificationAlert method.
     This method creates a notification alert.
     @param typeOfAlert is the text that says the type of alert, i.e. error, confirmation, notification, etc...
     @param context is the text that will be shown in the alert box*/
    public static void notificationAlert(String typeOfAlert, String context) {
        notificationAlert.setTitle(typeOfAlert);
        notificationAlert.setContentText(context);
        notificationAlert.showAndWait();
    }

    /** This is the confirmationAlert method.
     This method creates a confirmation alert.
     @param typeOfAlert is the text that says the type of alert, i.e. error, confirmation, notification, etc...
     @param context is the text that will be shown in the alert box
     @return Returns an Optional<ButtonType> object*/
    public static Optional<ButtonType> confirmationAlert(String typeOfAlert, String context) {
        confirmationAlert.setTitle(typeOfAlert);
        confirmationAlert.setContentText(context);

        Optional<ButtonType> result = confirmationAlert.showAndWait();

        return result;
    }
}
