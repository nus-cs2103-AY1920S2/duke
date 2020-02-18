import duke.command.*;
import duke.exception.CommandNotFoundException;
import duke.exception.DukeException;
import duke.exception.InvalidDukeArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.util.*;

import java.io.FileNotFoundException;
import java.util.StringTokenizer;


/**
 * Represents a Duke chat bot, which supports basic todo list features such as adding and deleting tasks,
 * marking a task as done or list out all the tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    public Duke() {

    }

    /**
     * Constructs a Duke chat bot instance.
     * @param filePath the file path of the data file in which the existing tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasklist = storage.loadTasks();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasklist = new TaskList();
        }
    }

    /**
     * Starts the chat bot, exits when encountering the "bye" command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser parser = new Parser();
                Command c = parser.parse(fullCommand, tasklist);
                String response = c.execute(tasklist, ui, storage);
                ui.showMessage(response);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Duke duke = new Duke("./data/duke.txt");
        TaskList tasklist = duke.tasklist;
        Storage storage = duke.storage;
        Parser parser = new Parser();
        try {
            Command c = parser.parse(input, tasklist);
            return c.execute(tasklist, ui, storage);
        } catch (InvalidDukeArgumentException | CommandNotFoundException e) {
            return e.getMessage();
        }
    }
}