package command;

import common.Storage;
import task.TaskList;
import exception.DukeException;
import ui.TextUi;

/**
 * Represents a command that finds matching items for the user.
 */
public class FindCommand extends Command {

    protected String searchingItem;

    public FindCommand(String item) {
        super();
        this.searchingItem = item;
    }

    /**
     * Executes the "finding" type of commands.
     *
     * @param tasks   A TaskList containing all tasks
     * @param textUi a TextUi object that handles user-system interaction
     * @param storage A Storage object which specifies the location of the data
     * @throws DukeException a duke exception representing errors in user input or storage
     */
    public String execute(TaskList tasks, TextUi textUi, Storage storage) throws DukeException {
        return textUi.findList_Str(tasks, searchingItem);
    }

    /**
     * Returns whether the current command is an exit command.
     *
     * @return a boolean value representing the property of the current command
     */
    public boolean isExit() {
        return false;
    }

}