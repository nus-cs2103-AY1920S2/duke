package duke.command;

import duke.interaction.Ui;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Represents an executable command of undoing an AddCommand
 * It deletes the task at the input task index from the task list.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class UndoAddCommand extends Command {

    /**
     * Executes delete behaviour of the latest added task.
     *
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        String output = taskList.deleteTask(
                taskList.getList().size() - 1
        );

        if (output == null) {
            Ui.showTaskNotFound();
            return;
        }

        storage.saveTaskListToFile(taskList);
        Ui.showTaskDelete(output, taskList.getList().size());

    }

    /**
     * Inform if command is an exit command.
     *
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

}
