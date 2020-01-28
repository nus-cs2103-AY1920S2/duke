package command;

import core.Common;
import core.Ui;
import dukexception.DukeException;
import task.Task;

/**
 * Generic command to add a new task.
 */
public abstract class AddTaskCommand extends Command{

    private Task task;

    /**
     * Constructor to create a new task.
     * @param task to be added to the storage.
     */
    public AddTaskCommand(Task task){
        this.task=task;
    }

    /**
     * Executes to add the new task to the storage.
     * @param common to add task to the data storage.
     * @param ui to display the response text of adding task.
     * @throws DukeException when adding of the new task is unsuccessful.
     */
    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        ui.display(common.addTask(task));
    }
}
