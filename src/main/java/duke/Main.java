package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    /**
     * This is the only Duke instance created, to handle everything.
     */
    private Duke duke = new Duke();

    /**
     * Creates the Window and Scenes to represent the Graphical User Interface (GUI).
     *
     * @param stage Stage for the GUI to present the user with visuals.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("E-1337: Personal Assistant");
            stage.setScene(scene);
            stage.sizeToScene();
            stage.setMinWidth(625);
            stage.setMinHeight(400);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}