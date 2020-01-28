package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    protected boolean isExit;

    public Command() {
        isExit = false;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
