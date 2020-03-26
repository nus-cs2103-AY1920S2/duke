package duke.commands;

import duke.commands.exceptions.CommandException;
import duke.model.Storage;
import duke.model.TaskList;

/**
 * The <code>Command</code> class represents a command made by the user, executes the command and
 * returns the result in a string.
 *
 * <p>Inspired by AddressBook3.
 */
public abstract class Command {
    
    /**
     * Executes the command and returns the result message.
     *
     * @param taskList Task list involved.
     * @param storage Storage component involved.
     * @return The result of executing the command.
     * @throws CommandException If command execution fails.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws CommandException;
}
