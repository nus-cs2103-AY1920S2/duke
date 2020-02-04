import java.io.FileNotFoundException;

/**
 * Represents a command that adds item to list. A <code>AddCommand</code> object
 * corresponds to a command represented by the command and a description e.g.,
 * <code>"deadline", "read /by 2019-05-10 1800"</code>
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {

    }

    /**
     * Constructor for Duke object.
     * @param filePath path of file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the bot.
     */
    public String run(String input) {
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            String output = c.execute(tasks, ui, storage);

            return output;
        } catch (DukeException e) {
            return e.toString();
        }
    }

    /**
     * Returns ui.
     * @return ui in bot object
     */
    public Ui getUi() {
        return this.ui;
    }
}