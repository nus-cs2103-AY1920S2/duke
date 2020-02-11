import Duke.*;

/**
 * Main class for Duke
 */
public class Duke {

    protected Ui ui;
    protected Storage storage;
    protected TaskList tasks;

    /**
     * Initialize Duke
     * @param filepath filepath of storage file
     */
    public Duke(String filepath){
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());

    }

    /**
     * Run Duke
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
     * Main Method of Duke
     * @param args takes in command prompt arguments
     */
    public static void main(String[] args) {
        new Duke("dukeStorage").run();
    }
}
