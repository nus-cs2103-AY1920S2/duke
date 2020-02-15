package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a command to be executed.
 */
public abstract class Command {

    /**
     * Returns true if the command terminates the program.
     *
     * @return true if the command terminates the program.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command and returns and acknowledgement message.
     *
     * @param tasks The TaskList containing the tasks.
     * @return A string with the message to be printed.
     * @throws DukeException If the command is invalid.
     */
    public abstract String execute(TaskList tasks) throws DukeException;
}
