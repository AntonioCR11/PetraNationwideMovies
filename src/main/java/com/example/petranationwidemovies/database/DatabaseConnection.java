package com.example.petranationwidemovies.database;

import com.example.petranationwidemovies.custom_ui.Data;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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

    public static void migrate() {
        String filePath = "pnmovies.sql";
        try (Connection conn = DatabaseConnection.getInstance();
             Statement stmt = conn.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            StringBuilder statementBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("--")) {
                    continue;
                }
                statementBuilder.append(line);
                if (line.endsWith(";")) {
                    String sqlStatement = statementBuilder.toString();
                    stmt.execute(sqlStatement);
                    statementBuilder.setLength(0);
                }
            }

            System.out.println("SQL file executed successfully.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + filePath, e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filePath, e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
