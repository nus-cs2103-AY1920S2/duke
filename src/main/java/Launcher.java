import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The application launcher class.
 */
public class Launcher extends Application {

    private TaskList taskList = new TaskList();
    private Duke duke = new Duke(taskList);

    /**
     * Sets up the stage upon start of application.
     *
     * @param stage the stage of the application.
     */

    @Override
    public void start(Stage stage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<Ui>getController().setDuke(duke);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
