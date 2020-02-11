package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    private int validNumOfArgs = 2;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isByeCommand();

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