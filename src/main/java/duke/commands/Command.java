/**
 * Object type of Command is determined by user input
 */
package duke.commands;

import duke.tasks.*;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public class Command {

    protected String command;
    protected boolean isExit;

    public Command (String command) {
        this.command = command;
        if (command.equals("bye")) {
            isExit = true;
        }
    }

    /**
     * Rejects input by user as it is not on recognized list of input
     * @param tasks List of current tasks
     * @param ui User interface used to reply user
     * @param storage For storing of tasks into file
     * @throws DukeException If input format is wrong
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showLine();
        if (!isExit()) {
            ui.print("Your entry is not recognized. Please try again.");
        }
    }

    /**
     * Changes instance variable to true if input command equals "bye"
     * @return isExit if input command equals "bye"
     */
    public boolean isExit() {
        return isExit;
    }
}
