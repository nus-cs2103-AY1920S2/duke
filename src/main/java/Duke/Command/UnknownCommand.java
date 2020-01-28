package Duke.Command;

import Duke.DukeExceptions.DukeException;
import Duke.DukeExceptions.UnknownCommandException;
import Duke.List.DukeList;
import Duke.Storage.DukeStorage;
import Duke.UI.DukeUI;

public class UnknownCommand extends DukeCommand {
    public UnknownCommand(){
    }

    @Override
    public void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
        throw new UnknownCommandException("Sorry! I don't understand that command.");
    }
}
