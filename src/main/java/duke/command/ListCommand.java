package command;
import java.io.IOException;

import storage.Storage;

import task.TaskList;

import ui.Ui;
/**
 * Represents the command of displaying the task list. A <code>ListCommand</code> object corresponds to the command of
 * displaying the current task list.
 */
public class ListCommand extends Command {
    /**
     * Displays the task list.
     * If storage file is invalid, an error message is returned.
     * @param tasks The task list to be updated.
     * @param ui The user interface to be to be shown.
     * @param storage The storage file to be updated.
     * @throws IOException If the storage file is not found.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.printList(tasks);
    }
    /**
     * Returns the result of whether this is an exit program command.
     * @return The result of whether this command can exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
