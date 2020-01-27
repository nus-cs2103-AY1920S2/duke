package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

public abstract class Command {
    public abstract String execute(TaskList taskList, Storage storage);
}
