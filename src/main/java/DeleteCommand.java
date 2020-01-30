/**
 * Represents a command to delete a task specified by
 * its index in the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand that takes in the
     * index of the task to be deleted.
     *
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task specified by the index from the task list,
     * stores the new task list and calls the UI to show the deleted task.
     *
     * @param tasks the task list to operate on
     * @param ui the user interface
     * @param storage the storage for the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.deleteTask(index);
        ui.showDeletedTask(deletedTask);
        storage.save(tasks);
    }
}
