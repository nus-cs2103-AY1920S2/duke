package duke;

import java.io.IOException;

import commands.CommandInvoker;
import database.Storage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke.Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Doki Doki Task Bot");
            stage.centerOnScreen();
            stage.setResizable(false);
            //Get icon code snippet from https://stackoverflow.com/questions/20094620/set-icon-on-stage-in-javafx/20094784
            stage.getIcons().add(new Image(("/images/mitsuri.jpg")));

            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        Storage.saveMemory();
        CommandInvoker.stop();
        super.stop();
    }
}
