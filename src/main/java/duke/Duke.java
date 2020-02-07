package duke;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.command.Command;


/**
 * Represents the main logic flow of the Duke Application.
 * A <code>Storage</code> object stores all list data into disk.
 * A <code>TaskList</code> object stores list of user tasks in RAM.
 * A <code>Ui</code> object handles input and output interaction with user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public Duke() {
    }

    /**
     * A loop that continuously seeks commands from user and executes them.
     * If bye command is received, the loop terminates.
     */
    public String run(String instruction) {
        String result = "";
        try {
                Command c = Parser.parse(instruction);
                result = c.execute(tasks, ui);
        } catch (DukeException e) {
                result = ui.showError(e);
        }
        storage.save(tasks.getList());
        return result;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}