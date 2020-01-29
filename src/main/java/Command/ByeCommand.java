package Command;

import DukeExceptions.DukeException;
import List.DukeList;
import Storage.DukeStorage;
import UI.DukeUI;

public class ByeCommand extends DukeCommand {
    public ByeCommand(){
        this.isExit = true;
    }
    @Override
    public void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
        dui.printCustomMessage("    " + "Bye. Hope to see you again soon!");
    }
}
