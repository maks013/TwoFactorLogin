package com.example.login2faapp.database;

public class UserDataFromDatabase {
    public String userEmailFromDatabase;
    public String userPasswordFromDatabase;
    public String verificationCodeFromDatabase;
    public String nameFromDatabase;

    public String getUserEmailFromDatabase() {
        return userEmailFromDatabase;
    }

    public void setUserEmailFromDatabase(String userEmailFromDatabase) {
        this.userEmailFromDatabase = userEmailFromDatabase;
    }

    public String getUserPasswordFromDatabase() {
        return userPasswordFromDatabase;
    }

    public void setUserPasswordFromDatabase(String userPasswordFromDatabase) {
        this.userPasswordFromDatabase = userPasswordFromDatabase;
    }

    public String getVerificationCodeFromDatabase() {
        return verificationCodeFromDatabase;
    }

    public void setVerificationCodeFromDatabase(String verificationCode) {
        this.verificationCodeFromDatabase = verificationCode;
    }

    public String getNameFromDatabase() {
        return nameFromDatabase;
    }

    public void setNameFromDatabase(String nameFromDatabase) {
        this.nameFromDatabase = nameFromDatabase;
    }
}
