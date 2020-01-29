package duke.command;

import duke.ui.Ui;
import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a ListCommand.
 * Used to execute the list command.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param ui Ui object for the command to interact with the user.
     * @param storage storage object for the retrieval/saving of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printDividerLine();
        ui.printMessageNoDivider("     Here are the tasks in your list:\n");
        ui.printMessageNoDivider(tasks.toString());
        ui.printDividerLine();
    }
}
