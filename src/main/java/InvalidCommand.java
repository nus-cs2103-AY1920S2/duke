/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Calls the UI  to alert the user of an invalid command.
     *
     * @param tasks the task list to operate on
     * @param ui the user interface
     * @param storage the storage for the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showInvalidCommandMessage();
    }
}
