module at.technikum.firstui {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;

    opens at.technikum.firstui to javafx.fxml;
    opens at.technikum.firstui.view to javafx.fxml;
    opens at.technikum.firstui.entity;

    exports at.technikum.firstui;
}