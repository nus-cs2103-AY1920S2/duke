package duke.command;

import duke.History;
import duke.TaskList;
import duke.exception.DukeException;


/**
 * Represents a command that is to be executed.
 */
public abstract class Command {

    protected boolean isExit;

    /**
     * Constructs a Command with the isExit being initialised as false.
     */
    public Command() {
        isExit = false;
    }

    /**
     *Returns true if the command is exit command.
     *
     * @return True if the command is exit command.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the command and returns the relevant messages.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param history The History that deals with past commands.
     * @return The relevant messages in the form of String.
     * @throws DukeException If description of the task is missing or in wrong format.
     */
    public abstract String execute(TaskList tasks, History history) throws DukeException;
}
