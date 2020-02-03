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
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException ioe) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Driver method for Duke.
     */
    public void run() {
        ui.printLogo();
        ui.greetDuke();
        Parser.handle(tasks, ui);
    }

    /**
     * Main method of Duke.
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke("savedata.txt");
        duke.run();
    }
}
