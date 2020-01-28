package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;

/**
 * Lists the tasks in the TaskList.
 */
public class ListCommand extends Command {
    /**
     * Prints the tasks in the specified TaskList.
     * @param tasks The TaskList containing the tasks.
     * @param ui The Ui that interacts with the user.
     * @param storage The Storage to load and save tasks into the data file.
     * @throws DukeException If the index is out of range (index < 1 || index > size()).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showTaskList(tasks);
    }
}
