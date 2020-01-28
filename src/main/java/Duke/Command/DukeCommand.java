package Duke.Command;

import Duke.DukeExceptions.DukeException;
import Duke.List.DukeList;
import Duke.Storage.DukeStorage;
import Duke.UI.DukeUI;

public abstract class DukeCommand {
    protected boolean isExit = false;
    public abstract void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException;
}
