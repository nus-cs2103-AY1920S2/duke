import dukeexception.DukeException;
import java.nio.file.Path;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke class uses Storage to load and unload data from and to files, TaskList to contain and operate on
 * the lit of tasks, Ui to interact with the user, and Parser to decipher the user command.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor of Duke.
     * Attempts to load tasks from file, if fail start with empty list of tasks.
     *
     * @param filePath The path to the file to load tasks from.
     */
    public Duke(Path filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * A continuous loop of getting the user command, parsing to make sense of it and executing the command.
     * Stops when the user command is "bye.
     * @throws DukeException for any exceptions that occur during this run.
     */
    public void run() {
        ui.showHello();
        while (true) {
            String command = ui.readCommand();
            if (command.equals("bye")) {
                ui.showBye();
                break;
            }
            try {
                parser.executeCommand(tasks, command, ui);
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                storage.updateFile(tasks, tasks.getLength());
            }
        }
    }

    /**
     * Main method to set the path for file and run Duke.
     *
     * @param args Command line arguments that aren't needed.
     */
    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        Path path = java.nio.file.Paths.get(home, "IdeaProjects", "duke", "data", "tasks.txt");
        new Duke(path).run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}