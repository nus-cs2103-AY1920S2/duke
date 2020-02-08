package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to be executed.
 */
public abstract class Command {

    /**
     * Returns true if the command terminates the program.
     *
     * @return true if the command terminates the program.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command and returns and acknowledgement message.
     *
     * @param tasks The TaskList containing the tasks.
     * @param ui The Ui that interacts with the user.
     * @param storage The Storage to load and save tasks into the data file.
     * @return A string with the message to be printed.
     * @throws DukeException If the command is invalid.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
