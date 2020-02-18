package duke;

import duke.controller.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The {@code Main} class helps to start up the GUI for Duchess.
 */
public class Main extends Application {

    private Duke duke = new Duke("data/tasks.json", true);

    @Override
    public void start(Stage stage) {
        MainWindow ap = new MainWindow(duke);
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.setMinHeight(600);
        stage.setHeight(600);
        stage.setMinWidth(400);
        stage.setTitle("Duchess");
        scene.getStylesheets().add("/styles/duchess.css");
        ap.themeToggle.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                scene.getStylesheets().add("/styles/dark-duchess.css");
            } else {
                scene.getStylesheets().remove("/styles/dark-duchess.css");
            }
        });
        stage.show();
    }
}
