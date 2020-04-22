package duke.logic.commands;

import duke.commons.exceptions.InvalidIndexException;
import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a type of command that could be executed. A <code>ListCommand</code> object corresponds to
 * a command to list all existing task(s).
 */

public class HelpCommand extends Command {

    public HelpCommand(String commandWord) {
        super(commandWord);
    }

    /**
     * Executes the <code>Command</code> and returns an output <code>String</code>.
     *
     * @param tasks <code>TaskList</code> object of the program.
     * @param ui <code>Ui</code> object of the program.
     * @param storage <code>Storage</code> object of the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return Ui.printHelp();
    }
}
