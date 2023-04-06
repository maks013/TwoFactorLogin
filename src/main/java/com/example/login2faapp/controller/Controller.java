package com.example.login2faapp.controller;

import com.example.login2faapp.database.QueryExecutor;
import com.example.login2faapp.email.EmailSender;
import com.example.login2faapp.ViewSwitcher;
import com.example.login2faapp.database.UserDataFromDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.mindrot.jbcrypt.BCrypt;

public class Controller {

    UserDataFromDatabase user = new UserDataFromDatabase();
    boolean changeView = false;
    public static String code = "";
    public static String email = "";
    public static String name = "";

    @FXML
    private Button cancelButton;

    @FXML
    public Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordPasswordField;

    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(ActionEvent e) throws IOException{
        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
          validateLogin();
          if(changeView){
              sendEmailWithCode();
              changeViewToVerifing(e);
          }
        } else {
            loginMessageLabel.setText("Enter your login and password");
        }
    }

    public void switchToRegistrationButtonOnAction(ActionEvent e) throws IOException {
        ViewSwitcher viewSwitcher = new ViewSwitcher();
        viewSwitcher.switchOrCloseView(e,"registration-view");
    }

    private void sendEmailWithCode() {
        EmailSender emailSender = new EmailSender();
        emailSender.setRecipient(email);
        emailSender.setTitle("Verification code");
        emailSender.setContent("Your verification code:\n" +
                code);
        try {
            emailSender.messageSend();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateLogin() {
        String inputedEmail = usernameTextField.getText();
        String inputedPassword = passwordPasswordField.getText();
        fetchDataFromDatabase(inputedEmail);
        checkData(inputedEmail,inputedPassword,this.user.getUserEmailFromDatabase(),this.user.getUserPasswordFromDatabase());
    }

    private void fetchDataFromDatabase(String inputedEmail) {
        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT * FROM users WHERE email = '" + inputedEmail + "'");
            result.next();
            user.setUserEmailFromDatabase(result.getString("email"));
            email = user.getUserEmailFromDatabase();
            user.setUserPasswordFromDatabase(result.getString("password"));
            user.setVerificationCodeFromDatabase(result.getString("verification_code"));
            code = user.getVerificationCodeFromDatabase();
            user.setNameFromDatabase(result.getString("name"));
            name = user.getNameFromDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkData(String inputedEmail,String inputedPassword,String userEmailFromDatabase,String userPasswordFromDatabase){
        if (!inputedEmail.equals(userEmailFromDatabase) || !BCrypt.checkpw(inputedPassword, userPasswordFromDatabase)) {
            loginMessageLabel.setText("Incorrect email or password");
        } else {
            changeView=true;
        }
    }

    private void changeViewToVerifing(ActionEvent e) throws IOException {
        ViewSwitcher viewSwitcher = new ViewSwitcher();
        viewSwitcher.switchOrCloseView(e,"verify-view");
    }

    public String getVerificationCodeFromUserDatabase(){
        return code;
    }

    public static String getName() {
        return name;
    }
}