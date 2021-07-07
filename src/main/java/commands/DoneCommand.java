package commands;

import dukeexception.DukeException;
import dukeexception.InvalidIndexException;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

/**
 * Mark a Task in the TaskList as done.
 */
public class DoneCommand extends Command {

    private int index;

    /**
     * Constructor for DoneCommand.
     *
     * @param index the index of Task to be marked as done from TaskList.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Execute the DoneCommand. It marks Task as done.
     *
     * @param tasks This is the TaskList where the Task is stored.
     * @param ui This is to interact with the user interface, printing message of Task being marked as done.
     * @param storage This allows for TaskList to be updated with new Task status.
     * @throws DukeException thrown when index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = tasks.markTaskAsDone(this.index - 1);
            storage.saveTasks(tasks.getTasks());
            String msg = "Wow you finally completed something: \n";
            msg += t;
            ui.printMsg(msg);
            return msg;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * DoneCommand does not cause the programme to exit.
     *
     * @return boolean false since not ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
