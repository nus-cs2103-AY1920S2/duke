package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String MAIN_WINDOW_FXML_PATH = "/view/MainWindow.fxml";
    private static final String SCROLL_PANE_CSS_PATH = "view/ScrollPane.css";
    private static final String ICON_PATH = "/images/icon.png";
    private static final String APPLICATION_NAME = "Megumin";

    /**
     * Initializes and starts the application.
     *
     * @param stage Stage object for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_FXML_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(SCROLL_PANE_CSS_PATH);
            stage.getIcons().add(new Image(ICON_PATH));
            stage.setTitle(APPLICATION_NAME);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
