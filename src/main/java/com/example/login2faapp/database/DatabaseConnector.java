package com.example.login2faapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.example.login2faapp.database.DatabaseConfig.*;

public class DatabaseConnector {

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
