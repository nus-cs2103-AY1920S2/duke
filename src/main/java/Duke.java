/** Driver class of Duke chatbot, also saves on startup and loads on exit. */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage("savedata.txt");
        tasks = new TaskList(storage.load());
    }

    /** Driver method for Duke. */
    public String run(String input) {
        String result = "";

        // For making PR
        assert (result.equals("")) : "There's no result.";

        result = Parser.handleTasks(input, tasks, ui);
        storage.save(tasks);

        return result;
    }

}
