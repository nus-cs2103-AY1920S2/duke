/**
 * Represents a command to mark a task in a task list as complete,
 * to be executed later.
 */
public class CompleteTaskCommand extends Command {
    private int index;

    /**
     * Constructor for CompleteTaskCommand that takes in
     * the index of the task to be completed in the task list.
     *
     * @param index the index of the task
     */
    public CompleteTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Completes the task in the task list specified by the index,
     * stores the new task list and calls the UI to show the completed task.
     *
     * @param tasks the task list to operate on
     * @param ui the user interface
     * @param storage the storage for the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task completedTask = tasks.completeTask(index);
        ui.showDonetask(completedTask);
        storage.save(tasks);
    }
}
