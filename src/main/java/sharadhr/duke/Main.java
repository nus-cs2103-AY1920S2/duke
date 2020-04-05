package sharadhr.duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sharadhr.duke.ui.MainWindow;

import java.io.IOException;

/**
 * Main
 */
public class Main extends Application {
    private Duke duke = new Duke("data", "tasks.csv");

    @Override public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}