package duke.command;

import duke.task.TaskList;
import duke.util.Storage;

public abstract class Command {

    public abstract void execute(TaskList taskList, Storage storage);
    public abstract boolean isExit();
}
