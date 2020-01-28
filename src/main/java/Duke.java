import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.exception.DukeException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
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

    /** Call this to begin using the chat-bot. */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        TaskList taskList = tasks;

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

    /** The main entry point of the program. */
    public static void main(String[] args) {
        // Save file named "tasks.txt"
        // Located in "data" folder, found in the root of this working directory.
        // Platform independent file directory
        Path path = Paths.get(System.getProperty("user.dir"), "data", "tasks.txt");
        new Duke(path.toString()).run();
    }
}