import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.Command;
import duke.Parser;

/**
 * Main class for duke.
 */
public class Duke {

    protected Ui ui;
    protected Storage storage;
    protected TaskList tasks;

    /**
     * Initialize duke.
     * @param filepath filepath of storage file
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());

    }

    /**
     * Run duke.
     */
    public void run() {
        ui.printLogo();
        ui.showWelcome();

        while (true) {
            String fullCommand = ui.readCommand();
            ui.printLine();
            Command c = Parser.parse(fullCommand);
            c.execute(ui, storage, tasks);
        }
    }

    /**
     * Main Method of duke.
     * @param args takes in command prompt arguments
     */
    public static void main(String[] args) {
        new Duke("dukeStorage").run();
    }
}
