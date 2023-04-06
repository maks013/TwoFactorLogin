package com.example.login2faapp.controller;

import com.example.login2faapp.database.QueryExecutor;
import com.example.login2faapp.ViewSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerRegistrationView {

    @FXML
    private Label registrationMessageLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField registrationUsernameTextField;

    @FXML
    private TextField registrationPasswordPasswordField;

    @FXML
    private TextField repeatRegistrationPasswordPasswordField;


    public void registerButtonOnAction(ActionEvent e) throws IOException, InterruptedException {
        registerUserQuery(e);
    }

    public void cancelRegistrationButtonOnAction(ActionEvent e) throws IOException {
        ViewSwitcher viewSwitcher = new ViewSwitcher();
        viewSwitcher.switchOrCloseView(e, "hello-view");
    }

    private void registerUserQuery(ActionEvent e) throws IOException, InterruptedException {
        String name = "'" + getInputedName() + "'";
        String email = "'" + getInputedEmail() + "'";
        String password = "'" + getInputedPassword() + "'";
        String code = "'" + generateCode() + "'";
        if (checkDataIfBlank(getInputedName(), getInputedEmail(), getInputedPassword())) {
            changeLabelText();
        } else {
            QueryExecutor.executeQuery("INSERT INTO users (\"email\", \"password\", \"verification_code\",\"name\") " +
                    "VALUES" + "(" + email + "," + " CRYPT(" + password + ", gen_salt('bf'))," + code + "," + name + ");");
            ViewSwitcher view = new ViewSwitcher();
            view.switchOrCloseView(e, "hello-view");
            ShowAlert showAlert = new ShowAlert();
            showAlert.showAlert("Registration", getInputedName(), "You have created account successfully");
        }
    }

    private String getInputedName() {
        return nameTextField.getText();
    }

    private String getInputedEmail() {
        if (checkEmail(registrationUsernameTextField.getText()) && !registrationUsernameTextField.getText().isBlank()) {
            return registrationUsernameTextField.getText();
        } else {
            return "";
        }
    }

    private boolean checkEmail(String email) {
        Pattern EMAIL_PATTERN = Pattern.compile(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    private String getInputedPassword() {
        if (checkPassword()) {
            return registrationPasswordPasswordField.getText();
        } else if (registrationPasswordPasswordField.getText().isBlank() || repeatRegistrationPasswordPasswordField.getText().isBlank()) {
            return "";
        } else {
            return "";
        }
    }

    private boolean checkPassword() {
        return registrationPasswordPasswordField.getText().equals(repeatRegistrationPasswordPasswordField.getText());
    }

    private String generateCode() {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int strLength = 5;

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strLength; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();

    }

    private boolean checkDataIfBlank(String name, String email, String password) {
        return name.isEmpty() || email.isEmpty() || password.isEmpty();
    }

    private void changeLabelText() {
        registrationMessageLabel.setText("Incorrect data, try again");
    }

}
