package dukecommand;

import dukeexceptions.DukeException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import dukeui.DukeUI;

public abstract class DukeCommand {
    protected boolean isExit = false;
    public abstract void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException;
    public abstract boolean getIsExit();
}
