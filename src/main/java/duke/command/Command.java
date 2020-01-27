package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;

public abstract class Command {
    protected boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}