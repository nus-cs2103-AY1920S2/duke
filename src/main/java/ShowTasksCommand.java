/**
 * Represents a command to show the list of tasks, to be executed later.
 */
public class ShowTasksCommand extends Command {
    /**
     * Passes the task list to the UI to be shown.
     *
     * @param tasks the task list to operate on
     * @param ui the user interface
     * @param storage the storage for the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }
}
