package dukecommand;

import dukeexceptions.InvalidEntryException;
import dukelist.DukeList;
import dukestorage.DukeStorage;
import duketasks.Task;
import dukeui.DukeUI;

/**
 * Represents a command that adds a new Task into the DukeList
 */

public class AddCommand extends DukeCommand {
    private Task toBeAdded;

    public AddCommand(Task tba) {
        this.toBeAdded = tba;
    }

    /**
     * Executes the requested command by adding the task to the list, saving the changes in the storage
     * and printing the success message of adding the task
     *
     * @param dl DukeList from the main Duke program
     * @param ds DukeStorage from the main Duke program
     * @param dui DukeUI from the main Duke program
     */
    public void execute(DukeList dl, DukeStorage ds, DukeUI dui) {
        dl.addTask(toBeAdded);
        ds.save(dl);
        dui.printCustomMessage("    Got it I've added this task:\n      " + toBeAdded);
        System.out.printf("    Now you have %d tasks in the list.\n", dl.getNumOfTasks());
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
