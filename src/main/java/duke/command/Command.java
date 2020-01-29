package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {

    private boolean isQuitCommand;

    public Command() {
        this.isQuitCommand = false;
    }

    protected abstract void execute(Storage storage, TaskList taskList);

    protected boolean isQuit() {
        return this.isQuitCommand;
    }

}
