package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Represents a command to quit the application.
 */
public class ByeCommand extends Command {
    /**
     * Represents a ByeCommand object.
     */
    public ByeCommand() {

    }

    /**
     * Shows the quit message to the user.
     *
     * @param taskList The TaskList object.
     * @param ui The User Interface object.
     * @param storage The hard disk object.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayBye();
    }

    @Override
    public boolean hasNextCommand() {
        return false;
    }
}
