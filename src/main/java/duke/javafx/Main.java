package duke.javafx;

import duke.exception.DukeException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private DukeJavaFxRunner dukeJavaFxRunner = new DukeJavaFxRunner();

    @Override
    public void start(Stage stage) throws DukeException {
        try {
            stage.setTitle("Trump vs Putin");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            ap.setId(".bodybg");
            Scene scene = new Scene(ap);
            scene.getStylesheets().addAll(this.getClass().getResource("/images/style.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDukeJavaFxRunner(dukeJavaFxRunner);
            stage.show();
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
