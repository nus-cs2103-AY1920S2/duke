package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    /**
     * Constructs an ExitCommand.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Saves the tasks in the TaskList to the data file and terminates the program with the exit message.
     * @param tasks The TaskList containing the tasks.
     * @param ui The Ui that interacts with the user.
     * @param storage The Storage to load and save tasks into the data file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        ui.showExit();
    }
}
