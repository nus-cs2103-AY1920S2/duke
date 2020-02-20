package duke.gui;

import duke.Duke;
import duke.DukeException;
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
            stage.setScene(prepareScene(loadFxml()));
            stage.show();
        } catch (DukeException e) {
            System.out.print(e.getMessage());
        }
    }

    //@@author Exeexe93-reused
    //Reused from https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart4.md.

    /**
     * Load the MainWindow fxml file and connect logic to MainWindow controller.
     *
     * @return Ui style loaded.
     */
    private AnchorPane loadFxml() throws DukeException {
        //@@author Exeexe93
        AnchorPane ap = null;
        //@@author Jeffry Lum
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            ap = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setDuke(duke);
        } catch (IOException e) {
            e.printStackTrace();
            //@@author Exeexe93
        } catch (NullPointerException e) {
            throw new DukeException("Error loading the fxml file from MainWindow.fxml");
        }
        return ap;
    }

    /**
     * Prepare scene based on the component given.
     */
    private Scene prepareScene(AnchorPane component) {
        return new Scene(component);
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