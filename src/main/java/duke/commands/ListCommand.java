package duke.commands;

import duke.tasks.*;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public class ListCommand extends Command {

    public ListCommand(String command) {
        super(command);
    }

    /**
     * Prints out the current list of tasks to terminal
     * @param tasks List of current tasks
     * @param ui User interface used to reply user
     * @param storage For storing of tasks into file
     * @throws DukeException If input format is wrong. Not used here
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.printTaskList(ui);
    }
}
