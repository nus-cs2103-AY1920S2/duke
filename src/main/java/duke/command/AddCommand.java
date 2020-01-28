package duke.command;

import duke.util.Storage;
import duke.util.Task;
import duke.util.TaskList;

public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public String execute(TaskList taskList, Storage storage) {
        return taskList.addTask(task, storage);
    }

}
