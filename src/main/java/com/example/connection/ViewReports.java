package com.example.connection;

import com.example.model.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewReports {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/PrincipalLectures";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "999999";

    @FXML
    private TableView<Report> reportTable;

    @FXML
    private TableColumn<Report, String> classColumn;

    @FXML
    private TableColumn<Report, String> moduleColumn;

    @FXML
    private TableColumn<Report, String> challengesColumn;

    @FXML
    private TableColumn<Report, String> recommendationsColumn;

    @FXML
    private TableColumn<Report, String> dateSubmittedColumn;

    @FXML
    public void initialize() {
        try {
            // Fetch data from the database and populate the table
            ObservableList<Report> reportsList = retrieveReports();
            setupTableColumns();
            reportTable.setItems(reportsList);
        } catch (SQLException e) {
            showErrorAlert("Failed to load reports from the database: " + e.getMessage());
        }
    }

    // Method to set up table columns
    private void setupTableColumns() {
        classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
        moduleColumn.setCellValueFactory(new PropertyValueFactory<>("moduleCode"));
        challengesColumn.setCellValueFactory(new PropertyValueFactory<>("challenges"));
        recommendationsColumn.setCellValueFactory(new PropertyValueFactory<>("recommendations"));
        dateSubmittedColumn.setCellValueFactory(new PropertyValueFactory<>("dateSubmitted"));
    }

    // Method to retrieve reports from the database
    private ObservableList<Report> retrieveReports() throws SQLException {
        List<Report> reportsList = new ArrayList<>();
        String query = "SELECT * FROM weekly_reports";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String className = resultSet.getString("class_name");
                String moduleCode = resultSet.getString("module_code");
                String challenges = resultSet.getString("challenges");
                String recommendations = resultSet.getString("recommendations");
                String dateSubmitted = resultSet.getString("submission_date");

                Report report = new Report(className, moduleCode, challenges, recommendations, dateSubmitted);
                reportsList.add(report);
            }
        } catch (SQLException e) {
            showErrorAlert("SQL Error: " + e.getMessage());
            throw e;
        }
        return FXCollections.observableArrayList(reportsList);
    }

    // Method to show an error alert
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
