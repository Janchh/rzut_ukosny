package rzut;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainFrame extends Application {

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainFrame.class.getResource("main-frame.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Rzut ukośny");
        //stage.getIcons().add(new Image(MainFrame.class.getResourceAsStream("/com/logo.png")));
        stage.setScene(scene);
        stage.show();
        Controller controller = fxmlLoader.getController();
        //controller.drawShapes();

    }

    public void stop(){
        File f = new File("output.csv");
        f.delete();
        System.out.println("kasuj");
    }


    public static void main(String[] args) {
        launch();
    }


}
