package command;

import ui.TextUi;
import common.Storage;
import common.Message;
import exception.DukeException;
import task.TaskList;

public class ErrorCommand extends Command {

    /**
     * Represents a wrong command which should be ignored with an error message.
     */
    public ErrorCommand() {
        super();
    }

    /**
     * Executes the "error" type of commands.
     *
     * @param tasks   A TaskList containing all tasks
     * @param textUi a TextUi object that handles user-system interaction
     * @param storage A Storage object which specifies the location of the data
     * @throws DukeException a duke exception representing errors in user input or storage
     */
    public String execute(TaskList tasks, TextUi textUi, Storage storage) throws DukeException {
        return textUi.showError_Str(Message.MESSAGE_INVALIDCOMMAND);
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
