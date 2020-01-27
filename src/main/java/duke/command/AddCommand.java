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
     * Constructor for AddCommand.
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
     * @param tasks TaskList of Duke.
     * @param ui The user interface.
     * @param storage To load from and save to the disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
    }
}
