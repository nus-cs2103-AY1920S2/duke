package duke;

// packages imports
import duke.commands.Command;
import duke.controllers.MainWindow;
import duke.tasks.TaskList;
import duke.ui.Ui;

// java imports
import java.io.IOException;
import java.io.FileNotFoundException;


/**
 * Main class of the chat bot program.
 */
public class Duke {
    /**
     * User interface class with formatted outputs.
     */
    private Ui ui;
    /**
     * Allows for persistent data.
     */
    private Storage storage;
    /**
     * List to store all tasks.
     */
    private TaskList taskList;

    /**
     * Parser to parse commands and interact with tasklist.
     */
    private Parser parser;

    /**
     * Creates a bot with personalize user interface, storage, and task list.
     * Will create a new save file is there is no existing one.
     */
    public Duke(MainWindow mainWindowController) {
        ui = new Ui(mainWindowController);
        storage = new Storage();
        taskList = new TaskList();
        parser = new Parser(taskList);

        try {
            storage.readSaveFile(taskList);
        } catch (FileNotFoundException ex) {
            Ui.printOutput("No saved task list found. Creating a new one...");
        }
    }

    /**
     * Responds to GUI input by the user by parsing the input
     * and executing the respective command.
     *
     * @param input Input of user.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            String response = command.execute();
            storage.save(taskList);
            return response;
        } catch (IOException ex) {
            return ex.toString();
        }
    }
}