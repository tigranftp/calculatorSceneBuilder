module com.example.testscenebuilder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testscenebuilder to javafx.fxml;
    exports com.example.testscenebuilder;
}