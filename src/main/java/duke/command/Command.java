package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable command.
 */
public class Command {
    public Command() {}

    /**
     * Check and return whether the command is exit command.
     *
     * @return boolean type data represent whether is exit command or not.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Execute the command and perform relative operation.
     *
     * @param tasks TaskList which contain all existing task
     * @param ui Ui which handle ui operation
     * @param storage Storage to perform read and write operation to the file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("This method is to be implemented by child classes");
    }
}
