package com.duke;

import com.duke.ui.MainWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.application.Application;

import java.io.File;
import java.io.IOException;

/**
 * A GUI for com.duke.Duke using FXML.
 */
public class Main extends Application {



    private Duke duke = new Duke("." + File.separator + "data" + File.separator + "duke.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().greetUser();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}