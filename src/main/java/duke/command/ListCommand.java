package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

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
     * @param tasks   TaskList of Duke.
     * @param storage To load from and save to the disk.
     * @return List of tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.listTasks();
    }
}
