package duke.command;

import duke.task.TaskList;
import duke.util.Storage;

public abstract class Command {

    /**
     * Abstract execute method for all Command subclasses.
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public abstract void execute(TaskList taskList, Storage storage);

    /**
     * Abstract method for all Command subclasses.
     * @return boolean indicating if command is an exit command.
     */
    public abstract boolean isExit();
}
