package com.example.connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    void onLoginclick(ActionEvent event) {
        String username = txtName.getText();
        String password = txtPassword.getText();

        if (JDBC.validateLogin(username, password)) {
            showAlert("Login Successful", "Welcome, " + username + "!");
            Stage stage = (Stage) btnLogin.getScene().getWindow();

            // Use HelloApplication to navigate to Principal Lecturer page
            HelloApplication.loadPage(stage, "com/example/connection/PrincipalLecturer.fxml", "Principal Lecturer", 600, 400);
        } else {
            showAlert("Login Failed", "Incorrect username or password. Please try again.");
        }
    }

    // Helper method to show alert messages
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}