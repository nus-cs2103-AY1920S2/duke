package duke.logic.commands;

import duke.commons.exceptions.DukeException;
import duke.commons.exceptions.InvalidIndexException;
import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command that could be executed.
 */

public abstract class Command {

    protected String commandWord;

    public Command(String commandWord) {
        this.commandWord = commandWord;
    }

    /**
     * Returns <code>true</code> if, and only if, this <code>Command</code> is an <code>ExitCommand</code>.
     *
     * @return <code>true</code> if this <code>Command</code> is an <code>ExitCommand</code>,
     * and <code>false</code> otherwise.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }

    /**
     * Executes the <code>Command</code> and returns an empty <code>String</code>.
     *
     * @param tasks <code>TaskList</code> object of the program.
     * @param ui <code>Ui</code> object of the program.
     * @param storage <code>Storage</code> object of the program.
     * @throws DukeException If the <code>Command</code> could not be executed.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "";
    }
}
