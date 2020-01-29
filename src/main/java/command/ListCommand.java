package command;

import storage.*;
import task.*;
import ui.*;
import java.io.IOException;

/**
 * Represents a command which shows the list of tasks left. A <code>ListCommand</code> object corresponds to
 * an ArrayList which saves the tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command and triggers UI to display the list
     * @param taskList task list to be updated
     * @param ui User interface displayed
     * @param storage Save file to be updated
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.displayTasks(taskList);
    }

    /**
     * Returns whether it is an exit command
     * @return The result of whether this command causes an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}