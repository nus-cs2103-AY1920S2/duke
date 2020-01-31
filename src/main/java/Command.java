package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;

import java.io.IOException;

/**
 * Abstract Command class handles user's request.
 */
public abstract class Command {

    /**
     * Abstract execute method that handles user input.
     *
     * @param tasks list of tasks.
     * @param ui user interface.
     * @param storage makeshift database for tasks.
     * @throws DukeException if user input does not follow input format.
     * @throws IOException named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be open for any other reason.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * Returns a boolean that determines if command exits the program.
     *
     * @return boolean.
     */
    public abstract boolean isExit();

}