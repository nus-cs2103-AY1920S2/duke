package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

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
