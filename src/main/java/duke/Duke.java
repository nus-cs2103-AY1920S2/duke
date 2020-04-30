package duke;

/**
 * The Main Class initialises all important objects for the program to run.
 * @author qiujingying
 * @version 1.0
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Eevee ui;

    /**
     * Creates a Main object from a filepath.
     * @param filepath filepath
     */
    public Duke(String filepath) {
        ui = new Eevee();
        try {
            storage = new Storage(filepath);
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Empty constructor for launcher to work.
     */
    public Duke() {

    }

    public String run(String command) {
        return ui.startConversation(tasks, storage, command);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        Duke eevee = new Duke("duke.txt");
        return eevee.run(input.toLowerCase());
    }
}
