package duke.commands;

import duke.tasks.Task;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.TaskList;
import java.io.IOException;

/**
 * Command that marks a task as done with a tick.
 */
public class DoneCommand extends Command {
    protected int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String action(TaskList taskList, Storage storage, Ui ui) throws IOException {
        Task task = taskList.getTask(index - 1);
        task.markDone();
        storage.writeNew(taskList);
        return ui.printDoneTask(task);
    }
}

