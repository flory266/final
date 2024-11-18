module com.example.connection {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.connection to javafx.fxml;
    exports com.example.connection;
}