import java.util.ArrayList;

/**
 * The main Duke class which runs the duke programme.
 */
public class Duke {

    private ArrayList<Task> list;
    private int latestIndex;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    /**
     * Transfers user input from the main window into the parser to recieve a response from the bot.
     *
     * @param input the input from the user.
     * @return a response from the bot.
     */

    public String getResponse(String input) {
        if (input.equals("start")) {

            return ui.welcomeMessage();
        }
        return parser.parse(input);

    }

    /**
     * Run function which runs the Duke application.
     * @throws Exception should any issue occur with any function.
     */
    public void run() throws Exception {

        this.storage = new Storage();
        this.ui = new Ui();

        this.list = storage.readFile();
        this.latestIndex = storage.returnInitialIndex();

        TaskList tasklist = new TaskList(list, latestIndex, storage);
        this.parser = new Parser(tasklist);
    }
}