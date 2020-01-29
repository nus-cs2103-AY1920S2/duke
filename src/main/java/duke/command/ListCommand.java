package duke.command;

import duke.task.TaskList;
import duke.interaction.Ui;
import duke.util.Storage;

/**
 * Represents the Command for the "list" input by the user.
 * It displays all task items in the task list.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class ListCommand extends Command {

    /**
     * Executes List behaviour of showing all tasks in collection.
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        Ui.showAllTasks(taskList);
    }

    /**
     * Inform if command is an exit command.
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
