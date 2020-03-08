package duke.commands;

import duke.tasks.Task;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.TaskList;
import java.io.IOException;

/**
 * Command that deletes a task from TaskList.
 */
public class DeleteCommand extends Command {
    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String action(TaskList taskList, Storage storage, Ui ui) throws IOException {
        Task task = taskList.getTask(index - 1);
        taskList.deleteTask(task);
        storage.writeNew(taskList);
        return ui.printDeletedTask(task, taskList);
    }
}

