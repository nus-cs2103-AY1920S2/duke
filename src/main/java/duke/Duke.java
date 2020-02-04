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
        storage = new Storage(filepath);
        try {
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

    public void run() {
        ui.echo(tasks, storage);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
