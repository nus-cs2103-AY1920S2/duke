/**
 * Main class for Duke.
 * Class contains constructor for Duke object.
 */
public class Duke {

    /**
     * Duke object contains parameters: Storage to update list of Tasks and save file in hard
     * drive, TaskList to update list of tasks in duke and Ui for user to interact with.
     */
    protected static Storage storage;
    protected static TaskList tasks;
    private Ui ui = new Ui();

    /**
     * Constructs a Duke object.
     * @param filePath file path to save file in hard drive
     */
    public Duke(String filePath) {
        assert !filePath.isEmpty(): "filepath should not be empty String.";

        // Prints welcome message
        this.ui.showWelcome();
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
     * Retrieves response from Duke based on user input.
     */
    protected String getResponse(String input) {
        return ui.getResponse(input);
    }

    public void run() {
        ui.start();
    }

    public static void main(String[] args) {
        // Creates new Duke object and initiates Duke.
        (new Duke("data/duke.txt")).run();
    }

    /**
     * Duke constructor for Launcher.
     */
    public Duke() {
        this("data/duke.txt");
    }

    enum Tasks {
        todo,
        deadline,
        event
    }
}
