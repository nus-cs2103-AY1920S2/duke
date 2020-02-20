import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author {BryanYap972}-reused
//https://github.com/nus-cs2103-AY1920S2/duke/tree/master/tutorials - javaFX tutorials

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    Path path = Paths.get("Data/duke.txt");
    private Duke duke = new Duke(path.toString());

    /**
     * Launches and runs the bot.
     * @param stage Stage for GUI
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("BOTBOT");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();

            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().showWelcome();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//@@author