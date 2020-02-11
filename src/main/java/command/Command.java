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

    /**
     * Undoes the command.
     *
     * @param tasks A TaskList containing all tasks
     * @param textUi a TextUi object that handles user-system interaction
     * @param storage A Storage object which specifies the location of the data
     * @return a string representing the result of the undo command
     */
    public abstract String undo(TaskList tasks, TextUi textUi, Storage storage) throws DukeException;

    public abstract boolean isExit();

    public abstract boolean isUndoable();
}
