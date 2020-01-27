package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

public class ListCommand extends Command {
    public ListCommand() {

    }

    public String execute(TaskList taskList, Storage storage) {
        return taskList.listTasks();
    }
}
