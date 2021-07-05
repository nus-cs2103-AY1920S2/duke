/**
 * The Duke class.
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        String s = c.execute(tasks, ui, storage);
        return s;
    }
}