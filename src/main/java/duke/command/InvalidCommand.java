package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an invalid command. Upon execution, produces some feedback to the user.
 */
public class InvalidCommand extends Command {
    private String message;

    /**
     * @param message Message about the error of command
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showInvalid(message);
    }
}
