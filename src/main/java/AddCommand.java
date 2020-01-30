/**
 * Abstract class representing a command to add a generic task to the task list,
 * to be executed later.
 */
public abstract class AddCommand extends Command {
    /**
     * The task to be added to the list.
     */
    Task task;

    /**
     * Adds the task to task list specified, stores the new list and
     * calls the UI to notify the user.
     *
     * @param tasks the task list to operate on
     * @param ui the user interface
     * @param storage the storage for the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.save(tasks);
        ui.showAddedTask(task, tasks.getCount());
    }
}
