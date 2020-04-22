package duke.logic.commands;

import duke.commons.Task;
import duke.commons.exceptions.DukeException;
import duke.commons.exceptions.DuplicateTaskException;
import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a type of command that could be executed. An <code>AddCommand</code> object corresponds to
 * a command to add new task(s).
 */

public class AddCommand extends Command {

    private String[] commands;

    public AddCommand(String commandWord, String[] commands) {
        super(commandWord);
        this.commands = commands;
    }

    /**
     * Executes the <code>Command</code> and returns an output <code>String</code> that either
     * confirms that the <code>Command</code> is successfully executed,
     * or shows error message.
     *
     * @param tasks <code>TaskList</code> object of the program.
     * @param ui <code>Ui</code> object of the program.
     * @param storage <code>Storage</code> object of the program.
     * @throws DuplicateTaskException If the <code>Task</code> has already existed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuplicateTaskException {
        Task task = tasks.addTask(commandWord, commands);
        String output = ui.printAddingMessage(tasks, task);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            output += ui.showDuplicateError(e.getMessage());
        }
        return output;
    }
}
