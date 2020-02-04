package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

/**
 * Initializes the setting and prepare respond to user input.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Setup the list of task based on the local file.
     *
     * @param filePath The filepath of the lacal file to store the tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public Duke() {

    }

    /**
     * Return the response based on user input.
     *
     * @param input user input from the GUI
     * @return return the response from the system
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c instanceof ExitCommand) {
                Platform.exit(); // statement to exit the JavaFX application
            }
            return c.execute(tasks, ui, storage);
        } catch (DukeException ex) {
            return ui.showError(ex);
        }
    }
}
