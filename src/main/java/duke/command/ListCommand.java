package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a list command. Upon execution, produces some feedback to the user.
 */
public class ListCommand extends Command {
    /**
     * Create instance of ListCommand.
     */
    public ListCommand() {

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showAllTask(tasks);
    }
}
