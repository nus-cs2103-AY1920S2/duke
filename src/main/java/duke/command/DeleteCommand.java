package duke.command;

import duke.task.TaskList;
import duke.util.Storage;

public class DeleteCommand extends Command {

    int indexToDelete;

    public DeleteCommand(int index) {
        indexToDelete = index;
    }

    public void execute(TaskList taskList, Storage storage) {
        if (taskList.DeleteTask(indexToDelete))
            storage.SaveTaskListToFile(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
