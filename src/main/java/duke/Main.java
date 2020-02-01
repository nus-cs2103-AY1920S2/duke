package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import duke.ui.Gui;

/**
 * Main class called by Launcher to initialise Duke and Gui.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Gui.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            Gui gui = fxmlLoader.<Gui>getController();
            gui.setDuke(duke);
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent event) {
                    event.consume();
                    gui.bye();
                }
            });
            new Thread(() -> {
                duke.run();
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}