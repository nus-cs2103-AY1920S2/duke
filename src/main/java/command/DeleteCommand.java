package command;

import common.Message;
import common.Storage;
import task.Task;
import ui.TextUi;
import exception.DukeException;
import task.TaskList;

/**
 * Represents a command that deletes a Task from TaskList.
 */
public class DeleteCommand extends Command {

    protected int index;
    protected Task deletedTask;
    protected boolean isValid;

    /**
     * The constructor of class DeleteCommand.
     * @param index representing the position of the task being deleted in the task list
     */
    public DeleteCommand(int index) {
        super();
        this.index = index - 1;
        this.isValid = false;
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
        this.isValid = true;
        this.deletedTask = tasks.getList().get(this.index);
        String res = textUi.showDeletingTask_Str(this.index, tasks);
        tasks.delete(this.index);
        storage.writeToFile(tasks.getList());
        return res;
    }

    /**
     * Undoes the deleting command.
     *
     * @param tasks A TaskList containing all tasks
     * @param textUi a TextUi object that handles user-system interaction
     * @param storage A Storage object which specifies the location of the data
     * @return a string representing the adding back command.
     * @throws DukeException when invalid user input is detected
     */
    public String undo(TaskList tasks, TextUi textUi, Storage storage) throws DukeException {
        if (!this.isValid) {
            return textUi.showError_Str(Message.MESSAGE_PREVIOUSINVALID);
        }
        tasks.add(this.deletedTask);
        storage.writeToFile(tasks.getList());
        return textUi.showAddingTask_Str(this.deletedTask, tasks);
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
        return true;
    }

}
