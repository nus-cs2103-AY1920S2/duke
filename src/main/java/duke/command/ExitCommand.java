package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an exit/bye command. Upon execution, produces some feedback to the user.
 */
public class ExitCommand extends Command {

    /**
     * Create instance of ExitCommand.
     */
    public ExitCommand() {

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }
}
