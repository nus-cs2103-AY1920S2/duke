package dukecommand;

import dukeexceptions.DukeException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import dukeui.DukeUI;

/**
 * Represents an abstract class which all over commands will extend from
 */

public abstract class DukeCommand {
    protected boolean isExit = false;
    public abstract String execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException;
    public abstract boolean getIsExit();
}
