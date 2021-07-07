package command;

import common.Message;
import common.Storage;
import ui.TextUi;
import exception.DukeException;
import task.TaskList;

/**
 * Represents a command that terminates the program.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    /**
     * Executes the "exit" type of commands.
     *
     * @param tasks   A TaskList containing all tasks
     * @param textUi a TextUi object that handles user-system interaction
     * @param storage A Storage object which specifies the location of the data
     * @throws DukeException a duke exception representing errors in user input or storage
     */
    public String execute(TaskList tasks, TextUi textUi, Storage storage) throws DukeException {
        return "     It is time to say goodbye :(";
    }

    /**
     * Informs the user that exit command cannot be undone.
     *
     * @param tasks A TaskList containing all tasks
     * @param textUi a TextUi object that handles user-system interaction
     * @param storage A Storage object which specifies the location of the data
     * @return a string representing the result of undoing the previous command
     * @throws DukeException when invalid input is detected
     */
    public String undo(TaskList tasks, TextUi textUi, Storage storage) throws DukeException {
        return textUi.showError_Str(Message.MESSAGE_CANNOTUNDO);
    }

    /**
     * Returns whether the current command is an exit command.
     *
     * @return a boolean value representing the property of the current command
     *     which is True for ExitCommand objects
     */
    public boolean isExit() {
        return true;
    }

    public boolean isUndoable() {
        return false;
    }

}
