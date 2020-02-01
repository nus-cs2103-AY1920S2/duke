package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

import java.io.IOException;

public abstract class Command {

    protected boolean isByeCommand;

    public Command() {
        this.isByeCommand = false;
    }

    public abstract String execute(Storage storage, TaskList taskList) throws IOException;

    public boolean isBye() {
        return this.isByeCommand;
    }

}
