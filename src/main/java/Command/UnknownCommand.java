package Command;

import DukeExceptions.DukeException;
import DukeExceptions.UnknownCommandException;
import List.DukeList;
import Storage.DukeStorage;
import UI.DukeUI;

public class UnknownCommand extends DukeCommand {
    public UnknownCommand(){
    }

    @Override
    public void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
        throw new UnknownCommandException("Sorry! I don't understand that command.");
    }
}
