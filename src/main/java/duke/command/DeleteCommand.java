package duke.command;

import duke.task.TaskList;
import duke.util.Storage;

public class DeleteCommand extends Command {

    int indexToDelete;

    /**
     * DeleteCommand constructor.
     * @param index of the task in collection to be deleted.
     */
    public DeleteCommand(int index) {
        indexToDelete = index;
    }

    /**
     * Executes Delete behaviour of deleted task at given index
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        if (taskList.DeleteTask(indexToDelete))
            storage.SaveTaskListToFile(taskList);
    }

    /**
     * Inform if command is an exit command.
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
