import storage.Storage;
import commands.Command;
import exceptions.DukeException;
import exceptions.InvalidCommandException;
import parser.Parser;
import tasks.TaskList;
import ui.Ui;

/**
 * Main class for Duke.
 * Class contains constructor for Duke object.
 */
public class Duke {

    /**
     * Duke object contains parameters: Storage to update list of Tasks and save file in hard
     * drive, TaskList to update list of tasks in duke and Ui for user to interact with.
     */
    private static final String FILE_PATH = "data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object.
     * @param filePath file path to save file in hard drive
     */
    private Duke(String filePath) {
        assert !filePath.isEmpty() : "filepath should not be empty String.";

        this.ui = new Ui();
        storage = new Storage(filePath);

        // Constructs new storage object
        storage = new Storage(filePath);
        // Load list of tasks from save file
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Retrieves Duke's greeting to be displayed upon startup.
     * @return Duke's welcome message.
     */
    protected String getGreeting() {
        return ui.showWelcome();
    }

    /**
     * Retrieves response from Duke based on user input.
     */
    protected String getResponse(String input) {
        boolean isExit;
        try {
            Command c = Parser.parse(input);
            isExit = c.isExit();
            if (isExit) {
                Runtime.getRuntime().exit(0);
                return c.execute(tasks, ui, storage);
            }
            return c.execute(tasks, ui, storage);
        } catch (DukeException | InvalidCommandException e) {
            return ui.formatResponse(e.getMessage());
        }
    }

    /**
     * Duke constructor for Launcher.
     */
    public Duke() {
        this(FILE_PATH);
    }
}
