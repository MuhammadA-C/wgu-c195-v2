package com.example.muhammad.chambers.c195.pa.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogBox {
    private static Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    private static Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    private static Alert notificationAlert = new Alert(Alert.AlertType.INFORMATION);

    public static void errorAlert(String typeOfAlert, String context) {
        errorAlert.setTitle(typeOfAlert);
        errorAlert.setContentText(context);
        errorAlert.showAndWait();
    }

    public static void notificationAlert(String typeOfAlert, String context) {
        notificationAlert.setTitle(typeOfAlert);
        notificationAlert.setContentText(context);
        notificationAlert.showAndWait();
    }

    public static Optional<ButtonType> confirmationAlert(String typeOfAlert, String context) {
        confirmationAlert.setTitle(typeOfAlert);
        confirmationAlert.setContentText(context);

        Optional<ButtonType> result = confirmationAlert.showAndWait();

        return result;
    }
}
