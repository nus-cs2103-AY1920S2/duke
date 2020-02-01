package dukecommand;

import dukeexceptions.DukeException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import duketasks.Task;
import dukeui.DukeUI;

public class DoneCommand extends DukeCommand {
    private int doneIndex;

    public DoneCommand (int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public void execute (DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
        Task curr = dl.markTaskAsDone(doneIndex);
        dui.printCustomMessage("    Nice! I've marked this task as done:");
        dui.printCustomMessage("    " + curr);
    }

    @Override
    public boolean getIsExit() {
        return this.isExit;
    }
}
