package duke.command;

import duke.main.TaskList;
import duke.main.Ui;
import duke.main.Storage;
import duke.DukeException;

/**
 * Command that is executed when user inputs 'bye'.
 */
public class ByeCommand extends Command {

    /**
     * Executes the 'bye' command and saves latest version of the task list.
     *
     * @param tasks Task list
     * @param ui Current user interface
     * @param storage Current storage
     * @return String representation of goodbye message
     * @throws DukeException If task list fails to save
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks.getTaskList());
        return ui.showGoodbye();
    }

    /**
     * Updates main function if it should exit the programme or not.
     *
     * @return True if 'bye' command is called, false otherwise. In this case, true is returned.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
