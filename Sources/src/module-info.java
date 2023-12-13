module ollert {
    requires javafx.controls;
    requires javafx.fxml;


    opens ollert to javafx.fxml;
    exports ollert;
}