package dukecommand;

import dukeexceptions.DukeException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import duketasks.Task;
import dukeui.DukeUI;

/**
 * Represents a command that will delete a certain Task from the DukeList
 */

public class DeleteCommand extends DukeCommand {
    private int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }


    /**
     * Executes the delete command by removing the task from the DukeList according to index.
     * DukeUI returns the deleted task if available and the number of remaining tasks.
     *
     * @return String DeleteCommand output message
     *
     * @param dl DukeList from the main Duke program
     * @param ds DukeStorage from the main Duke program
     * @param dui DukeUI from the main Duke program
     * @throws DukeException Thrown when the list is empty or index >= 0, index > DukeList's size
     */
    @Override
    public String execute(DukeList dl, DukeStorage ds, DukeUI dui) throws DukeException {
        Task removedTask = dl.deleteTask(deleteIndex);
        ds.save(dl);
        dui.holdCurrentMessage("    The task requested has been successfully removed:");
        dui.holdCurrentMessage("      " + removedTask);

        int remainingNum = dl.getNumOfTasks();
        if (remainingNum == 1) {
            dui.holdCurrentMessage("    There is " + remainingNum + " task left.");
        } else {
            dui.holdCurrentMessage("    There are " + remainingNum + " tasks left.");
        }

        return dui.getCurrentMessage();
    }

    /**
     * Returns a True boolean if this is a ByeCommand
     *
     * @return False as this is not a ByeCommand
     */
    @Override
    public boolean getIsExit() {
        return this.isExit;
    }
}
