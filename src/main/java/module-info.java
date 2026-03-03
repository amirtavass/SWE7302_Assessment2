module com.cinema.modern {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.cinema.modern.ui to javafx.graphics, javafx.fxml;


    opens com.cinema.modern.api to javafx.base;
    opens com.cinema.modern.core to javafx.base;

    exports com.cinema.modern;
    exports com.cinema.modern.ui;
    exports com.cinema.modern.api;
    exports com.cinema.modern.core;
}