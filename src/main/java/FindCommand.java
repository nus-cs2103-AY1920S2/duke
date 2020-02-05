/**
 * Represents a command that finds tasks from the task list using a specified keyword.
 */
public class FindCommand extends Command {
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the main method of finding tasks from the task list, while the Ui replies the user
     * with the corresponding messages.
     * @param tasks Task list that is updated.
     * @param ui Ui to display interaction messages with the user.
     * @param storage Storage where the updated list is saved into.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showFoundTasks(tasks.findTasks(keyword));
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
