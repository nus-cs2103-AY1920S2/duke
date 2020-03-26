package duke;

import java.nio.file.Paths;
import duke.component.AppComponent;
import duke.model.TaskModel;
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

        TaskModel taskModel = new TaskModel(Paths.get(System.getProperty("user.dir"), "data.txt"));
        AppComponent app = new AppComponent(taskModel);

        Scene scene = new Scene(app);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
