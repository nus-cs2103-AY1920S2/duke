package duke.gui;

import duke.Duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Gui extends Application {

    private Duke duke;
    private String saveFolder;

    @Override
    public void init() throws Exception {
        super.init();

        saveFolder = "data";
        duke = new Duke(saveFolder);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Personal Assistant Lite");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Gui.class.getResource("/view/MainWindow.fxml"));
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