package com.example.muhammad.chambers.c195.pa.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogBox {
    private static Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    private static Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);

    public static void errorAlert(String title, String context) {
        errorAlert.setTitle(title);
        errorAlert.setContentText(context);
        errorAlert.showAndWait();
    }

    public static Optional<ButtonType> confirmationAlert(String title, String context) {
        confirmationAlert.setTitle(title);
        confirmationAlert.setContentText(context);

        Optional<ButtonType> result = confirmationAlert.showAndWait();

        return result;
    }
}
