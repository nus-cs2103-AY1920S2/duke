package command;

import core.Common;
import core.Ui;
import dukexception.DukeException;

/**
 * Specific command that display the list of task.
 */
public class ListCommand extends Command{

    /**
     * Executes to display the list of task in the storage.
     * @param common to retrieve the data from storage.
     * @param ui to display the list of task.
     * @throws DukeException when the list is empty.
     */
    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        ui.display(common.printList());
    }
}
