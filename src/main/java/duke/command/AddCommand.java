package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
     * @param taskList   TaskList of Duke.
     * @param ui      The user interface.
     * @param storage To load from and save to the disk.
     * @return Acknowledgement message sent by Duke.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.addTask(this.task);
    }
}
