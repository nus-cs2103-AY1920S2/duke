package Command;

import DukeExceptions.DukeException;
import List.DukeList;
import Storage.DukeStorage;
import UI.DukeUI;

public abstract class DukeCommand {
    protected boolean isExit = false;
    public abstract void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException;
}
