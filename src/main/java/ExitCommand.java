/**
 * Represents a command that exits the chat bot, Duke.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
    }

    /**
     * Executes the main method of exiting the system of Duke.
     * @param tasks Task list that is updated.
     * @param ui Ui to display interaction messages with the user.
     * @param storage Storage where the updated list is saved into.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.endDuke();
    }

    /**
     * Returns if this command is an ExitCommand.
     * @return The result if this command gives an exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
