package command;

import exception.DukeException;
import task.TaskList;
import ui.TextUi;
import common.Storage;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   A TaskList containing all tasks
     * @param storage A Storage object which specifies the location of the data
     * @param textUi a TextUi object that handles user-system interaction
     * @throws DukeException If the execution fails.
     */
    public abstract String execute(TaskList tasks, TextUi textUi, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
