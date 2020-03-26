package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 *
 * <p>Adapted from JavaFx Tutorial initially written by Jeffry Lum.
 */
public class Main extends Application {
    private final String mainWindowPath = "/view/MainWindow.fxml";
    private final String applicationName = "Duke";
    
    private Duke duke = new Duke();
    
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(mainWindowPath));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
