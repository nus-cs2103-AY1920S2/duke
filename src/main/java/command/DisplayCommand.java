package command;

import common.Message;
import common.Storage;
import task.TaskList;
import exception.DukeException;
import ui.TextUi;

/**
 * Represents a command that displays the task list.
 */
public class DisplayCommand extends Command {

    public DisplayCommand() {
        super();
    }

    /**
     * Executes the "display" type of commands.
     *
     * @param tasks   A TaskList containing all tasks
     * @param textUi a TextUi object that handles user-system interaction
     * @param storage A Storage object which specifies the location of the data
     */
    public String execute(TaskList tasks, TextUi textUi, Storage storage) {
        return textUi.displayList_Str(tasks, storage);
    }

    /**
     * Informs the user that display command cannot be undone.
     * @param tasks A TaskList containing all tasks
     * @param textUi a TextUi object that handles user-system interaction
     * @param storage A Storage object which specifies the location of the data
     * @return a string representing the result of undoing a display command
     * @throws DukeException when invalid input is detected
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