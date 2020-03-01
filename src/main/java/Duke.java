/** Driver class of Duke chatbot, also saves on startup and loads on exit. */
public class Duke {

    /**
     * Storage for loading task list, and to save right before exiting Duke.
     */
    private Storage storage;

    /**
     * TaskList to keep track of existing tasks.
     */
    private TaskList tasks;

    /**
     * Ui to provide user interface printing.
     */
    private Ui ui;

    /**
     * Constructor of Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("savedata.txt");
        tasks = new TaskList(storage.load());
    }

    /**
     * Driver method for Duke.
     */
    public String run(String input) {
        String result = "";

        // For making PR
        assert result.equals("") : "There's no result.";

        result = Parser.handleTasks(input, tasks, ui);
        storage.save(tasks);
        return result;
    }


}
