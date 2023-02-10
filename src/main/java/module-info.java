module com.example.songlib {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.songlib to javafx.fxml;
    exports com.example.songlib;
}