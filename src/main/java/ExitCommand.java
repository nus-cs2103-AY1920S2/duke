/**
 * Represents a command to stop the chatbot, to be executed later.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }

    /**
     * Calls the UI to show the closing message.
     *
     * @param tasks the task list to operate on
     * @param ui the user interface
     * @param storage the storage for the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }
}
