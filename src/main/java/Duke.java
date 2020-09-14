import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a Duke robot that deal with multiple tasks.
 */
public class Duke extends Application{
    /**
     * The Storage used.
     */
    private Storage storage;
    /**
     * The list of task.
     */
    private TaskList tasks;
    /**
     * The user interface.
     */
    private Ui ui;
    /**
     * The Parser used.
     */
    private Parser parser;

    /**
     * Creates a new Duke with the given filePath.
     */
    public Duke (String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs Duke.
     */
    public void run() {
        this.parser = new Parser(ui, tasks, storage);
        parser.run();
    }

    /**
     * Runs Duke with the given filePath.
     */
    public static void main(String[] args) {
        new Duke("C:\\Users\\h1430\\Documents\\CS2103T\\duke\\src\\main\\java\\data\\duke.txt").run();
    }

    /**
     * A GUI for Duke.
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader l = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = (AnchorPane) l.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
