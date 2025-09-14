module com.ecosim {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.ecosim to javafx.fxml;
    exports com.ecosim;
}
