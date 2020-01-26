package duke.command;

import duke.task.TaskList;
import duke.util.Storage;

public class ExitCommand extends Command {

    public void execute(TaskList taskList, Storage storage) { }

    /**
     * Inform if command is an exit command.
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return true;
    }
}
