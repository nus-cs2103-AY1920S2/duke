package duke.gui;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * This is where the program run when startup for the GUI. It set up the components such as Scene and ArchorPane
     * before start the window. Print any exception occurs.
     *
     * @param stage window for the other components to set up and display to user
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delay 800ms to close window when an exit request received.
     *
     * @throws Exception Occurs when sleep method being interrupted
     */
    @Override
    public void stop() throws Exception {
        TimeUnit.MILLISECONDS.sleep(800);
    }
}