package duke.command;

import duke.task.TaskList;
import duke.util.Storage;

public class ExitCommand extends Command {

    public void execute(TaskList taskList, Storage storage) { }

    public boolean isExit() {
        return true;
    }
}
