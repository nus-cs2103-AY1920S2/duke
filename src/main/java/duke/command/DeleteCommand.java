package duke.command;

import duke.task.TaskList;
import duke.util.Storage;

/**
 * Represents the Command for the "delete" input by the user.
 * It deletes the task at the input task index from the task list.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
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
     * Executes Delete behaviour of deleted task at given index.
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        if (taskList.deleteTask(indexToDelete)) {
            storage.saveTaskListToFile(taskList);
        }
    }

    /**
     * Inform if command is an exit command.
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
