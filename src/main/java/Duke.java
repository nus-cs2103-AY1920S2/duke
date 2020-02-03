/**
 * This is a simulation of a chat bot called Duke.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a new Duke with the given data storage path.
     *
     * @param path The file path of Duke data file.
     */
    public Duke(String path) {
        this.ui = new Ui();
        this.storage = new Storage(path);
        this.tasks = new TaskList(storage.load());
    }

    public String getResponse(String input) {
        return Parser.parse(input).run(ui, storage,tasks);
    }

    public String getLogo() {
        return ui.showLogo();
    }

    public String getGreet() {
        return ui.showGreet();
    }

}
