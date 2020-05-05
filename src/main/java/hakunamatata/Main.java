package hakunamatata;

//@Override

import java.io.IOException;

import hakunamatata.controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for HakunaMatata using FXML.
 */
public class Main extends Application {

    private HakunaMatata hakunaMatata = new HakunaMatata("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add("/css/myStyle.css");
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setHakunaMatata(hakunaMatata);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
