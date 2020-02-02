package commands;

import dukeexception.DukeException;
import dukeexception.InvalidIndexException;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

/**
 * Deletes a Task from the TaskList.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index the index of Task to be deleted from TaskList.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Execute the DeleteCommand. Delete Task from TaskList.
     *
     * @param tasks This is the TaskList Task is being deleted from.
     * @param ui This is to interact with the user interface, printing message of Task being deleted.
     * @param storage This allows for TaskList to be updated.
     * @throws DukeException thrown when index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = tasks.removeTask(this.index - 1);
            storage.saveTasks(tasks.getTasks());
            String msg = "Noted. I've removed this task:\n" + t + '\n';
            msg += "Now you have " + tasks.getNumTasks() + " tasks in the list.";
            ui.printMsg(msg);
            return msg;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * DeleteCommand does not cause the programme to exit.
     *
     * @return boolean false since not ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
