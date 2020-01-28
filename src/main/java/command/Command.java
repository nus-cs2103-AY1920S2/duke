package command;

import core.Common;
import core.Ui;
import dukexception.DukeException;

/**
 * A abstract command to be inherited by specific commands
 * Contains the generic method to be polymorphic.
 */
public abstract class Command {

    /**
     * Execute the specific command to modify the system and
     * output the response to the user.
     * @param common to modify the data storage.
     * @param ui to display the response text of the command.
     * @throws DukeException when any subsequent process triggers general exception
     */
    public void execute(Common common, Ui ui) throws DukeException {}

    /**
     * Indicates the non-termination of the program.
     * @return boolean that indicates non-termination of the program.
     */
    public boolean isExit(){
        return false;
    }
}
