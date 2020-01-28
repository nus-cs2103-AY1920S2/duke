package Duke.Command;

import Duke.DukeExceptions.DukeException;
import Duke.List.DukeList;
import Duke.Storage.DukeStorage;
import Duke.UI.DukeUI;

public class ByeCommand extends DukeCommand {
    public ByeCommand(){
        this.isExit = true;
    }
    @Override
    public void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
        dui.printCustomMessage("    " + "Bye. Hope to see you again soon!");
    }
}
