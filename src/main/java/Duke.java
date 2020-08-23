import dukeexception.DukeException;
import java.nio.file.Path;

/**
 * Duke class uses Storage to load and unload data from and to files, TaskList to contain and operate on
 * the lit of tasks, Ui to interact with the user, and Parser to decipher the user command.
 */
public class Duke {

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
            tasks = new TaskList(storage.loadFromFile());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Empty constructor for JavaFx GUI to run.
     */
    public Duke() {
        this(java.nio.file.Paths.get("", "data", "tasks.txt"));
    }

    /**
     * Executes the user command.
     *
     * @param command The user command.
     * @return The output message to the user.
     */
    public String getResponse(String command) {
        String outputMsg = "";
        if (command.equals("bye")) {
            return ui.showBye();
        }
        try {
            outputMsg = parser.executeCommand(tasks, command, ui);
            storage.updateFile(tasks, tasks.getLength());
        } catch (DukeException e) {
            outputMsg = ui.showError(e) + "\nType help if you need Oppa's help!";
        } finally {
            return outputMsg;
        }
    }
}