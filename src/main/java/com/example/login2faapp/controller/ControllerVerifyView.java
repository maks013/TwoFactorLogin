package com.example.login2faapp.controller;

import com.example.login2faapp.ViewSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.io.IOException;

public class ControllerVerifyView {

    @FXML
    private TextField verificationCodeTextField;

    @FXML
    private Label verifyMessageLabel;

    public void verifyButtonOnAction(ActionEvent e) throws IOException {
        Controller controller = new Controller();
        String codeFromTextField = verificationCodeTextField.getText();
        if(controller.getVerificationCodeFromUserDatabase().equals(codeFromTextField)){
            changeViewToHello(e);
            ShowAlert showAlert = new ShowAlert();
            showAlert.showAlert("Login", Controller.getName(),"You have successfully logged in" );
        }else{
            verifyMessageLabel.setText("Incorrect code");
        }
    }

    public void cancelButtonOnAction(ActionEvent e) {
        try {
            changeViewToHello(e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void changeViewToHello(ActionEvent e) throws IOException {
        ViewSwitcher viewSwitcher = new ViewSwitcher();
        viewSwitcher.switchOrCloseView(e,"hello-view");
    }
}
