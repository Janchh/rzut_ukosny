module com.example.rzut_ukosny {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    exports rzut;
    opens rzut to javafx.fxml;
}