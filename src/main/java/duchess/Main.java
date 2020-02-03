package duchess;

import duchess.controller.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Duke duke = new Duke("data/tasks.json");

    @Override
    public void start(Stage stage) {
        AnchorPane ap = new MainWindow(duke);
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.show();
    }
}
