package duke.logic.commands;

import duke.commons.exceptions.InvalidIndexException;
import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a type of command that could be executed. An <code>ExitCommand</code> object corresponds to
 * a command to exit the program.
 */

public class ExitCommand extends Command {

    public ExitCommand(String commandWord) {
        super(commandWord);
    }

    /**
     * Executes the <code>Command</code> and returns an output <code>String</code> that shows the exit message.
     *
     * @param tasks <code>TaskList</code> object of the program.
     * @param ui <code>Ui</code> object of the program.
     * @param storage <code>Storage</code> object of the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.exit();
    }
}
