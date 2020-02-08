package dukecommand;

import dukeexceptions.DukeException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import dukeui.DukeUI;

/**
 * Represents a command that will end the Duke program after execution
 */

public class ByeCommand extends DukeCommand {
    public ByeCommand() {
        this.isExit = true;
    }


    /**
     * Executes the Bye Command by toggling the isExit boolean to true
     * and returning the exit message via the DukeUI;
     *
     * @param dl DukeList from the main Duke program
     * @param ds DukeStorage from the main Duke program
     * @param dui DukeUI from the main Duke program
     */
    @Override
    public String execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
        this.isExit = true;
        dui.holdCurrentMessage("    " + "Bye. Hope to see you again soon!");
        return dui.getCurrentMessage();
    }


    /**
     * Returns a True boolean if this is a ByeCommand
     *
     * @return True as this is a ByeCommand
     */
    @Override
    public boolean getIsExit() {
        return this.isExit;
    }
}
