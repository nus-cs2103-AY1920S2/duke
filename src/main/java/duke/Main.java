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
        // Solution below adapted from JavaFx Tutorial by Jeffry Lum.
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Gui.fxml"));
        AnchorPane ap;
        try {
            ap = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Error in loading fxml!");
            return;
        }
        Scene scene = new Scene(ap);

        Gui gui = fxmlLoader.<Gui>getController();
        gui.setDuke(duke);

        stage.setTitle("Duke");
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        // Handle case where user manually exits
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                event.consume();
                gui.bye();
            }
        });
        stage.show();

        new Thread(() -> {
            duke.run();
        }).start();
    }
}