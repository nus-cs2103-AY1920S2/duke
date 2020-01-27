package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.storage.Storage;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    protected void execute(Storage storage, TaskList taskList) {
        // To be implemented
    }

}
