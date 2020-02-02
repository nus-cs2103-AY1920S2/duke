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
     * @throws DukeException If the index is out of range (index < 1 || index > size()).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.isEmpty()) {
            return ui.showToUser("There are no tasks in the list.");
        } else {
            return ui.showToUser("Here are the tasks in your list:", tasks.toString());
        }
    }
}
