/**
 * Class representation of Duke
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke Constructor
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     *  Duke's run method
     *  Calls its Ui attribute, and activates the onStart function
     */
    public void run() {
        ui.onStart(tasks, storage.filepath);
    }

    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }
}
