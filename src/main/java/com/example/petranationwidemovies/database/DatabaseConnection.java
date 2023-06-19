package com.example.petranationwidemovies.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/pnmovies";
    static final String USER = "root";
    static final String PASS = "";

    private static Connection conn;

    public static Connection getInstance() {
        if (conn == null){
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return conn;
        }
        return conn;
    }

}
