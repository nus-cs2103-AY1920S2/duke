package duke.command;

import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a ExitCommand.
 * Used to execute the exit command.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param ui Ui object for the command to interact with the user.
     * @param storage storage object for the retrieval/saving of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
    }
}
