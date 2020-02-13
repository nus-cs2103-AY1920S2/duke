package command;

import storage.*;
import task.*;
import ui.*;
import java.io.IOException;

/**
 * Represents the command that is to be executed. A <code>Command</code> object corresponds to the
 * command that is to be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     * If storage file invalid, exception thrown and error message is returned.
     * @param taskList task list to be updated
     * @param ui User interface displayed
     * @param storage Save file to be updated
     * @throws IOException If storage file not found
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

    /**
     * Returns whether it is an exit command
     * @return The result of whether this command causes an exit.
     */
    public abstract boolean isExit();
}