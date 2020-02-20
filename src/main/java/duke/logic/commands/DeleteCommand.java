package duke.logic.commands;

import duke.commons.Task;
import duke.commons.exceptions.DukeException;
import duke.commons.exceptions.InvalidIndexException;
import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a type of command that could be executed. A <code>DeleteCommand</code> object corresponds to
 * a command to delete task(s).
 */

public class DeleteCommand extends Command {

    private int deleteIndex;

    public DeleteCommand(String commandWord, int deleteIndex) {
        super(commandWord);
        this.deleteIndex = deleteIndex - 1;
    }

    /**
     * Executes the <code>Command</code> and returns an output <code>String</code> that either
     * confirms that the <code>Command</code> is successfully executed,
     * or shows error message.
     *
     * @param tasks <code>TaskList</code> object of the program.
     * @param ui <code>Ui</code> object of the program.
     * @param storage <code>Storage</code> object of the program.
     * @throws InvalidIndexException If the specified index is out of bound.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        Task task = tasks.getTask(this.deleteIndex);
        tasks.deleteTask(this.deleteIndex);
        String output = ui.printDeletingMessage(tasks, task);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            output += ui.showDeletingError(e.getMessage());
        }
        return output;
    }
}
