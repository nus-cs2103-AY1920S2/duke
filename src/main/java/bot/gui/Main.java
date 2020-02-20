package bot.gui;

import java.io.IOException;

import bot.Duke;
import bot.loadsave.AliasLoader;
import bot.store.AliasStorage;
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
 *
 * <p>Adapted from Main in JavaFX tutorial found at
 * https://github.com/se-edu/duke/tree/master/tutorials
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
                            new GraphicalUi(fxmlLoader
                                    .<MainWindow>getController()
                                    .getChatContainer()
                                    .getChildren()
                            ),
                            new TaskStorage(),
                            new TasksToDisk(
                                    Duke.FILE_DIRECTORY,
                                    Duke.FILE_NAME_TASKS
                            ),
                            new AliasStorage(),
                            new AliasLoader(
                                    Duke.FILE_DIRECTORY,
                                    Duke.FILE_NAME_ALIASES
                            ))
            );
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}