import java.io.IOException;

/**
 * Driver class of Duke chatbot, also saves on startup and loads on exit.
 */
public class Duke {

    /**
     * Storage for loading task list, and to save right before exiting Duke.
     */
    private Storage storage;

    /**
     * TaskList to keep track of existing tasks.
     */
    private TaskList tasks;

    /**
     * Ui to provide user interface printing.
     */
    private Ui ui;

    /**
     * Constructor of Duke.
     *
     * @param filePath String representing file path of save file of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Driver method for Duke.
     */
    public void run() {
        ui.printLogo();
        ui.greetDuke();
        Parser.handleTasks(tasks, ui);

        storage.save(tasks);
    }

    /**
     * Main method of Duke.
     *
     * @param args String array from System input.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("savedata.txt");
        duke.run();
    }
}
