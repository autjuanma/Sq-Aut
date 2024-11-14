package com.devaura.qa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.io.IOException;

public class SQLiteConnectionTest {

    // JDBC URL for SQLite database
    private static final String DATABASE_URL = "jdbc:sqlite:sample.db";
    private static final String APP_IMAGE_PATH = "resources/DB.Browser.for.SQLite-v3.13.1-x86.64-v2.AppImage";

    public static void main(String[] args) {
        SQLiteConnectionTest test = new SQLiteConnectionTest();
        test.testConnection();
    }

    public void testConnection() {
        Connection connection = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(DATABASE_URL);
            if (connection != null) {
                System.out.println("Connection to SQLite has been established.");
                // If connection is successful, launch the DB Browser
                launchDBBrowser();
            }
        } catch (SQLException e) {
            System.out.println("Connection to SQLite has failed.");
            e.printStackTrace();
        } finally {
            // Close the connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Failed to close the connection.");
                ex.printStackTrace();
            }
        }
    }

    private void launchDBBrowser() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(APP_IMAGE_PATH);
        processBuilder.directory(new File("resources"));

        try {
            // Start the process
            Process process = processBuilder.start();
            System.out.println("DB Browser for SQLite is launching...");
            process.waitFor(); // Wait for the process to finish
        } catch (IOException | InterruptedException e) {
            System.err.println("Error launching DB Browser for SQLite.");
            e.printStackTrace();
        }
    }
}