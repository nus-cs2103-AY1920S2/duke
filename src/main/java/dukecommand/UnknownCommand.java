package dukecommand;

import dukeexceptions.DukeException;
import dukeexceptions.UnknownCommandException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import dukeui.DukeUI;

public class UnknownCommand extends DukeCommand {
    public UnknownCommand(){
    }

    @Override
    public void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
        throw new UnknownCommandException("Sorry! I don't understand that command.");
    }

    @Override
    public boolean getIsExit() {
        return this.isExit;
    }
}
