import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.nio.file.Paths;

/**
 * Represents a chatbot, which takes in commands related to tasks and tracks a list of tasks.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String expectedStoragePath = Paths.get("data", "duke.txt").toString();

    /**
     * Constructor for Duke that takes in the path to the file that
     * contains the saved task list from previous sessions.
     *
     * @param filePath path to the storage file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructor for Duke that uses a default expected file path for
     * storage file.
     */
    public Duke() {
        this(expectedStoragePath);
    }

    /**
     * Starts the chatbot and continues executing commands until the
     * exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.getCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        new Duke(expectedStoragePath).run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

}
