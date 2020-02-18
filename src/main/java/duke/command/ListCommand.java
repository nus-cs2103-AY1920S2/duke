package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Lists tasks from the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the ListCommand by listing all the tasks in the task list.
     *
     * @param taskList   TaskList of Duke.
     * @param ui      The user interface.
     * @param storage To load from and save to the disk.
     * @return List of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.listTasks();
    }
}
