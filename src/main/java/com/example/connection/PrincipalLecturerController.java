package com.example.connection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class PrincipalLecturerController {

    @FXML
    private Label lblName;

    @FXML
    private Text ttlStuNumId;

    @FXML
    private Pane displayContent;

    @FXML
    private Pane sidePaneId;

    @FXML
    private Text txtDModified;

    @FXML
    private Text txtPrincipal;

    @FXML
    private Text txtTClasses;

    @FXML
    private Text txtTModules;

    @FXML
    private Text txtTClassesTotal;

    @FXML
    private Text txtTModulesTotal;

    @FXML
    private Text txtLoadViewReports;

    @FXML
    private Text txtLoadWeeklyReport;

    @FXML
    private Text txtLogout;

    // Method to load the View Reports page
    @FXML
    void loadViewReports(MouseEvent event) {
        loadFXMLToPane("viewreports.fxml", "View Reports");
    }

    // Method to load the Weekly Report form
    @FXML
    void loadWeeklyReport(MouseEvent event) {
        loadFXMLToPane("WeeklyReportform1.fxml", "Weekly Report Form");
    }

    // Method to log out and return to the login screen
    @FXML
    void logout(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Scene loginScene = new Scene(loader.load());
            Stage stage = (Stage) displayContent.getScene().getWindow();
            stage.setScene(loginScene);
            stage.show();
        } catch (IOException e) {
            showAlert("Error", "Failed to load login screen: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Helper method to load FXML files into the main content pane
    private void loadFXMLToPane(String fxmlFile, String description) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Node content = fxmlLoader.load();
            displayContent.getChildren().setAll(content);
        } catch (IOException e) {
            showAlert("Error", "Failed to load " + description + ": " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Helper method to show an alert dialog
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
