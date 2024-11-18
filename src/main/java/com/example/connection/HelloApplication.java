package com.example.connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        // Example of loading PrincipalLecturer UI
        loadPage(stage, "PrincipalLecturer.fxml", "Principal Lecturer", 320, 240);

    }

    // Generic method to load the FXML page
    public static void loadPage(Stage stage, String fxml, String title, int width, int height) {
        try {
            // Correct path with leading '/'
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/connection/PrincipalLecturer.fxml" + fxml));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            stage.setTitle("Principal Lecturer");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load FXML file: " + fxml);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
