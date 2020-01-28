package command;

import core.Common;

import core.Ui;
import dukexception.DukeException;

/**
 * Specific command to delete a task based on index.
 */
public class Command_Delete extends Command{

    private int index;

    /**
     * Constructor for command to delete the task.
     * @param index indicates the specific task to be deleted.
     */
    public Command_Delete(int index){
        this.index=index;
    }

    /**
     * Execute to delete the specific index task from storage.
     * @param common to delete task from the data storage.
     * @param ui to display the response text of deleting task.
     * @throws DukeException when deleting task is unsuccessful.
     */
    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        ui.display(common.deleteTask(index));
    }
}
