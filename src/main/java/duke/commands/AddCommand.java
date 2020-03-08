package duke.commands;

import duke.tasks.Task;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.TaskList;
import java.io.IOException;

/**
 * Command that adds a task to the TaskList.
 */
public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String action(TaskList taskList, Storage storage, Ui ui) throws IOException {
        taskList.addTask(task);
        storage.writeToOld(task.formatText());
        return ui.printAddedTask(task, taskList);
    }
}

