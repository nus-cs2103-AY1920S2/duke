import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class Duke {

    private TaskList allTasks;
    private Storage file;
    private Ui ui;

    public Duke() {
        this.allTasks = new TaskList();
        this.ui = new Ui();
        this.file = new Storage();
    }

    /**
     * Main driver method
     *
     * @param args standard argument
     */
    public static void main(String[] args) {

        // Welcomes the user
        Ui.welcome();

        // Initialise Duke program
        Duke duke = new Duke();
        duke.initialise();

        // Run main program
        duke.run();

        // User is done
        Ui.goodbye();

    }

    /**
     * Runs TaskList to serve the user
     */
    private void run() {
        // Begins accepting input commands
        allTasks.serveUser();
    }

    /**
     * Constructs new Duke, and uses past user data if any
     */
    private void initialise() {
        // Loads previous Tasks if any
        load();
    }

    /**
     * Loads previously saved files if any.
     * Creates new file otherwise.
     */
    private void load() {
        // Take information from file to update allTasks to last saved preferences
        file.readFromLastSavedFile(allTasks);
    }

    /**
     * Prints a given String
     *
     * @param s String to be printed
     */
    private void print(String s) {
        System.out.println(s);
    }

}
