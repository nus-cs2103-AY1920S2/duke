import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    static private Ui ui;
    static private Storage storage;
    static private TaskList taskList;
    static private Parser parser;
    static String dataFilePath = Paths.get(Paths.get(System.getProperty("user.dir")).toString(),
            "data/task_data.txt").toString();

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        ui = new Ui();
        storage = new Storage(dataFilePath);
        taskList = new TaskList(ui, storage);
        try {
            taskList.setList(storage.getList());
        } catch (Exception e) {
            taskList.setList(new ArrayList<>());
        }
        parser = new Parser(taskList);
        duke.linkResources(ui, parser);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Duck - a smart animal assistant");
            stage.getIcons().add(new Image("/images/duck_logo.png"));
            stage.show();
            fxmlLoader.<MainWindow>getController().showWelcomeMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
