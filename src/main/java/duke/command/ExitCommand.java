package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Represents an exit command.
 * The command deals with saving the resulting list of tasks to storage.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand with isExit initialised as true.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Saves the resulting list of tasks in file and returns relevant messages.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param ui The Ui that deals with interactions with user.
     * @param storage The Storage deals with loading and saving tasks in file.
     * @return The relevant messages in the form of String.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveTasksToStorage(tasks);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
        return ui.showExit();
    }

}
