package duke.gui;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Duke duke = new Duke();
    // Robot icon By Bilboq - Own work, Public Domain,
    // https://commons.wikimedia.org/w/index.php?curid=1118420
    private Image launcherIcon = new Image(
            this.getClass().getResourceAsStream("/images/launcher-icon.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Mr. Robot");
            stage.getIcons().add(launcherIcon);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the application should stop, and provides a
     * convenient place to prepare for application exit and destroy resources.
     *
     * <p>
     * The implementation of this method provided by the Application class does nothing.
     * </p>
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @throws Exception if something goes wrong
     */
    @Override
    public void stop() throws Exception {
        // Sleep for 1s before exit
        TimeUnit.SECONDS.sleep(1);
        super.stop();
    }
}