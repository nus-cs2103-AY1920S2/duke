package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command object for listing the tasks in the list.
 */
public class ListCommand extends Command {
    /**
     * Executes the command to lists out the tasks in the list.
     * @param tasks List of tasks of the Duke object.
     * @param ui UI object of the Duke object.
     * @param storage Storage object of the Duke object.
     * @return String as the response of the execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return Ui.showList(tasks);
    }
}
