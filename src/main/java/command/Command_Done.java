package command;

import core.Common;
import core.Ui;
import dukexception.DukeException;

/**
 * Specific command to mark the task as done.
 */
public class Command_Done extends Command{

    private int index;

    /**
     * Constructor for command that mark the task as done.
     * @param index indicates the specific task to be marked as done.
     */
    public Command_Done(int index){
        this.index=index;
    }

    /**
     * Execute to mark the specific index task from storage as done.
     * @param common to modify the data storage.
     * @param ui to display the response text of marking the task as done.
     * @throws DukeException when the marking of the task is unsuccessful.
     */
    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        ui.display(common.markAsDone(index));
    }
}
