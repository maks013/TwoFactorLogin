module com.example.login2faapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    requires java.mail;


    opens com.example.login2faapp to javafx.fxml;
    exports com.example.login2faapp;
    exports com.example.login2faapp.controller;
    opens com.example.login2faapp.controller to javafx.fxml;
    exports com.example.login2faapp.database;
    opens com.example.login2faapp.database to javafx.fxml;
    exports com.example.login2faapp.email;
    opens com.example.login2faapp.email to javafx.fxml;
}