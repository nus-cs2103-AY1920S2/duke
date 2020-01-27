package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    protected boolean isExit;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }
}
