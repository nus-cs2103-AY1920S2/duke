/**
 * Represents a command that lists all the tasks from the task list.
 */
public class ListCommand extends Command {

    public ListCommand() {
    }

    /**
     * Executes the main method of listing the tasks from the task list, while the Ui replies the user
     * with the corresponding messages.
     * @param tasks Task list that is updated.
     * @param ui Ui to display interaction messages with the user.
     * @param storage Storage where the updated list is saved into.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printList(tasks);
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
