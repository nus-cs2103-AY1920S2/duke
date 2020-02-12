package dukebot;

import dukebot.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke(true);

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Waifu Simulator (Duke)");
            stage.getIcons().add(new Image("/images/duke_happy.png"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().printCurrentMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
