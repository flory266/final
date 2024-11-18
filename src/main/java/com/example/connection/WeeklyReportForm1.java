package com.example.connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class WeeklyReportForm1 {

    @FXML
    private Button btnSubmit;

    @FXML
    private TextArea challengesField;

    @FXML
    private TextField classField;

    @FXML
    private TextField moduleField;

    @FXML
    private TextArea recommendationsField;

    @FXML
    void submitWeeklyReport(ActionEvent event) {

        String className = classField.getText().trim();
        String moduleCode = moduleField.getText().trim();
        String challenges = challengesField.getText().trim();
        String recommendations = recommendationsField.getText().trim();

        // Validate input
        if (className.isEmpty() || moduleCode.isEmpty() || challenges.isEmpty() || recommendations.isEmpty()) {
            showAlert("Error", "Please fill in all fields.", Alert.AlertType.ERROR);
            return;
        }

        String query = "INSERT INTO weekly_reports (class_name, module_code, challenges, recommendations) VALUES (?, ?, ?, ?)";

        // Use JDBC method for parameterized query to prevent SQL injection
        boolean isInserted = JDBC.executeParameterizedInsert(query, className, moduleCode, challenges, recommendations);

        if (isInserted) {
            showAlert("Report Submitted", "Your weekly report has been submitted successfully.", Alert.AlertType.INFORMATION);
            clearFields();  // Clear the fields upon successful submission
        } else {
            showAlert("Error", "An error occurred while submitting the report.", Alert.AlertType.ERROR);
        }
    }

    // Clear all text fields after a successful report submission
    private void clearFields() {
        classField.clear();
        moduleField.clear();
        challengesField.clear();
        recommendationsField.clear();
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
