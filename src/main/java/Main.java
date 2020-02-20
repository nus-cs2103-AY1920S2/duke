//@Override

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author huixianc-reused
// reused from https://github.com/huixianc/duke/blob/master/tutorials/ JavaFX tutorials

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String path = "data/duke.txt";
    private Duke duke = new Duke(path);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("MBot Jr");

            Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
            stage.getIcons().add(dukeImage);

            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//@@author