package seedu.duke;

//@Override
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seedu.duke.ui.Ui;

//@@author johannagwan-reused
//Reused from https://github.com/johannagwan/duke/blob/master/tutorials/javaFxTutorialPart4.md with minor modifications.
/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke;

    {
        duke = new Duke();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Dodo");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//@@author