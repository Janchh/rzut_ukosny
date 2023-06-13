package rzut;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class NotDoubleException extends Exception{
    @FXML
    Button button2;
    Alert alert = new Alert(Alert.AlertType.ERROR);{
        alert.setTitle("Runtime error");
        alert.setHeaderText("Input data type not a double.");
        alert.setContentText("How to fix it:\n" +
                "Remove all non-decimal characters from input; \nUse dot as a separator.");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });}
}
