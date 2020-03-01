package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    private int validNumOfArgs = 2;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isByeCommand();

    /**
     * Checks if the command contains a valid number of arguments.
     * @param numOfArgs the valid number of arguments
     * @return true if the comamnd contains the valid number of args, false otherwise
     */
    public boolean hasValidNumOfArgs(int numOfArgs) {
        boolean result;
        if (numOfArgs < validNumOfArgs) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }
}