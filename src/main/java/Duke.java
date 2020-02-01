/**
 * The Duke class
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke object.
     *
     * @param filePath A string representation of the file directory.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the program by calling other classes.
     *
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
    }

    /**
     * Main program that implements an application of the classes that will manage all the operations.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}