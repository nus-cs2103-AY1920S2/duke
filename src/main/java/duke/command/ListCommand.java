package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Lists the tasks in the TaskList.
 */
public class ListCommand extends Command {
    /**
     * Prints the tasks in the specified TaskList.
     *
     * @param tasks The TaskList containing the tasks.
     * @param ui The Ui that interacts with the user.
     * @param storage The Storage to load and save tasks into the data file.
     * @return A string with the message to be printed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return Ui.MESSAGE_NO_TASK;
        } else {
            return ui.showToUser(Ui.MESSAGE_LIST, tasks.toString());
        }
    }
}
