package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {

    protected Task task;

    /**
     * Constructs AddCommand object.
     *
     * @param task The task to add to the task list.
     */
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    /**
     * Executes AddCommand by adding the task to the task list.
     *
     * @param tasks   TaskList of Duke.
     * @param storage To load from and save to the disk.
     * @return Acknowledgement message sent by Duke.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.addTask(this.task);
    }
}
