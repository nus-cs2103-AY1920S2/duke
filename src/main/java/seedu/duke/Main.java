package seedu.duke;

//@Override
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author johannagwan-reused
//Reused from https://github.com/johannagwan/duke/blob/master/tutorials/javaFxTutorialPart4.md with minor modifications.
/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke;

    {
        try {
            duke = new Duke();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//@@author