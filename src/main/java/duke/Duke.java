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

        // Assignment of objects
        this.allTasks = new TaskList();
        this.ui = new Ui();
        this.file = new Storage();

        // Initialise Duke with any last saved user data
        this.initialisePastUserData();

    }

    /**
     * Runs TaskList to serve the user.
     */
    public String run(String command) {
        return allTasks.serveUser(command);
    }

    /**
     * Loads previously saved files if any.
     * Creates new file otherwise.
     */
    public void initialisePastUserData() {
        // Take information from file to update allTasks to last saved preferences
        file.readFromLastSavedFile(allTasks);
    }

}
