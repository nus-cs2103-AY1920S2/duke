package command;

import common.Message;
import common.Storage;
import ui.TextUi;
import exception.DukeException;
import task.TaskList;

/**
 * Represents a command that deletes a Task from TaskList.
 */
public class DeleteCommand extends Command {

    protected int index;

    public DeleteCommand(int index) {
        super();
        this.index = index - 1;
    }

    /**
     * Executes the "deleting" type of commands.
     *
     * @param tasks   A TaskList containing all tasks
     * @param textUi a TextUi object that handles user-system interaction
     * @param storage A Storage object which specifies the location of the data
     * @throws DukeException a duke exception representing errors in user input or storage
     */
    public String execute(TaskList tasks, TextUi textUi, Storage storage) throws DukeException {
        if (this.index >= tasks.getList().size() || this.index < 0) {
            return textUi.showError_Str(Message.MESSAGE_INVALIDCOMMAND);
        }
        String res = textUi.showDeletingTask_Str(this.index, tasks);
        tasks.delete(this.index);
        storage.writeToFile(tasks.getList());
        return res;
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
