package duke;

import java.io.IOException;

import duke.controllers.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke;
    public static MainWindow mainWindowController;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            AnchorPane ap = fxmlLoader.load(); // root node
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/assets/stylesheet.css").toExternalForm());
            stage.setTitle("Duke");
            stage.setScene(scene);

            duke = new Duke(fxmlLoader.<MainWindow>getController());
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}