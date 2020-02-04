package duke.main;

import duke.exceptions.DukeException;
import duke.exceptions.UnknownCommandException;

/**
 * Main application class for a To Do List Application
 */
public class Duke {
    /**
     * Storage object used to store TaskList object into a Storage mechanism (e.g. File)
     */
    private Storage storage;

    /**
     * TaskList object used to store Tasks
     */
    private TaskList taskList;

    /**
     * Constructor for Duke class
     * @param filePath Path to file used for persistent storage
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            Ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Function to run Duke object
     */
    public void run() {
        Ui.start();
        boolean run = true;
        while (run) {
            try {
                run = Parser.parseCommand(Ui.getInput(), taskList);
            } catch (DukeException ex) {
                Ui.printException(ex);
            }
        }
    }

    /**
     * Default function to be invoked
     * @param args Optional String command line arguments
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
