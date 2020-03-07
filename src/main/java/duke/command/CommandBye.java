package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.interact.UiDesign;

/**
 * Represents a bye command where the program will shut down.
 */

public class CommandBye implements Command {

    /**
     * Saves the TaskList into Storage.
     * @param tasks TaskList to be stored.
     * @param storage Storage where the TaskList will be saved in.
     * @throws DukeException Exception thrown if saving fails.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        storage.save(tasks);
        System.exit(0);
    }

    @Override
    public String output() {
        return UiDesign.BYE.getString();
    }
}
