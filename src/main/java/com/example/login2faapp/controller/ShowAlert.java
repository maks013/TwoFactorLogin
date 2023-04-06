package com.example.login2faapp.controller;

import javafx.scene.control.Alert;

public class ShowAlert {
    public void showAlert(String title, String name, String context) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Hi, " + name);
        alert.setContentText(context);
        alert.showAndWait();
    }
}