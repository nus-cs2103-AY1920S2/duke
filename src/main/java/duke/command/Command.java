package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a command.
 * Class is declared abstract for subclasses to implement the execute method.
 */
public abstract class Command {
    /**
     * Execute the corresponding command.
     *
     * @param storage Storage class for the commands to read/write data from the file.
     * @param tasks TaskList class for the commands to make operations on the list of tasks.
     * @return Every command should return an response to the user.
     * @throws DukeException Read/write errors are thrown as exceptions.
     */
    public abstract String execute(Storage storage, TaskList tasks) throws DukeException;
}
