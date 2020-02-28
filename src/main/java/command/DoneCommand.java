package command;

import common.Message;
import common.Storage;
import task.Task;
import ui.TextUi;
import exception.DukeException;
import task.TaskList;

/**
 * Represents a done command that set a Task to done.
 */
public class DoneCommand extends Command {

    protected int index;
    protected Task doneTask;
    protected boolean isValid;

    /**
     * Constructor of the DoneCommand class.
     * @param index representing the position of the task being marked as done
     */
    public DoneCommand(int index) {
        super();
        this.index = index - 1;
        this.isValid = false;
    }

    /**
     * Executes the "done" type of commands.
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
        //add an assertion to check the range of this.index
        assert (this.index <= tasks.getList().size() && this.index >= 0) : "out of bound";
        this.isValid = true;
        tasks.done(this.index);
        storage.writeToFile(tasks.getList());
        this.doneTask = tasks.getList().get(this.index);
        return textUi.showDoneTask_Str(this.index, tasks);

    }

    /**
     * Undoes the done command.
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
        tasks.unDone(this.doneTask);
        storage.writeToFile(tasks.getList());
        return textUi.showUndoneTask(this.doneTask, tasks);
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
