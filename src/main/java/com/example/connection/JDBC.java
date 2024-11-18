package com.example.connection;

import com.example.model.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;

public class JDBC {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/PrincipalLectures";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "999999";

    // Establish a database connection
    public static Connection connecting() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    // Insert a new user into the database
    public static boolean insertUser(String username, String password, String role) {
        String insertQuery = "INSERT INTO users (Username, password, role) VALUES (?, ?, ?)";
        try (Connection connection = connecting();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("User inserted successfully. Rows affected: " + rowsAffected);
            return rowsAffected > 0;  // Returns true if insertion was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Execute an insertion query
    public static int executeInsertion(String query) {
        try (Connection connection = connecting();
             Statement stmt = connection.createStatement()) {

            stmt.executeUpdate(query);
            return 1;  // Indicates success
        } catch (SQLException e) {
            showAlert("Error", "Database insertion failed: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
            return 0;
        }
    }

    // Execute an insertion query with parameters (for improved security)
    public static boolean executeParameterizedInsert(String query, String className, String moduleCode, String challenges, String recommendations) {
        try (Connection connection = connecting();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set parameters for the prepared statement
            preparedStatement.setString(1, className);
            preparedStatement.setString(2, moduleCode);
            preparedStatement.setString(3, challenges);
            preparedStatement.setString(4, recommendations);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;  // Return true if the insertion was successful

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to insert data: " + e.getMessage(), Alert.AlertType.ERROR);
            return false;
        }
    }

    // Validate user login
    public static boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE Username = ? AND password = ?";
        try (Connection connection = connecting();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();  // Returns true if a matching record is found

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve reports as an ObservableList
    public static ObservableList<Report> retrieveReports() {
        String query = "SELECT * FROM weekly_reports";
        ObservableList<Report> reportsList = FXCollections.observableArrayList();

        try (Connection connection = connecting();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String className = resultSet.getString("class_name");
                String moduleCode = resultSet.getString("module_code");
                String challenges = resultSet.getString("challenges");
                String recommendations = resultSet.getString("recommendations");
                String submissionDate = resultSet.getString("submission_date");

                reportsList.add(new Report(className, moduleCode, challenges, recommendations, submissionDate));
            }

        } catch (SQLException e) {
            showAlert("Error", "Failed to retrieve reports: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }

        return reportsList;
    }

    // Close the database connection
    public static void closeSql() {
        try {
            Connection connection = connecting();
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close database connection", e);
        }
    }

    // Helper method to display alerts
    private static void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
