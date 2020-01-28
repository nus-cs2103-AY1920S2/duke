package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Returns true if the command terminates the program.
     * @return true if the command terminates the program.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command.
     * @param tasks The TaskList containing the tasks.
     * @param ui The Ui that interacts with the user.
     * @param storage The Storage to load and save tasks into the data file.
     * @throws DukeException If the command is invalid.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
