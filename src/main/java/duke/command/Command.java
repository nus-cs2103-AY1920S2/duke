package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that is to be executed.
 */
public abstract class Command {

    protected boolean isExit;

    /**
     * Constructs a Command with the isExit being initialised as false.
     */
    public Command() {
        isExit = false;
    }

    /**
     *Returns true if the command is exit command.
     *
     * @return True if the command is exit command.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Execute the command.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param ui The Ui that deals with interactions with user.
     * @param storage The Storage deals with loading and saving tasks in file.
     * @throws DukeException If description of the task is missing or in wrong format.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
