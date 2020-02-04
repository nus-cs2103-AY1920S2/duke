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
    /** True if the chat-bot has ended, otherwise false. */
    private boolean isExit;
    /** True if the chat-bot loaded from a save file, otherwise false. */
    private boolean isLoaded;

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
            isLoaded = true;
        } catch (DukeException e) {
            tasks = new TaskList(); // Start a brand new task list if file cannot be found/opened.
            isLoaded = false;
        }
        isExit = false;
    }

    /**
     * Constructs a new chat-bot Duke with the default save file location.
     */
    public Duke() {
        this(getDefaultPath());
    }

    /**
     * Returns the response from Duke after parsing and executing the user input.
     * If an error is thrown, Duke will respond with its own custom error message.
     *
     * @param input the user input.
     * @return the response from Duke after parsing and executing the user input.
     */
    public String getResponse(String input) {
        // TODO: Fix immutability of Ui
        try {
            ui.clear();
            Command c = Parser.parse(input);
            tasks = c.execute(tasks, ui, storage); // Update the task list
            isExit = c.isExit();
            return ui.getResponse();
        } catch (DukeException e) {
            return ui.getError(e);
        }
    }

    /**
     * Returns whether the chat-bot has ended or not.
     *
     * @return true if the chat-bot has ended, otherwise false.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Returns whether the chat-bot was loaded from a save file.
     *
     * @return true if the chat-bot was loaded from a save file, otherwise false.
     */
    public boolean isLoaded() {
        return isLoaded;
    }

    /**
     * Returns the welcome message.
     *
     * @return the welcome message.
     */
    public String getWelcome() {
        return ui.getWelcome();
    }

    /**
     * Returns the loading error message.
     *
     * @return the loading error message.
     */
    public String getLoadingError() {
        return ui.getLoadingError();
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