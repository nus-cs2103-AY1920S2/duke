package duke.command;
import java.io.IOException;

import duke.storage.Storage;

import duke.task.TaskList;

import duke.ui.Ui;
/**
 * Represents the command to be executed. A <code>Command</code> object corresponds to the command to be executed.
 */
public abstract class Command {
    /**
     * Executes the command.
     * If storage file is invalid, an error message is returned.
     * @param tasks The task list to be updated.
     * @param ui The user interface to be to be shown.
     * @param storage The storage file to be updated.
     * @throws IOException If the storage file is not found.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
    /**
     * Returns the result of whether this is an exit program command.
     * @return The result of whether this command can exit the program.
     */
    public abstract boolean isExit();
}
