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
    private String loadingErrorMessage;

    /**
     * Initialises a newly created {@code Duke} object that uses the
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
     * Initialises a newly created {@code Duke} object that uses the
     * provided {@code filePath} as the location of the JSON save file.
     * This is an overloaded constructor for GUI that stores any error
     * messages encountered when starting up so as to print subsequently.
     *
     * @param filePath A {@code String} denoting the location of the JSON
     *                 save file.
     * @param isGui    A {@code boolean} denoting whether the program is in
     *                 Gui mode.
     */
    public Duke(String filePath, boolean isGui) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DuchessException e) {
            if (isGui) {
                this.loadingErrorMessage = this.ui.printLoadingError(e.getMessage());
            } else {
                this.ui.printLoadingError(e.getMessage());
            }
            this.taskList = new TaskList();
        }
    }

    /**
     * Begins the console version of the Duchess program. Upon calling
     * the run() method, the user can begin to interact with the program.
     */
    public void run() {
        this.ui.printToConsole(this.ui.printConsoleWelcome());
        ui.printLine();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = this.ui.readCommand();
                this.ui.printLine();
                Command command = Parser.parse(fullCommand);
                String response = command.execute.apply(fullCommand, this.taskList, this.ui, this.storage);
                this.ui.printToConsole(response);
                if (command == Command.BYE) {
                    isRunning = false;
                }
            } catch (DuchessException e) {
                this.ui.printToConsole(this.ui.printError(e.getMessage()));
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Returns a Duke response to the given input.
     *
     * @param input User input.
     * @return Duke response.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute.apply(input, this.taskList, this.ui, this.storage);
        } catch (DuchessException e) {
            return ui.printError(e.getMessage());
        }
    }

    /**
     * Returns the welcome message for GUI mode.
     *
     * @return Welcome message {@code String} for GUI mode.
     */
    public String getWelcomeMessage() {
        if (this.loadingErrorMessage != null && !this.loadingErrorMessage.isEmpty()) {
            return this.ui.printWelcome() + this.loadingErrorMessage;
        } else {
            return this.ui.printWelcome();
        }
    }
}
