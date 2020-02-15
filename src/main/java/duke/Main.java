package duke;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import duke.javafx.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Duke duke = new Duke();

    public Main() throws IOException {
    }

    /**
     * Starts the scene builder.
     * @param stage The stage for the scene.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Freddy's Duke");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Terminates the scene builder.
     * @throws Exception If program is unable to terminate.
     */

    @Override
    public void stop() throws Exception {
        TimeUnit.MILLISECONDS.sleep(50);
    }
}