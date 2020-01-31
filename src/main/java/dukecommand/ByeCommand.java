package dukecommand;

import dukeexceptions.DukeException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import dukeui.DukeUI;

public class ByeCommand extends DukeCommand {
    public ByeCommand() {
        this.isExit = true;
    }
    @Override
    public void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
        this.isExit = true;
        dui.printCustomMessage("    " + "Bye. Hope to see you again soon!");
    }

    @Override
    public boolean getIsExit() {
        return this.isExit;
    }
}
