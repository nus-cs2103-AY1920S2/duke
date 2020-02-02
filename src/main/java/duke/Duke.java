package duke;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.MainWindow;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;

    /**
     * Starts the Duke application.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setOnCloseRequest(new EventHandler<>() {
                @Override
                public void handle(WindowEvent event) {
                    event.consume();
                    fxmlLoader.<MainWindow>getController().closeWindowButtonClicked();
                }
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}