package views;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import app.Duke;

public final class Launcher extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("/views/MainWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            fxmlLoader.<MainWindow>getController().setDuke(new Duke());
            
            stage.setScene(scene);
            stage.setTitle("Duke");
            stage.show();
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}