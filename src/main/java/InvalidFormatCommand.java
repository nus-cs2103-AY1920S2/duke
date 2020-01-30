/**
 * Represents an improperly formatted command.
 */
public class InvalidFormatCommand extends Command {
    /**
     * Calls the UI to alert the user that the format of the command is wrong.
     *
     * @param tasks the task list to operate on
     * @param ui the user interface
     * @param storage the storage for the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showInvalidFormatMessage();
    }
}
