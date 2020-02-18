package dukeproj;

import java.io.IOException;

import dukeproj.enums.SayType;
import dukeproj.gui.MainWindow;
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

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            MainWindow mainWindow = fxmlLoader.<MainWindow>getController();

            stage.setScene(scene);
            stage.setTitle("Duke");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/Icon.jfif")));

            mainWindow.setDuke(duke);
            mainWindow.displayDukeResponse(duke.getUi().say(SayType.INTRO));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}