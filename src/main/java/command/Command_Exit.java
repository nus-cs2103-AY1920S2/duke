package command;

import core.Common;
import core.Ui;
import dukexception.DukeException;

/**
 * Specific command to exit the programme.
 */
public class Command_Exit extends Command{

    /**
     * Execute to exit the programme.
     * @param common to save the data before exiting programme.
     * @param ui to display the exit response text.
     * @throws DukeException when the saving of data is unsuccessful.
     */
    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        common.saveData();
        ui.endLog();
    }

    /**
     * Indicates the ermination of the program.
     * @return boolean that indicates termination of the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
