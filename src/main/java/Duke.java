import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import javafx.scene.image.Image;
/**
 * A GUI for Duke using FXML.
 */
public class Duke extends Application {

    @Override
    public void start(Stage stage) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/MainPage.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke, Chatbot to track your task progress");
            stage.getIcons().add(new Image("/images/chatbot_icon.png"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}