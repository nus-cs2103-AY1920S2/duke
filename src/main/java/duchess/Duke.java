package duchess;

import duchess.command.Command;
import duchess.exception.DuchessException;
import duchess.io.Parser;
import duchess.storage.Storage;
import duchess.task.TaskList;
import duchess.ui.Ui;

/**
 * The {@code Duke} class is the entry point of the Duchess program.
 * Upon initialising an instance of this class, calling run() on it
 * will begin the program.
 *
 * <p>To initialise the {@code Duke} instance, a {@code String filePath}
 * ending with {@code .json} needs to be passed into the constructor.
 * If no file is found at the provided file path, a brand new JSON file will
 * be created.
 *
 * @author Zhu Hanming
 */
public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Initialises a newly created {@code Duchess} object that uses the
     * provided {@code filePath} as the location of the JSON save file.
     *
     * @param filePath A {@code String} denoting the location of the JSON
     *                 save file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DuchessException e) {
            this.ui.printLoadingError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Begins the Duchess program. Upon calling the run() method, the user
     * can begin to interact with the program.
     */
    public void run() {
        this.ui.printWelcome();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command command = Parser.parse(fullCommand);
                command.execute.apply(fullCommand, this.taskList, this.ui, this.storage);
                if (command == Command.BYE) {
                    isRunning = false;
                }
            } catch (DuchessException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Starts up the Duchess program.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        Duke duchess = new Duke("data/tasks.json");
        duchess.run();
    }
}
