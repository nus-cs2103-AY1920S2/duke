package dukecommand;

import dukeexceptions.DukeException;
import dukeexceptions.UnknownCommandException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import dukeui.DukeUI;

/**
 * Represents a command is not understood by Duke program
 */

public class UnknownCommand extends DukeCommand {
    public UnknownCommand(){
    }

    /**
     * Throws an UnknownCommandException as the command is not understood by the program
     *
     * @param dl DukeList from the main Duke program
     * @param ds DukeStorage from the main Duke program
     * @param dui DukeUI from the main Duke program
     * @throws DukeException Thrown to represent an unknown command
     */
    @Override
    public String execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
        throw new UnknownCommandException("Sorry! I don't understand that command.");
    }

    @Override
    public boolean getIsExit() {
        return this.isExit;
    }
}
