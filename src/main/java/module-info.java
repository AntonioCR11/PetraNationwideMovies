module com.example.petranationwidemovies {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.petranationwidemovies to javafx.fxml;
    exports com.example.petranationwidemovies;
}