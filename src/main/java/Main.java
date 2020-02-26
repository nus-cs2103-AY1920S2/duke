//@Override

import java.io.IOException;

import core.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Core.Duke using FXML.
 */
public class Main extends Application {

    private static Stage stage;

    private static final String filepath = System.getProperty("user.dir")
            + System.getProperty("file.separator") + "duke.txt";

    private Duke duke = new Duke(filepath);

    @Override
    public void start(Stage stage) {
        try {
            this.stage = stage;
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/Vader.png")));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Mission Luke");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}