/**
 * Represents a command that executes the deletion of a task into the task list.
 */
public class DeleteCommand extends Command {
    protected int option;

    public DeleteCommand(int option) {
        this.option = option;
    }

    /**
     * Executes the main method of deleting the task into the task list, while the Ui replies the user
     * with the corresponding messages. The updated task list is then written and saved into the storage file.
     * @param tasks Task list that is updated.
     * @param ui Ui to display interaction messages with the user.
     * @param storage Storage where the updated list is saved into.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String s = ui.showDeletedTask(tasks, option);
        tasks.deleteTask(option);
        storage.writeFile(tasks);
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
