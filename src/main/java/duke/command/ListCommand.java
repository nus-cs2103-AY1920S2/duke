package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a list command. Upon execution, produces some feedback to the user.
 */
public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showAllTask(tasks);
    }
}
