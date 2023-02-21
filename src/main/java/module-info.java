module com.example.todo_new {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires javafx.media;

    opens com.example.todo_new to javafx.fxml;
    exports com.example.todo_new;
}