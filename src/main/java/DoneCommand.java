/**
 * Represents a command that marks a task to be done before updating it into the task list.
 */
public class DoneCommand extends Command {
    protected int option;

    public DoneCommand(int option) {
        this.option = option;
    }

    /**
     * Executes the main method of updating the task's done status into the task list, while the Ui replies the user
     * with the corresponding messages. The updated task list is then written and saved into the storage file.
     * @param tasks Task list that is updated.
     * @param ui Ui to display interaction messages with the user.
     * @param storage Storage where the updated list is saved into.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markAsDone(option);
        ui.showDoneTask(tasks.arr.get(option - 1));
        storage.writeFile(tasks);
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

