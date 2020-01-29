package duke;

/**
 * Main class for the entire program, Duke is the chatbot and this is the driver class.
 */
public class Duke {

    /**
     * TaskList to store all the Tasks for Duke.
     */
    private TaskList allTasks;

    /**
     * Storage file for reading when initialising or writing to when there are changes to the TaskList.
     */
    private Storage file;

    /**
     * Handles the User Interactions/Formalities.
     */
    private Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.allTasks = new TaskList();
        this.ui = new Ui();
        this.file = new Storage();
    }

    /**
     * Main driver method.
     *
     * @param args standard argument.
     */
    public static void main(String[] args) {

        // Welcomes the user
        Ui.welcome();

        // Initialise duke.Duke program
        Duke duke = new Duke();
        duke.initialise();

        // Run main program
        duke.run();

        // User is done
        Ui.goodbye();

    }

    /**
     * Runs TaskList to serve the user.
     */
    private void run() {
        // Begins accepting input commands
        allTasks.serveUser();
    }

    /**
     * Constructs new duke.Duke, and uses past user data if any.
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
     * Prints a given String.
     *
     * @param s String to be printed.
     */
    private void print(String s) {
        System.out.println(s);
    }

}
