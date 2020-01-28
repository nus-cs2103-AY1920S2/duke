package Command;

import DukeExceptions.DukeException;
import DukeExceptions.InvalidEntryException;
import List.DukeList;
import Storage.DukeStorage;
import Tasks.Task;
import UI.DukeUI;

public class DoneCommand extends DukeCommand {
    private int doneIndex;
    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public void execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
        Task curr = dl.markTaskAsDone(doneIndex);
        dui.printCustomMessage("    Nice! I've marked this task as done:");
        dui.printCustomMessage("    " + curr);
    }
}
