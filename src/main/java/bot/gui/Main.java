package bot.gui;

import java.io.IOException;

import bot.Duke;
import bot.store.TaskStorage;

import bot.command.CommandParser;

import bot.loadsave.TasksToDisk;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class extends the FXML application,
 * and it is called by the Launcher to start
 * the graphical user interface in a window
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBaron(
                    new Baron(
                            new CommandParser(),
                            new TaskStorage(),
                            new TasksToDisk(
                                    Duke.FILE_DIRECTORY,
                                    Duke.FILE_NAME
                            ),
                            new GraphicalUi(fxmlLoader
                                    .<MainWindow>getController()
                                    .getChatContainer()
                                    .getChildren()
                            )
                    )
            );
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}