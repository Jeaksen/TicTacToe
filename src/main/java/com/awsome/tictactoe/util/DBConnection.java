package com.awsome.tictactoe.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final static String USERNAME = "tictacteo";
    private final static String PASSWORD = "#verihardpassword";
    private final static String CONNSTRING = "jdbc:mysql://den1.mysql6.gear.host/tictacteo?useSSL=false&verifyServerCertificate=false";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNSTRING, USERNAME, PASSWORD);
    }
}
