package duke;

import java.nio.file.Paths;
import duke.ui.component.App;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A personal note-taking assistant.
 */
public class Duke extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Duke");

        PersistentStorage persistentStorage = new PersistentStorage(
                Paths.get(System.getProperty("user.dir"), "data.txt")
        );
        App app = new App(persistentStorage);

        Scene scene = new Scene(app);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
