/**
 * Represents a command that executes the addition of a task into the task list.
 */
public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the main method of adding the task into the task list, while the Ui replies the user
     * with the corresponding messages. The updated task list is then written and saved into the storage file.
     * @param tasks Task list that is updated.
     * @param ui Ui to display interaction messages with the user.
     * @param storage Storage where the updated list is saved into.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.appendFile(task);
        String s = ui.showAddedTask(task, tasks);
        return s;
    }

    /**
     * Returns if this command is an ExitCommand.
     * @return The result if this command gives an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
