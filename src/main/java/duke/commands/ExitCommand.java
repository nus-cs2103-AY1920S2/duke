package duke.commands;

import duke.tasks.*;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public class ExitCommand extends Command {

    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Exits the program upon user input of "bye"
     * @param tasks List of current tasks
     * @param ui User interface used to reply user
     * @param storage For storing of tasks into file
     * @throws DukeException If input format is wrong. Not used here
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "See you again!";
    }
}
