package command;

import storage.*;
import task.*;
import ui.*;
import java.io.IOException;

/**
 * Represents a command to exit the program. A <code>ExitCommand</code> object corresponds to the command when
 * exiting the program.
 */
public class ExitCommand extends Command {

    /**
     * Returns whether it is an exit command
     * @return The result of whether this command causes an exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command, exiting the program.
     * If storage file invalid, exception thrown and error message is returned.
     * @param taskList task list to be updated
     * @param ui User interface displayed
     * @param storage Save file to be updated
     * @throws IOException If storage file not found
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.displayBye();
    }
}