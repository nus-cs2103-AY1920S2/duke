package duke;

import duke.controller.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The {@code Main} class helps to start up the GUI for Duchess.
 */
public class Main extends Application {

    private Duke duke = new Duke("data/tasks.json", true);

    @Override
    public void start(Stage stage) {
        VBox ap = new MainWindow(duke);
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.setMinHeight(600);
        stage.setHeight(600);
        stage.setMinWidth(400);
        stage.show();
    }
}
