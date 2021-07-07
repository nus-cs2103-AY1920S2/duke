package duke.main;

import java.io.IOException;

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

    private final String FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/task-list.txt";
    private Duke duke = new Duke(FILE_PATH);
    private static Stage stg;

    @Override
    public void start(Stage stage) {
        try {
            stg = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Brian-bot");
            stage.getIcons().add(new Image("/images/Bill Gates.jpg"));
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeWindow() {
        stg.close();
    }
}
