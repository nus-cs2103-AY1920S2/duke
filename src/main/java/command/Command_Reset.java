package command;

import core.Common;
import core.Ui;
import core.UiMessage;
import dukexception.DukeException;

/**
 * Specific command that reset the storage.
 */
public class Command_Reset extends Command{

    /**
     * Execute to reset the temporal and permanent data storage.
     * @param common to clear the data storage.
     * @param ui to display the response text of successful reset.
     * @throws DukeException when the reading and saving of the data is unsuccessful.
     */
    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        common.reset();
        ui.display(UiMessage.RESET.getMsg());
    }
}
