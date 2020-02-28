package command;

import common.Message;
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
     * Informs the user that find command cannot be undone.
     *
     * @param tasks A TaskList containing all tasks
     * @param textUi a TextUi object that handles user-system interaction
     * @param storage A Storage object which specifies the location of the data
     * @return a string representing the result of undoing the previous command
     * @throws DukeException when errors or invalid input is detected
     */
    public String undo(TaskList tasks, TextUi textUi, Storage storage) throws DukeException {
        return textUi.showError_Str(Message.MESSAGE_CANNOTUNDO);
    }

    /**
     * Returns whether the current command is an exit command.
     *
     * @return a boolean value representing the property of the current command
     */
    public boolean isExit() {
        return false;
    }

    public boolean isUndoable() {
        return false;
    }

}