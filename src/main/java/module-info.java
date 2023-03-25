module com.example.rzut_ukosny {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.rzut_ukosny to javafx.fxml;
    exports com.example.rzut_ukosny;
}