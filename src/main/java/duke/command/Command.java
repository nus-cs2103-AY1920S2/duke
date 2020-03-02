package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;
import exception.IllegalTextException;

/**
 * Represents the command to be executed, in response to user inputs.
 */
public abstract class Command {

    /**
     * Returns a response string through the Ui after carrying out the business logic
     * corresponding to the command type.
     * @param ui
     * @param storage
     * @return A string corresponding to the response message after executing the command.
     * @throws IllegalTextException
     */
    public abstract String execute(Ui ui, TaskStorage storage) throws IllegalTextException;
}
