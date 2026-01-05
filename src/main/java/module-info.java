module com.kolesalite {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports com.kolesalite.controller to javafx.fxml;
    exports com.kolesalite;
    exports com.kolesalite.model;

    opens com.kolesalite.controller to javafx.fxml;
    opens com.kolesalite.view to javafx.fxml;
}