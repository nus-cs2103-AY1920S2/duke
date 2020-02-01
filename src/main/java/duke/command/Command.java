package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable command.
 */
public class Command {

    /**
     * Create instance of Command.
     */
    public Command() {

    }

    /**
     * Execute the command and perform relative operation and return the feedback to user.
     *
     * @param tasks TaskList which contain all existing task
     * @param ui Ui which handle ui operation
     * @param storage Storage to perform read and write operation to the file
     *
     * @throws DukeException the exception for the unimplemented method
     * @return feedback to user
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("This method is to be implemented by child classes");
    }
}
