package dukecommand;

import dukeexceptions.DukeException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import duketasks.Task;
import dukeui.DukeUI;

/**
 * Represents a command that will mark a certain Task as complete from the DukeList
 */

public class DoneCommand extends DukeCommand {
    private int doneIndex;

    public DoneCommand (int doneIndex) {
        this.doneIndex = doneIndex;
    }

    /**
     * Executes the done command by marking a task as done from the DukeList according to given index.
     * returns the marked task if available.
     *
     * @return String Output message of a successful done command
     *
     * @param dl DukeList from the main Duke program
     * @param ds DukeStorage from the main Duke program
     * @param dui DukeUI from the main Duke program
     * @throws DukeException Thrown when the list is empty or index >= 0, index > DukeList's size
     */
    @Override
    public String execute (DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
        Task curr = dl.markTaskAsDone(doneIndex);
        ds.save(dl);
        dui.holdCurrentMessage("    Nice! I've marked this task as done:");
        dui.holdCurrentMessage("    " + curr);

        return dui.getCurrentMessage();
    }

    @Override
    public boolean getIsExit() {
        return this.isExit;
    }
}
