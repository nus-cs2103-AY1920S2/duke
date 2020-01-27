package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {

    protected boolean isExit;

    /**
     * Constructor for a command.
     *
     * @param isExit To indicate an exit command.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean on whether the command is an exit command
     * @return true if the command is an exit command.
     */
    public boolean isExit() {
        return this.isExit;
    }

}
