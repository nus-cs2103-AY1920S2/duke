package duke;

import duke.command.Command;

import duke.exception.DukeException;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.stage.Stage;

public class Duke extends Application {
    /** The save/loading mechanism. */
    private Storage storage;
    /** The list of tasks. */
    private TaskList tasks;
    /** The user interface for console displays. */
    private Ui ui;

    /**
     * Constructs a new chat-bot Duke.
     *
     * @param filePath the relative path to the save file of the chat-bot.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load()); // Load from file if possible
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(); // Start a brand new task list if file cannot be found/opened.
        }
    }

    /**
     * Constructs a new chat-bot Duke with the default save file location.
     */
    public Duke() {
        this(getDefaultPath());
    }

    /** Call this to begin using the chat-bot. */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        TaskList taskList = tasks;

        // Keep receiving user input until the exit command is entered
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                taskList = c.execute(taskList, ui, storage); // Update the task list
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /** The main entry point of the program. */
    public static void main(String[] args) {
        // Save file named "tasks.txt"
        // Located in "data" folder, found in the root of this working directory.
        // Platform independent file directory
        new Duke(getDefaultPath()).run();
    }

    /**
     * Returns the os-dependent directory to the default save file location.
     * This default save file location is {folder_enclosing_duke_program}->data->tasks.txt.
     *
     * @return the os-dependent directory to the default save file location.
     */
    private static String getDefaultPath() {
        return Paths.get(System.getProperty("user.dir"), "data", "tasks.txt").toString();
    }
}