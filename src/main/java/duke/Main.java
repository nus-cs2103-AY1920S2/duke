package duke;

import duke.javafx.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            ap.setId("pane");
            Scene scene = new Scene(ap);
            scene.getStylesheets().addAll(Main.class.getResource("/css/style.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}