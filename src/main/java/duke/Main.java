package duke;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * A GUI for duke.Duke using FXML.
 */
public class Main extends Application {
    private Duke duke;

    @Override
    public void start(Stage stage) {
        try {
            TaskList tasks = new TaskList();
            Storage.readFile(tasks);
            duke = new Duke(tasks, stage);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("YATL - Yet Another Todo List");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}