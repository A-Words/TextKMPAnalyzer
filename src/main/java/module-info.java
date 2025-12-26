module com.awords.textkmpanalyzer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.awords.textkmpanalyzer to javafx.fxml;
    exports com.awords.textkmpanalyzer;
}