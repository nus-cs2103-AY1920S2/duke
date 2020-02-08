package dude;

import java.io.IOException;

import dude.component.Duke;
import dude.component.DukeFactory;
import dude.component.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Duke duke;

    @Override
    public void start(Stage stage) {
        try {
            // Set up the GUI Node hierarchy
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Wire up Duke class (main application logic) and UI components
            MainWindow mainWindow = fxmlLoader.getController();
            this.duke = DukeFactory.createDuke(mainWindow);
            mainWindow.setDuke(duke);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves state (calls duke#saveState which calls IStorage#saveSession) before quitting.
     * However, since UI could already be closed when this method is called, unlikely to report errors
     * while saving state.
     * Does not throw exception.
     */
    @Override
    public void stop() {
        this.duke.saveState();
    }
}
