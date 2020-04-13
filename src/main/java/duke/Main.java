package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private String filePath = "data" + File.separator + "duke.txt";

    private Duke duke = new Duke(filePath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Ding Ding!");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/photo2.jpg")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}