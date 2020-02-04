package duke;

import duke.command.Command;

import duke.exception.DukeException;

import java.nio.file.Paths;

public class Duke {
    // Duke components

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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        // TODO: modify this method to behave like run()
        return "Duke heard: " + input;
    }

    /** The main entry point of the program. */
    public static void main(String[] args) {
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