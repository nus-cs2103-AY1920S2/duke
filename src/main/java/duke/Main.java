package duke;

//@@author j-lum-reused
//Reused from JavaFX Tutorial Part 4 with modifications
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A Graphical User Interface for Duke.
 */
public class Main extends Application {
    private Duke duke = new Duke("./data/duke.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane mainWindow = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            Scene scene = new Scene(mainWindow);

            stage.setTitle("Duke");
            stage.setScene(scene);
            stage.sizeToScene();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//@@author
