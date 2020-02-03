package duke;

/**
 * The Main Class initialises all important objects for the program to run.
 * @author qiujingying
 * @version 1.0
 */
public class Main {

    private Storage storage;
    private TaskList tasks;
    private Eevee ui;

    /**
     * Creates a Main object from a filepath.
     * @param filepath filepath
     */
    public Main(String filepath) {
        ui = new Eevee();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.echo(tasks, storage);
    }

    public static void main(String[] args) {
        new Main("../../../data/duke.txt").run();
    }
}
