package duke.logic.commands;

import duke.commons.exceptions.InvalidIndexException;
import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a type of command that could be executed. A <code>FindCommand</code> object corresponds to
 * a command to find task(s) with matching keywords.
 */

public class FindCommand extends Command {

    protected String keyword;

    public FindCommand(String commandWord, String keyword) {
        super(commandWord);
        this.keyword = keyword;
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
        return tasks.findTask(ui, this.keyword);
    }
}
